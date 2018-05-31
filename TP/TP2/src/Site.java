
public class Site {
	
	
	private int idSite;
	private int stockMax;
	private int stockInit;
	private int nbVelo;
	
	
	public Site(int idSite, int stockInit, int stockMax){
		this.idSite = idSite;
		this.stockInit = stockInit;
		this.stockMax = stockMax;
		this.nbVelo = stockInit;
	}
	
	public synchronized int maintenance(int nbVeloInCamion){
		int surplusVelo = this.nbVelo - this.stockInit;
		
		if(surplusVelo > 0){
			this.nbVelo = this.stockInit;
			notifyAll();
			return nbVeloInCamion + surplusVelo;
		}else if (surplusVelo < 0){
			if(nbVeloInCamion <  Math.abs(surplusVelo)){
				this.nbVelo = this.nbVelo + nbVeloInCamion;
				notifyAll();
				return 0;
			}else{
				this.nbVelo = stockInit;
				notifyAll();
				return nbVeloInCamion - Math.abs(surplusVelo);
			}
		}else{
			return nbVeloInCamion;
		}
	}
	
	public synchronized void addVelo() {
		if(this.nbVelo < stockMax){
			this.nbVelo++;
		}
	}
	
	public synchronized void removeVelo() {
		if(this.nbVelo > this.stockInit){
			this.nbVelo--;
		}
	}

	public synchronized int getIdSite() {
		return idSite;
	}
	
	public synchronized int getStockMax() {
		return stockMax;
	}
	
	public synchronized int getStockInit() {
		return stockInit;
	}
	
	public synchronized int getNbVelo(){
		return nbVelo;
	}
	
	public synchronized void printSite(){
		System.out.println("Site " + idSite + ": " + nbVelo + "/" + stockMax);
	}

	
	public synchronized boolean slotAvailable(){
		return nbVelo<stockMax;
	}
	
	public synchronized void takeVelo(){
		while(nbVelo == 0){
			try {
				wait(); 
			} catch (InterruptedException e1) {e1.printStackTrace();}
		}
		nbVelo --;
		notifyAll();
	}
	
	
	public synchronized void putVelo(){
		while(nbVelo == stockMax){
			try {
				wait(); 
			} catch (InterruptedException e1) {e1.printStackTrace();}
		}
		nbVelo ++;
		notifyAll();
		
	}
	
}

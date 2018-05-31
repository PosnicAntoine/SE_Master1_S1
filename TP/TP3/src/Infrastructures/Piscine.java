package Infrastructures;

public class Piscine {
	
	private int maxClient;
	private int currentClient;
	
	private boolean clean;
	
	public Piscine(int maxClient) {
		this.maxClient = maxClient;
		this.currentClient = 0;
		this.clean= true;
	}
	
	public int getMaxClient(){
		return this.maxClient;
	}
	
	public synchronized int getCurrentClient(){
		return this.currentClient;
	}
	
	public synchronized void setClean(boolean c){
		this.clean = c;
		if(this.clean){
			notifyAll();
		}
	}
	
	public synchronized void enterCleaning(){
		while(currentClient != 0) {
			try {
				wait(); 
			} catch (InterruptedException e1) {e1.printStackTrace();}
		}
	}
	
	public synchronized void leaveCleaning() {
		notifyAll();
	}
	
	public synchronized void addClient(){
		while(currentClient == this.maxClient || !this.clean) {
			try {
				wait(); 
			} catch (InterruptedException e1) {e1.printStackTrace();}
		}
		
		this.currentClient+=1;
	}
	
	public synchronized void rmClient() {
		this.currentClient-=1;
		notifyAll();
	}

	
}

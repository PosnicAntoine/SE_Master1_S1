public class Camion implements Runnable {
	
	private Site[] allSites;
	int minIdSite;
	int maxIdSite;
	
	private int nbVelo;
	
	
	public Camion(Site[] sites, int minIdSite, int maxIdSite, int initVelos){
		this.allSites = sites;
		this.minIdSite = minIdSite;
		this.maxIdSite = maxIdSite;
		
		this.nbVelo = initVelos;
	}
	
	
	private void startMaintenance(int indexTab){
		printCamion("Debut", indexTab);
		nbVelo = allSites[indexTab].maintenance(nbVelo);
		printCamion("Fin", indexTab);
	}
	
	private synchronized void printCamion(String s, int indexTab){
		System.out.println(s+" MAINTENANCE sur "+this.allSites[indexTab].getIdSite()+" avec "+this.nbVelo);
		this.allSites[indexTab].printSite();
		
	}
	
	@Override
	public void run() {
		while(true){
			for (int ncurr = this.minIdSite; ncurr <= maxIdSite; ncurr++){
				for (int i = 0; i<allSites.length; i++){
					try { Thread.sleep(10); } 
			    	catch(InterruptedException e) {}
					if(allSites[i].getIdSite() == ncurr) startMaintenance(i);
				}
			}
		}		
	}

}

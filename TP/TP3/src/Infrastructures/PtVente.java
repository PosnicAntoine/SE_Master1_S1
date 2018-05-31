package Infrastructures;

public class PtVente {
	
	private SalleAttente salle;
	
	public PtVente(SalleAttente salle){
		this.salle = salle;
	}
	//semaphores plus efficaces?Tres certainnement! Car pas de sleep en section critique
	public synchronized void processClient(){
		// temps achat ticket simulé avec des variables critiques
		try { Thread.sleep(15);} 
    	catch(InterruptedException e) {}
		this.salle.push(this);
	}
	
}

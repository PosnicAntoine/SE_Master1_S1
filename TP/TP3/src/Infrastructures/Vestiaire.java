package Infrastructures;

public class Vestiaire {
	
	private int maxClient;
	private int currentClient;
	
	public Vestiaire(int maxClient){
		this.maxClient = maxClient;
		this.currentClient = 0;
	}
	
	public int getMaxClient(){
		return this.maxClient;
	}
	
	public int getCurrentClient(){
		return this.currentClient;
	}
	
	public synchronized void addClient(){
		while(currentClient == this.maxClient){
			try {
				wait(); 
			} catch (InterruptedException e1) {e1.printStackTrace();}
		}
		this.currentClient+=1;
	}
	
	public synchronized void rmClient(){
		this.currentClient-=1;
		notifyAll();
	}
}

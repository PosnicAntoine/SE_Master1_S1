package Infrastructures;

public class PtPalme {
	private int nbPalme;
	private int nbClientIn;
	
	public PtPalme(int nbPalmeMax){
		this.nbClientIn = 0;
		this.nbPalme = nbPalmeMax;
	}
	
	public synchronized void addClient(){
		while(this.nbClientIn >= this.nbPalme/2){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.nbClientIn++;
	}
	
	public synchronized void rmClient(){
		this.nbClientIn--;
		notifyAll();
	}
	
	public synchronized void takePalme(){
		while(this.nbPalme == 0){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.nbPalme--;
	}
	
	public synchronized void returnPalme(){
		this.nbPalme++;
		notifyAll();
	}
}

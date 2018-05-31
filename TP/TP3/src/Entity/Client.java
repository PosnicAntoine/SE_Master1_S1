package Entity;

import java.util.Observable;
import java.util.Random;

import Infrastructures.Batiment;
import View.TableModel;

public class Client extends Observable implements Runnable{
	private Batiment bat;
	
	private StatePattern state = null;
	
	boolean running = true;
	
	private TableModel model;
	
	private boolean wantPalme;
	
	public Client(Batiment bat) {
		this(bat, null);
	}
	
	public Client(Batiment bat, TableModel model) {
		this.model = model;
		this.bat = bat;
		this.wantPalme = new Random().nextBoolean();
	}
	
	@Override
	public void run() {
		//simulation du temps d'arrivée au batiment de la picsine (avant de se presenter pour le guichet)
		try { Thread.sleep( new Random().nextInt(4000)); } 
    	catch(InterruptedException e) {}
		this.setState(StatePattern.ST_ACHAT_TICKET);
		this.bat.addClient();
		//arivée a la picsine
		while(this.running) {
			this.state.process(this, this.bat);
		}
		
		this.bat.rmClient();
//		System.out.println("\tBatiment < sortie d'un client");
	}

	public Boolean getWantPalme(){
		return this.wantPalme;
	}
	
	public StatePattern getState() {
		return state;
	}

	public void setState(StatePattern state) {
		if(this.state != null && this.model != null) {
			this.model.sub(this.state);
		}
		this.state = state;
		if(this.state != null && this.model != null) {
			this.model.add(this.state);
		}
	}
}

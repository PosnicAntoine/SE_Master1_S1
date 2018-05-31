package Infrastructures;

import java.util.Observable;

import Entity.Client;
import View.Vue;

public class Batiment {
	private Piscine piscine;
	private SalleAttente salle;
	private Vestiaire vestiaire;
	private PtPalme ptPalme;
	
	private int currentNbClient = 0;

	public Batiment(int poolMax, int vestiaireMax, int nbPalmeMax, int nbGuichet) {
		
		this.piscine = new Piscine(poolMax);
		this.salle = new SalleAttente(nbGuichet);
		this.vestiaire = new Vestiaire(vestiaireMax);
		this.ptPalme = new PtPalme(nbPalmeMax);
	}
	
	public synchronized void addClient() {
		this.currentNbClient++;
	}
	
	public synchronized void rmClient() {
		this.currentNbClient--;
	}

	public Piscine getPiscine() {
		return piscine;
	}

	public SalleAttente getSalleAttente() {
		return this.salle;
	}

	public Vestiaire getVestiaire() {
		return vestiaire;
	}
	
	public PtPalme getPtPalme(){
		return this.ptPalme;
	}
	
	
	
}

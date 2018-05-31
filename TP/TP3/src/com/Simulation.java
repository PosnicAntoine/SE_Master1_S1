package com;
import java.util.ArrayList;
import java.util.List;

import View.Vue;
import Entity.Client;
import Entity.Janitor;
import Infrastructures.Batiment;



public class Simulation {
	
	public static final int nbOfClients = 1000;
	public static final int maxClientVestiaire = 35;
	public static final int maxClientPiscine = 300;
	public static final int maxPalmes = 153;
	public static final int nbOfGuichet = 4;
	
	public static final boolean log = true;
	
	public static void main(String [] args){
		Batiment b = new Batiment(maxClientPiscine, maxClientVestiaire, maxPalmes, nbOfGuichet);
		Janitor j = new Janitor(b);
		List<Client>  cList = new ArrayList<Client>();
		
//		Vue v = new Vue();
		for(int i = 0; i < nbOfClients; i++) {
			Client c = new Client( b, null);
			cList.add(c);
			new Thread(c).start();
		}
		Thread janitor = new Thread(j);
		janitor.setDaemon(true);
		janitor.start();
	}
}

package Entity;

import java.util.Random;

import com.Simulation;

import Infrastructures.Batiment;
import Infrastructures.Piscine;

public class Janitor implements Runnable{
	private Piscine p;
	
	public Janitor(Batiment b){
		this.p = b.getPiscine();
	}

	@Override
	public void run() {
		while(true) {
			try { Thread.sleep( new Random().nextInt(1000)+10000); } 
	    	catch(InterruptedException e) {}
			
			if(Simulation.log) {
				System.out.println(Thread.currentThread() +"\n\tBLOCKING_POOL\n");
			}
			
			this.p.setClean(false);
			this.p.enterCleaning();
			
			if(Simulation.log) {
				System.out.println(Thread.currentThread() +"\n\tCLEANING with: "+p.getCurrentClient()+" clients swimming\n");
			}
			
			try { Thread.sleep(10000); } 
	    	catch(InterruptedException e) {}
			
			if(Simulation.log) {
				System.out.println(Thread.currentThread() +"\n\tFINISHED_CLEANING\n");
			}
			
			this.p.setClean(true);
			this.p.leaveCleaning();
		}
	}
}

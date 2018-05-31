package Infrastructures;

import java.util.Stack;

import Entity.Client;

public class SalleAttente {
	
	private Stack<PtVente> queue;
	
	public SalleAttente(int nbPtVente) {
		this.queue = new Stack<PtVente>();
		
		for(int i = 0; i < nbPtVente; i++) {
			this.queue.add(new PtVente(this));
		}
	}
	
	public synchronized PtVente pop() {
		while(this.queue.empty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return this.queue.pop();
	}
	
	public synchronized void push(PtVente pv) {
		this.queue.add(pv);
		notifyAll();
	}
}

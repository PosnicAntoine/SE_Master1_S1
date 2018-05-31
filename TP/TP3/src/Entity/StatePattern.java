package Entity;

import java.util.Random;

import Infrastructures.Batiment;

import com.Simulation;

public enum StatePattern implements StateStrategy {

	ST_ACHAT_TICKET {
		/**
		 * Etat 1 : Achat du ticket
		 */

		@Override
		public void process(Client c, Batiment b) {
			if(Simulation.log) {
				System.out.println(Thread.currentThread() +"\n\tST_ACHAT_TICKET\n");
			}
			b.getSalleAttente().pop().processClient();
			c.setState(ST_ENTRY_CHANGING_ROOM);
		}
		
	},
	
	ST_ENTRY_CHANGING_ROOM {

		/**
		 * Etat 2 : attente vers vestiaire
		 */
		
		@Override
		public void process(Client c, Batiment b) {
			if(Simulation.log) {
				System.out.println(Thread.currentThread() +"\n\tST_ENTRY_CHANGING_ROOM\n");
			}
			// TODO Auto-generated method stub
			b.getVestiaire().addClient();
			c.setState(ST_CHANGING_ROOM_IN);
		}
		
	},
	
	ST_CHANGING_ROOM_IN {

		/**
		 * Etat 3 : Se changer -> mettre son maillot de bain
		 */
		
		@Override
		public void process(Client c, Batiment b) {
			if(Simulation.log) {
				System.out.println(Thread.currentThread() +"\n\tST_CHANGING_ROOM_IN\n");
			}
			// TODO Auto-generated method stub
			try {
				Thread.sleep(new Random().nextInt(800) + 200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			b.getVestiaire().rmClient();
			if (c.getWantPalme()){
				c.setState(ST_GO_TAKE_PALME);
			}else{
				c.setState(ST_GO_POOL);
			}
		}
		
	},

	ST_GO_TAKE_PALME{

		/**
		 * Etat 4bis : Attente pour aller recuperer des palmes.
		 */
		
		@Override
		public void process(Client c, Batiment b) {
			if(Simulation.log) {
				System.out.println(Thread.currentThread() +"\n\tST_GO_TAKE_PALME\n");
			}
			// TODO Auto-generated method stub
			b.getPtPalme().addClient();
			b.getPtPalme().takePalme();
			b.getPtPalme().takePalme();
			b.getPtPalme().rmClient();
			c.setState(ST_GO_POOL);
		}
		
	},
	
	ST_GO_POOL {

		/**
		 * Etat 4 : Attente pour aller dans la piscine
		 */
		
		@Override
		public void process(Client c, Batiment b) {
			if(Simulation.log) {
				System.out.println(Thread.currentThread() +"\n\tST_GO_POOL\n");
			}
			// TODO Auto-generated method stub
			b.getPiscine().addClient();
			c.setState(ST_SWIMMING);
		}
		
	},
	
	ST_SWIMMING {

		/**
		 * Etat 5 : dans la piscine ...
		 */
		
		@Override
		public void process(Client c, Batiment b) {
			if(Simulation.log) {
				System.out.println(Thread.currentThread() +"\n\tST_SWIMMING\n");
			}
			// TODO Auto-generated method stub
			try {
				Thread.sleep(new Random().nextInt(1800) + 1200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			b.getPiscine().rmClient();
			
			if (c.getWantPalme()){
				c.setState(ST_GO_RETURN_PALME);
			}else{
				c.setState(ST_LEAVE_POOl);
			}
		}
		
	},
	
	ST_GO_RETURN_PALME{

		/**
		 * Etat 6bis : Attente pour aller rendre les palmes.
		 */
		
		@Override
		public void process(Client c, Batiment b) {
			if(Simulation.log) {
				System.out.println(Thread.currentThread() +"\n\tST_GO_RETURN_PALME\n");
			}
			// TODO Auto-generated method stub
			b.getPtPalme().returnPalme();
			b.getPtPalme().returnPalme();
			c.setState(ST_LEAVE_POOl);
		}
		
	},
	
	ST_LEAVE_POOl {

		/**
		 * Etat 6 : sortir de la piscine, attente vers vestiaire
		 */
		
		@Override
		public void process(Client c, Batiment b) {
			if(Simulation.log) {
				System.out.println(Thread.currentThread() +"\n\tST_LEAVE_POOl\n");
			}
			// TODO Auto-generated method stub
			b.getVestiaire().addClient();
			c.setState(ST_CHANGING_ROOM_OUT);
		}
		
	},

	ST_CHANGING_ROOM_OUT {

		/**
		 * Etat 7 : sortir, de la piscine, stopper le thread
		 */
		
		@Override
		public void process(Client c, Batiment b) {
			if(Simulation.log) {
				System.out.println(Thread.currentThread() +"\n\tST_CHANGING_ROOM_OUT\n");
			}
			// TODO Auto-generated method stub
			try {
				Thread.sleep(new Random().nextInt(800) + 200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			c.setState(ST_EXIT);
		}
		
	},
	
	ST_EXIT {

		/**
		 * no state after that...
		 */
		
		@Override
		public void process(Client c, Batiment b) {
			if(Simulation.log) {
				System.out.println(Thread.currentThread() +"\n\tST_EXIT\n");
			}
			b.getVestiaire().rmClient();
			c.running = false;
			return;
		}
		
	}, 
}

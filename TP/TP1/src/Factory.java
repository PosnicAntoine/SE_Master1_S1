//Please comment each modification of this skeleton to highlight the code asked for this lab.

/**
 * Objects of class Factory represent a factory with two processing workshops.
 * A factory has a stock of products to process and, a stock of processed products (initially empty).
 * Both workshops process half of the products. The method activate() launch successively the two
 * workshops and then print the state of the stocks when the processing is finished.
 */
class Factory{
	int nb = 100;
	/**
	 * source stock
	 */
    Stock sourceStock = new Stock("source", nb, nb);
    /**
     * intermediate stock
     */
    Stock intermediateStock = new Stock("intermediate", 0, 1);
    /**
     * destination stock
     */
    Stock destinationStock = new Stock("destination", 0, nb);
    /**
     * processing workshops
     */
    Workshop workshop11 = new Workshop(sourceStock, intermediateStock, nb/2);
    Workshop workshop12 = new Workshop(sourceStock, intermediateStock, nb/2);
    Workshop workshop21 = new Workshop(intermediateStock, destinationStock, nb/2);
    Workshop workshop22 = new Workshop(intermediateStock, destinationStock, nb/2);
    
    /**
     * Launch the factory work
     */
    public void activate() {
    	Thread thread11 = new Thread(workshop11, "Thread(11)");
    	Thread thread12 = new Thread(workshop12, "Thread(12)");
    	Thread thread21 = new Thread(workshop21, "Thread(21)");
    	Thread thread22 = new Thread(workshop22, "Thread(22)");
    	
   		thread21.start();
   		thread22.start();
    	thread11.start();
   		thread12.start();
    	
   		try {thread11.join();} catch (InterruptedException e) {e.printStackTrace();}
   		try {thread21.join();} catch (InterruptedException e) {e.printStackTrace();}
   		try {thread12.join();} catch (InterruptedException e) {e.printStackTrace();}
   		try {thread22.join();} catch (InterruptedException e) {e.printStackTrace();}
   		
   		
   		System.out.println("\n    Etat Final:");
    	Thread thread = Thread.currentThread();
    	sourceStock.print(thread);
    	intermediateStock.print(thread);
    	destinationStock.print(thread);

    }
    
    /**
     * Entry point for the lab
     * @param args Not used
     */
    public static void main(String[] args) {
    	Factory factory = new Factory();
    	factory.activate();
    }
}

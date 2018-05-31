//import com.sun.xml.internal.bind.v2.runtime.Name;

//Please comment each modification of this skeleton to highlight the code asked for this lab.


/**
 * Les objets instances de la classe Atelier representent des ateliers de transformation.
 * Le fonctionnement est le suivant : l'appel a transformer retire un element du stock A,
 * attend 100 ms, puis ajoute un element au stock B.
 * La methode travailler() effectue n transformations successives, n etant un parametre
 * fourni a la creation de l'objet.
 */

/**
 * Objects of class Workshop represent processing workshops. The operation is as follows:
 * The method processing() removes an element from a Stock A, waits 100 ms 1 and, finally adds 
 * an element to a Stock B. The method work() performs n processings successively.
 */

class Workshop implements Runnable{
	
	private Thread thread;

	/**
	 * source stock
	 */
    private Stock A;
    /**
     * Destination stock
     */
    private Stock B;
    
    /** 
     * Number of processings to perform when the method work is called
     */
    private int nbProcessing;

    /**
     * Constructor
     * @param A: source stock
     * @param B: destination stock
     * @param nbProcessing: Number of processings to perform
     */
    public Workshop(Stock A, Stock B, int nbProcessing) {
        this.A = A;
        this.B = B;
        this.nbProcessing = nbProcessing;
    }

    /**
     * Perform one processing
     */
    public void processing() {
        A.destock(thread);
    	//try { Thread.sleep(100); } catch(InterruptedException e) {}
        B.stock(thread);
    }

    /**
     * Perform nbProcessing processings
     */
    public void work() {
        for(; nbProcessing > 0; nbProcessing--)
            processing();
    }
    
    public void run(){
    	this.thread = Thread.currentThread();
    	work();
    }

    /**
     * Method main for unit testing
     * @param args Not used
     */
    static public void main(String[] args) {
/**
    	Stock stock1 = new Stock("stock1",10);
    	Stock stock2 = new Stock("stock2",10);
    	
    	Workshop workshop1 = new Workshop(stock1, stock2, 10);
    	Workshop workshop2 = new Workshop(stock1, stock2, 10);
    	
    	Thread thread1 = new Thread(workshop1);
    	Thread thread2 = new Thread(workshop2);
    	
    	thread1.start();
    	thread2.start();
    	
    	try {
			thread1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	try {
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
**/
    }
}

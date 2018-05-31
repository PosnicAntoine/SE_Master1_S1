//Please comment each modification of this skeleton to highlight the code asked for this lab.

/**
 * Objects of class Stock represent a set of products stacked one above the other.
 * Due to the stack layout, it is not possible that two workshops take two products 
 * at the same time.
 */
class Stock {
	
	private int nbMaxProducts;
	/**
	 * Number of products in the stack
	 */
    private int nbProducts;
    /**
     * Name of the stock
     */
    private String name;

    /**
     * Constructor
     * @param name: Name of the new stock
     * @param nbProducts: Initial number of products
     */
    public Stock(String name, int nbProducts, int maxProducts) {
        this.nbProducts = nbProducts;
        this.name = name;
        this.nbMaxProducts = maxProducts;
    }
    
    public int getNbProducts(){
    	return this.nbProducts;
    }

    /**
     * Add a product on top of the stack
     * @param thread 
     */
    public synchronized void stock(Thread thread) {
    	while(nbProducts >= nbMaxProducts) {
    		try {
    			System.out.println(thread.getName() + " is Waiting to stock"); 
    			wait();
    			} catch (InterruptedException e1) {e1.printStackTrace();}
    	}
        nbProducts++;
        this.print(thread);
        if(nbProducts > 0 || nbProducts < nbMaxProducts){
        	notifyAll(); System.out.println(thread.getName() + " is Notifying");
        }
    }

    /**
     * Remove the highest product on the stack
     * @param thread 
     */
    public synchronized void destock(Thread thread) {
    	while(nbProducts <= 0) {
    		try {
    			wait(); 
    			System.out.println(thread.getName() + " is Waiting to destock");
    			} catch (InterruptedException e1) {e1.printStackTrace();}
    	}
        nbProducts--;
        this.print(thread);
        if(nbProducts > 0 || nbProducts < nbMaxProducts){
        	notifyAll(); System.out.println(thread.getName() + " is Notifying");
        }
    }

    /**
     * Print the state of the stock object
     * @param thread 
     */
    public synchronized void print(Thread thread) {
        System.out.println(thread.getName() + " : " + name + ": " + nbProducts + " product(s).");
    }

    /** 
     * Method main for unit testing
     * @param args Not used
     */
    static public void main(String[] args) {
/**
    	
    	Stock stock1 = new Stock("stock1",10);
    	Stock stock2 = new Stock("stock2",10);
    	stock1.print();
    	stock2.print();
    	
    	System.out.println("DESTOCKAGE");
    	for(int i = 0; i<5; i++) stock1.destock();
    	stock1.print();
    	for(int i = 0; i<10; i++) stock2.destock();
    	stock2.print();
    	
    	System.out.println("RESTOCKAGE");
    	for(int i = 0; i<5; i++) stock1.stock();
    	for(int i = 0; i<5; i++) stock1.destock();
    	stock1.print();
    	for(int i = 0; i<10; i++) stock2.destock();
    	stock2.print();
    	stock1.print();
    	for(int i = 0; i<10; i++) stock2.stock();
    	stock2.print();
    	
    	System.out.println("SURDESTOCKAGE");
    	for(int i = 0; i<15; i++) stock1.destock();
    	stock1.print();
    	for(int i = 0; i<11; i++) stock2.destock();
    	stock2.print();
**/

    }
}

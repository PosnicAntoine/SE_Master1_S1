import java.util.Random;
import java.lang.Math;

public class Client implements Runnable{
	
	private Site begin;
	private Site end;
	

	public Client(Site begin, Site end){
		this.begin=begin;
		this.end=end;
	}
	
	public Client(Site sites[]){
		Random rand = new Random();
		this.begin = sites[rand.nextInt(sites.length - 1)];
		this.end = sites[rand.nextInt(sites.length - 1)];
	}
	
	@Override
	public void run() {

		printClient("Debut", begin);
		
		
    	try { Thread.sleep(Math.abs( 10 + end.getIdSite() - begin.getIdSite())); } 
    	catch(InterruptedException e) {}
    	
    	
    	end.putVelo();
    	printClient("Fin",end);		
	}
	
	private synchronized void printClient(String s, Site site){
		System.out.println(s+" sur "+site.getIdSite());
		site.printSite();
		
	}

	public void printClient() {
		System.out.println("Client de: Site "+begin.getIdSite()+ " à " +end.getIdSite()+" |"+begin.getNbVelo()+"|");
	}

}

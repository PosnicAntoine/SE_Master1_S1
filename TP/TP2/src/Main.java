import java.util.Random;


public class Main {
	
	public static int nbOfSites = 100;
	public static int nbOfClients = 1000;
	
	public static int minIdSites = 100;
	public static int maxIdSites = 500;
	
	public static int maxStockVelos = 10;
	
	public static int nbVeloInCamion = 250;
	
	

	public static void main(String[] args) {
		
		Site sites[] = new Site[nbOfSites];
		
		Client clients[] = new Client[nbOfClients];
		Thread clientsThreads[] = new Thread[nbOfClients];
		
		System.out.println("---Generation des Sites :---");
		Random rand = new Random();
		
		for(int i = 0; i < sites.length; i++){
			sites[i] = new Site(
					rand.nextInt(maxIdSites -minIdSites + 1) +minIdSites ,
					maxStockVelos/2,
					maxStockVelos
				);
			sites[i].printSite();
		}
		
		System.out.println("\n---Generation des Clients :---");
		
		for(int i = 0; i < clients.length; i++){
			clients[i] = new Client(sites);
			clientsThreads[i] = new Thread(clients[i], "Thread("+i+")");
			clients[i].printClient();
		}
		
		System.out.println("\n---Generation du Camion :---");
		Camion camion = new Camion(sites, minIdSites, maxIdSites, nbVeloInCamion);
		Thread camionThread = new Thread(camion, "Thread(Camion)");
		

		System.out.println("\n---STARTING SIMULATION---");
		camionThread.start();
		for (int i = 0; i < clientsThreads.length; i++){
			clientsThreads[i].start();
		}
	}

}

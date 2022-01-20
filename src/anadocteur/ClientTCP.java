package anadocteur;
import java.io.IOException ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.io.PrintWriter ;
import java.io.IOException ;
import java.net.Socket ;
import java.net.SocketException;
import java.net.UnknownHostException ;
import java.util.Iterator;

public class ClientTCP extends Thread  {
	private String tcp = new String();
	private String tcpres="";
	private String entrer;
    private Socket socket = null ;
    private PrintWriter flux_sortie = null ;
    private BufferedReader flux_entree = null ;
    private String chaine="" ;
    public ClientTCP(int port) {
		// TODO Auto-generated constructor stub
        try {
            // deuxieme argument : le numero de port que l'on contacte
            socket = new Socket ("127.0.0.1", port) ;
            flux_sortie = new PrintWriter (socket.getOutputStream (), true) ;
            flux_entree = new BufferedReader (new InputStreamReader (
                                        socket.getInputStream ())) ;
        } 
        catch (IOException e) {
            System.err.println ("Hote inconnu\n") ;
            System.err.println ("le client tente d'ouvrir une connextion / d'envoyer un messega vers un port serveur non ouvert\n") ;
            System.exit (1) ;
        } 
	}
	public void setTcp(String tcp) {
		this.tcp=tcp;
	}
	   @Override
	   public void run(){

        BufferedReader entree_standard = new BufferedReader ( new InputStreamReader ( System.in)) ;

    	do {

    		// si non on lit ce que l'utilisateur a tape sur l'entree standard
    		try {
				chaine = entree_standard.readLine () ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		//effectuer une petite vérification de la saissie avant d'envoie au server
    		String[] verifier=chaine.split(";;");
    		int lenVerif = verifier.length;
    		if(verifier[0].equals("ajouter")) {
    			if(lenVerif>=2) {
    				try {
    					int resultat = Integer.parseInt(verifier[1]);
    				}catch(Exception e){
    					System.err.println(verifier[1]+": n'est pas une valeur numérique");
    					e.printStackTrace();
    				}
    			}
    		}
    		// et on l'envoie au serveur
    		
	    		flux_sortie.println(chaine) ;
    	
    		


    		// on lit ce qu'a envoye le serveur
    		try {
				chaine = flux_entree.readLine () ;
	    		if(chaine.length()>1000) {
	        		System.out.println ("Le serveur m'a repondu : résultat trop long || essayer avec l'interface graphique'") ;
	    		}
	    		// et on l'affiche a l'ecran
	    		System.out.println ("Le serveur m'a repondu : " + chaine) ;
	    		tcpres=chaine;
			}catch(SocketException e) {
				System.err.println("Connection avec le serveur perdu");
				System.exit(1);
			} 
    		catch (Exception e) {
				// TODO Auto-generated catch block
    			e.printStackTrace();
				System.exit(1);
			}


        	} while (chaine != null) ;
            try {
            	System.err.println("fermeture de la connection");
                flux_sortie.close () ;
				flux_entree.close () ;
				entree_standard.close () ;
	            socket.close () ;
	            System.exit(1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        
	}

	
	public String getTcp() {
		return chaine;
	}
	
	
	public void exeTcp(String sortie) {
		flux_sortie.println (sortie) ;

		// on lit ce qu'a envoye le serveur
		try {
			chaine = flux_entree.readLine () ;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// et on l'affiche a l'ecran
		System.out.println ("Le serveur m'a repondu : " + chaine) ;
	}
	public void close() throws IOException {
		System.out.println("fin de la connection");
		flux_sortie.println ("Au revoir !") ;
        flux_sortie.close () ;
        flux_entree.close () ;
        socket.close () ;
        System.exit(1);
	}
}

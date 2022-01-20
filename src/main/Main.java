package main;
import java.io.IOException;
import java.net.ServerSocket;

import graphics.Window2;

public class Main {
	public static void main(String[] args) throws IOException   {
    	if(args.length>0) {
    		try {
    		int port = Integer.parseInt(args[0]);
    			new Window2("AnaDocteur",port);	
    		}catch(NumberFormatException e) {
    			System.err.println("le numéro de port doit étre une valeur numerique");
    			System.exit(1);
    		}catch(Exception e) {
    			e.printStackTrace();
    			System.exit(1);
    		}
    	}else {
			new Window2("AnaDocteur",5000);//numéro de port par défaut	
    	}

	}
}

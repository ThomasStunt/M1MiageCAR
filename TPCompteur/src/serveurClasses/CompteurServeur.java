package serveurClasses;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CompteurServeur {

	public static void main(String[] args) {
		CompteurServeur cs = new CompteurServeur();
		
		//Launching server.
		System.out.println("CompteurServeur launching.");
		ServerSocket ps = null;
		Socket as = null;
		Integer nThread;
		
		//Reads the amount of thread needed for every reading. Default is 4.
		try {
			nThread = Integer.parseInt(args[0]);
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			System.out.println("Cannot read value for the number of threads, default will be 4");
			nThread = 4;
		}
		
		//Handles the connections.
		try {
			ps = new ServerSocket(7777);
			while (true) {
				
				/* For every client connected to the server, a ClientThread will be created. It will
				handle the analysis of the sentence and sends the most occured word in the sentence
				to the client. */
				new ReadClientThread((as = ps.accept()), cs, nThread);
				System.out.println("Client connected.");
				
			}
		} catch (IOException e) {
			System.out.println("Cannot connect to port 7777");
		} finally {
			
			try {
				ps.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
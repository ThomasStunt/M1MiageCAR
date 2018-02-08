package serveurClasses;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CompteurServeur {

	public static void main(String[] args) {
		CompteurServeur cs = new CompteurServeur();
		
		System.out.println("CompteurServeur launching.");
		ServerSocket ps = null;
		Socket as = null;
		BufferedReader in = null;
		DataOutputStream out = null;
		Integer nThread;
		
		try {
			nThread = Integer.parseInt(args[0]);
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			System.out.println("Cannot read value for the number of threads, default will be 4");
			nThread = 4;
		}
		
		try {
			ps = new ServerSocket(7777);
			while (true) {
				
				new ClientThread((as = ps.accept()), cs, nThread);
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
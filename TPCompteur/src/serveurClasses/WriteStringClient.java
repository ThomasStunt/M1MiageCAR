package serveurClasses;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class WriteStringClient {

public static void main(String[] args) {
		
		System.out.println("WriteStringClient launching.");
		Socket as = null;
		DataOutputStream out = null;
		BufferedReader in = null;
		Boolean exit = false;
		
		Scanner sc;
		
		try {
			//Connecting to the server
			as = new Socket(InetAddress.getLocalHost(), Integer.parseInt(args[0]));
			System.out.println("Connected to the server.");
			
			//Dialogs with server.
			out = new DataOutputStream(as.getOutputStream());
			in = new BufferedReader(new InputStreamReader(as.getInputStream()));
			
			//Starts reading the file
			System.out.println("You can start writing. Write 'stop' to end writing.");
			
			sc = new Scanner(System.in);
			
			while(!exit) {
				
				String line = sc.nextLine();
				
				if(!line.contentEquals("stop")) {
					//Normal line to read.
					out.writeBytes(line+'\n');
				} else {
					//No more line to read.
					line = ":::END:::\n";
					System.out.println("End of writing. Sending to the server.");
					out.writeBytes(line);
					exit = true;
					sc.close();
				}
				
			}
			
			//Printing the server response.
			String response = in.readLine();
			System.out.println("\nServer reponse : "+response);
			
		} catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
			System.err.println("Couldn't connect. Shutting down.");
		}
	}
	
}

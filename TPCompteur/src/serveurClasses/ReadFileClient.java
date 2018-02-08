package serveurClasses;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class ReadFileClient {

	public static void main(String[] args) {
		
		System.out.println("ReadFileClient launching.");
		Socket as = null;
		DataOutputStream out = null;
		BufferedReader in = null;
		Boolean exit = false;
		
		File f = null;
		FileInputStream fis = null;
		BufferedReader reader = null;
		
		//Attempts to read the file it was given in parameter.
		try {
			f = new File(args[1]);
			
			//Creates InputStream and BufferedReader for the File.
			fis = new FileInputStream(f);
			reader = new BufferedReader(new InputStreamReader(fis));
		} catch (ArrayIndexOutOfBoundsException | NullPointerException | FileNotFoundException e) {
			System.err.println("Couldn't read, unreachable file.");
		}
		
		try {
			//Connecting to the server
			as = new Socket(InetAddress.getLocalHost(), Integer.parseInt(args[0]));
			System.out.println("Connected to the server.");
			
			//Dialogs with server.
			out = new DataOutputStream(as.getOutputStream());
			in = new BufferedReader(new InputStreamReader(as.getInputStream()));
			
			//Starts reading the file
			System.out.println("Reading file : "+f.getName());
			
			while(!exit) {
				
				String line = reader.readLine();
				
				if(line != null) {
					//Normal line to read.
					out.writeBytes(line+'\n');
				} else {
					//No more line to read.
					line = ":::END:::\n";
					out.writeBytes(line);
					exit = true;
				}
				
				System.out.println("iter : "+line);
				
			}
			
			//Printing the server response.
			String response = in.readLine();
			System.out.println("\nServer reponse : "+response);
			
		} catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
			System.err.println("Couldn't connect. Shutting down.");
		}
	}
	
}

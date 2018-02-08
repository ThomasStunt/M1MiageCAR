package serveurClasses;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import compteurParaClasses.CompteurMultiThread;

public class CompteurServeur {

	public static void main(String[] args) {
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
				
				as = ps.accept();
				System.out.println("Client connected.");
				in = new BufferedReader(new InputStreamReader(as.getInputStream()));
				out = new DataOutputStream(as.getOutputStream());

				try {
					String totalLine = "";
					int numberOfLines = 0;
					
					String read = new String();
					while(!(read = in.readLine()).contentEquals(":::END:::")) {
						numberOfLines++;
						System.out.println("iter : "+read);
						totalLine = totalLine.concat(read+'\n');
					}
					
					System.err.println("total line : "+totalLine);
					
					CompteurMultiThread cmt = new CompteurMultiThread(nThread, totalLine, numberOfLines);
					
					out.writeBytes("Most occured word : "+cmt.getMostOccuredWord()+'\n');
					
				} catch (SocketException | InterruptedException e) {
					e.printStackTrace();
				}
				
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

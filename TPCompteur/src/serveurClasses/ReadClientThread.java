package serveurClasses;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import compteurParaClasses.CompteurMultiThread;

/**
 * This thread will be created by the server. It is responsible for handling the treatment
 * of a file sent by a client and will send him back the most occured word. This allows the
 * server to treat multiple client simultaneously.
 * @author Thomas Perrier.
 */

public class ReadClientThread implements Runnable {

	Socket sock = null;
	
	BufferedReader in = null;
	DataOutputStream out = null;
	
	Integer nThread = null;
	
	Thread t = null;
	
	public ReadClientThread(Socket socket, Integer nThread) {
		this.sock = socket;
		this.nThread = nThread;
		
		t = new Thread(this);
		t.start();
	}

	public void run() {
		try {
			//Dialogs with the client.
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = new DataOutputStream(sock.getOutputStream());
			
			//Reading the lines received from the File.
			String totalLine = "";
			int numberOfLines = 0;
			
			String read = new String();
			while(!(read = in.readLine()).contentEquals(":::END:::")) {
				numberOfLines++;
				totalLine = totalLine.concat(read+" \n");
			}
			
			//Creates a CompteurMultiThread to treat the sentence.
			CompteurMultiThread cmt = new CompteurMultiThread(nThread, totalLine, numberOfLines);
			
			//Once the sentence is treated, sends back to the client the most occured word.
			out.writeBytes("Most occured word : "+cmt.getMostOccuredWord()+'\n');
			
			//Close the socket once the answer is sent.
			sock.close();
			
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}

}

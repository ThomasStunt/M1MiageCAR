package serveurClasses;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import compteurParaClasses.CompteurMultiThread;

public class ClientThread implements Runnable {

	Socket sock = null;
	BufferedReader in = null;
	DataOutputStream out = null;
	Integer nThread = null;
	CompteurServeur cs = null;
	
	Thread t = null;
	
	public ClientThread(Socket socket, CompteurServeur cs, Integer nThread) {
		this.sock = socket;
		this.cs = cs;
		this.nThread = nThread;
		
		t = new Thread(this);
		t.start();
	}

	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = new DataOutputStream(sock.getOutputStream());
			
			String totalLine = "";
			int numberOfLines = 0;
			
			String read = new String();
			while(!(read = in.readLine()).contentEquals(":::END:::")) {
				numberOfLines++;
				totalLine = totalLine.concat(read+'\n');
			}
			
			CompteurMultiThread cmt = new CompteurMultiThread(nThread, totalLine, numberOfLines);
			
			out.writeBytes("Most occured word : "+cmt.getMostOccuredWord()+'\n');
			
			sock.close();
			
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}

}

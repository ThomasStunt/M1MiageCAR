package classes;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CompteurMultiThread {

	private int nThread;
	private List <CountingThread> threads;
	private File inputFile;
	private int nbLinesToRead;
	private int nbLinesLeft;
	
	public CompteurMultiThread(int nThread, File f) throws IOException {
		this.nThread = nThread;
		threads = new ArrayList<CountingThread>();
		this.inputFile = f;
		int lines = countLines();
		nbLinesToRead = lines / nThread;
		nbLinesLeft = lines % nThread;
		initThreads();
	}
	
	public synchronized void initThreads() throws IOException {
		for(int i = 0; i<nThread; i++) {
			int lineStart = 1 + i*nbLinesToRead;
			int lineEnd = lineStart + nbLinesToRead;
			if(countLines() - lineEnd < nbLinesLeft) {
				lineEnd += nbLinesLeft;
			}
			CountingThread ct = new CountingThread(lineStart, lineEnd, inputFile);
			threads.add(ct);
		}
	}
	
	public int countLines() throws IOException {
	    InputStream is = new BufferedInputStream(new FileInputStream(inputFile));
	    try {
	        byte[] c = new byte[1024];
	        int count = 0;
	        int readChars = 0;
	        boolean empty = true;
	        while ((readChars = is.read(c)) != -1) {
	            empty = false;
	            for (int i = 0; i < readChars; ++i) {
	                if (c[i] == '\n') {
	                    ++count;
	                }
	            }
	        }
	        return (count == 0 && !empty) ? 1 : count + 1;
	    } finally {
	        is.close();
	    }
	}
	
	public void getMostOccuredWord() {
		HashMap<String, Integer> words = new HashMap<String, Integer>();
		for (CountingThread ct : threads) {
			words.putAll(ct.getCs().getOccurs());
		}
		String res = "";
		for(String s : words.keySet()) {
			if(res == "")
				res = s;
			if(words.get(s) > words.get(res))
				res = s;
		}
		System.out.println("Most occured word : " + res + " with "+ words.get(res) + " occurencies");
	}
	
	public static void main(String[] args) {
		CompteurMultiThread cmt;
		try {
			try {
				cmt = new CompteurMultiThread(Integer.parseInt(args[0]), null);
			} catch (NullPointerException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
				System.out.println("Cannot read value, default is 4");
				cmt = new CompteurMultiThread(4, new File("test.txt"));
				cmt.getMostOccuredWord();
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}

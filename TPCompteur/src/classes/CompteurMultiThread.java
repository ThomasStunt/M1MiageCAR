package classes;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
		
		//Total number of lines.
		int lines = countLines();
		//Number of lines to read per thread.
		nbLinesToRead = lines / nThread;
		//Number of lines left after the repartition of lines. (will be sent to the last thread).
		nbLinesLeft = lines % nThread;
		
		initThreads();
	}
	
	/**
	 * Given the number of threads available, initiates threads with the lines they have to read and analyze.
	 * @throws IOException
	 */
	public void initThreads() {
		try {
			for(int i = 0; i<nThread; i++) {
				
				int lineStart = 1 + i*nbLinesToRead;
				int lineEnd = lineStart + nbLinesToRead;
				
				if(countLines() - lineEnd < nbLinesLeft) {
					lineEnd += nbLinesLeft;
				}
				
				CountingThread ct = new CountingThread(lineStart, lineEnd, inputFile);
				threads.add(ct);
			}
		} catch (IOException e) {
			System.out.println("Cannot read file");
		}
	}
	
	/**
	 * Counts the number of lines in the text to read.
	 * @return the good number of lines.
	 * @throws IOException
	 */
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
	
	/**
	 * Once the threads finished the computing, gathers every thread HashMap and adds them all together.
	 * It then looks for the most occurred word and prints it with the number of appearances in the text.
	 * @throws InterruptedException 
	 */
	public synchronized void getMostOccuredWord() throws InterruptedException {
		HashMap<String, Integer> words = new HashMap<String, Integer>();
		
		//Gathers every thread occurrences HashMap
		for (CountingThread ct : threads) {
			for(String s : ct.getCs().getOccurs().keySet())
				if(words.containsKey(s))
					words.put(s, words.get(s) + ct.getCs().getOccurs().get(s));
				else
					words.put(s, ct.getCs().getOccurs().get(s));
		}
		
		String res = "";
		
		//Looks for the most occurred word.
		for(String s : words.keySet()) {
			if(res == "")
				res = s;
			if(words.get(s) > words.get(res))
				res = s;
		}
		
		//Prints the result.
		System.out.println("Most occured word : " + res + " with "+ words.get(res) + " appearances");
	}
	
	public static void main(String[] args) throws InterruptedException {
		CompteurMultiThread cmt;
		
		try {
			try {
				cmt = new CompteurMultiThread(Integer.parseInt(args[0]), new File(args[1]));
				cmt.getMostOccuredWord();
			} catch (NullPointerException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
				System.out.println("Cannot read value for number of threads or the file.");
				System.out.println("Lauching with 4 threads and the file test.txt");
				cmt = new CompteurMultiThread(4, new File("testbis.txt"));
				cmt.getMostOccuredWord();
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}

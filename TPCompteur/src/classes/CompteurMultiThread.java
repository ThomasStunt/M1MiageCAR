package classes;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CompteurMultiThread {

	private int nThread;
	private List <CountingThread> threads;
	private File inputFile;
	private int nbLinesToRead;
	private int nbLinesLeft;
	
	public CompteurMultiThread(int nThread, File f) throws IOException {
		this.nThread = nThread;
		this.inputFile = f;
		int lines = countLines();
		nbLinesToRead = lines / nThread;
		nbLinesLeft = lines % nThread;
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
	        return (count == 0 && !empty) ? 1 : count;
	    } finally {
	        is.close();
	    }
	}
	
	public static void main(String[] args) {
		CompteurMultiThread cmt;
		try {
			try {
				cmt = new CompteurMultiThread(Integer.parseInt(args[0]), null);
			} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
				System.out.println("Cannot read value, default is 4");
				cmt = new CompteurMultiThread(4, null);
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}

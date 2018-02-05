package classes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.stream.*;

/**
 * This thread will allow the user to treat selected lines from the CompteurMultiThread class.
 * It will read the lines it was given and will compute the appearances of each word he can find thanks to
 * his CompteurSeq attribute.
 * @author Thomas Perrier.
 */

public class CountingThread extends Thread {

	private int firstLine, lastLine;
	private File inputFile;
	private CompteurSeq cs;
	
	public CountingThread(int fLine, int lLine, File iFile) throws IOException {
		System.out.println("start of thread "+getId());
		this.firstLine = fLine;
		this.lastLine = lLine;
		this.inputFile = iFile;
		cs = new CompteurSeq(readLines());
		cs.countWords();
		try {
			Thread.sleep(1000);
			System.out.println("end of thread "+getId());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads the lines it was given and builds a String to send to the CompteurSeq.
	 * @return the full String of the lines to analyze.
	 * @throws IOException
	 */
	public synchronized String readLines() throws IOException {
		String res = "";
		for(int i = firstLine; i < lastLine; i++) {
			try (Stream <String> lines = Files.lines(Paths.get(inputFile.getPath()))) {
		    	String line = lines.skip(i-1).findFirst().get();
		    	res = res.concat(line);
			} catch (IllegalStateException | NoSuchElementException e) {
				e.printStackTrace();
			}
		}
		System.out.println("String to analyze : "+res);
		return res;
	}
	
	public int getFirstLine() {
		return firstLine;
	}

	public void setFirstLine(int firstLine) {
		this.firstLine = firstLine;
	}

	public int getLastLine() {
		return lastLine;
	}

	public void setLastLine(int lastLine) {
		this.lastLine = lastLine;
	}

	public File getInputFile() {
		return inputFile;
	}

	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}

	public CompteurSeq getCs() {
		return cs;
	}

	public void setCs(CompteurSeq cs) {
		this.cs = cs;
	}

	@Override
	public void run(){
		super.run();
	}
	
}

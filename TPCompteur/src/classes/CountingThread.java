package classes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.*;

public class CountingThread extends Thread {

	private int firstLine, lastLine;
	private File inputFile;
	private CompteurSeq cs;
	
	public CountingThread(int fLine, int lLine, File iFile) throws IOException {
		this.firstLine = fLine;
		this.lastLine = lLine;
		this.inputFile = iFile;
		System.out.println("\tNew Thread ! From line "+firstLine+" to line "+lastLine);
		cs = new CompteurSeq(readLines());
		System.out.println(cs.getSentence());
	}
	
	public synchronized String readLines() throws IOException {
		String res = "";
		Stream <String> lines = Files.lines(Paths.get(inputFile.getPath()));
		try {
			for(int i = firstLine; i <= lastLine; i++) {
				System.out.println(i);
		    	String line = lines.skip(i-1).findFirst().get();
		    	res = res.concat(line);
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		lines.close();
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

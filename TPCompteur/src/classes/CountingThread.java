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
		cs = new CompteurSeq(readLines());
		cs.countWords();
	}
	
	public String readLines() throws IOException {
		String res = "";
		for(int i = firstLine; i < lastLine; i++) {
			try (Stream <String> lines = Files.lines(Paths.get(inputFile.getPath()))) {
		    	String line = lines.skip(i-1).findFirst().get();
		    	res = res.concat(line);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
		}
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

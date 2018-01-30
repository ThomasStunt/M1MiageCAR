package classes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class CountingThread extends Thread {

	private int firstLine, lastLine;
	private File inputFile;
	private CompteurSeq cs;
	
	public CountingThread(int fLine, int lLine, File iFile) throws IOException {
		this.firstLine = fLine;
		this.lastLine = lLine;
		this.inputFile = iFile;
		cs = new CompteurSeq(readLines());
	}
	
	public String readLines() throws IOException {
		String res = "";
		try (Stream<String> lines = Files.lines(Paths.get(inputFile.getPath()))) {
			for(int i = firstLine; i <= lastLine; i++) {
		    	String line = lines.skip(i - 1).findFirst().get();
		    	res = res.concat(line);
			}
		}
		return res;
	}
	
	@Override
	public void run(){
		super.run();
	}
	
}

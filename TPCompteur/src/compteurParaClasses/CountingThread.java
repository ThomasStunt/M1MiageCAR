package compteurParaClasses;

/**
 * This thread will allow the user to treat selected lines from the CompteurMultiThread class.
 * It will read the lines it was given and will compute the appearances of each word he can find thanks to
 * his CompteurSeq attribute.
 * @author Thomas Perrier.
 */

public class CountingThread extends Thread {

	private CompteurSeq cs;
	
	public CountingThread(String line) {
		System.out.println("start of thread "+getId());
		System.out.println("line to analyze : "+line);
		cs = new CompteurSeq(line);
		cs.countWords();
		try {
			Thread.sleep(1000);
			System.out.println("end of thread "+getId());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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

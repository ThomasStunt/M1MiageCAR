package classes;

public class CountingThread extends Thread {

	private CompteurSeq cs;
	
	public CountingThread(String s) {
		cs = new CompteurSeq(s);
	}
	
	@Override
	public void run() {
		super.run();
	}
	
}

package classes;
import java.util.ArrayList;
import java.util.HashMap;

public class CompteurSeq {

	private ArrayList<String> words;
	private HashMap<String, Integer> occurs;
	private String[] result;
	private String sentence;
	
	public CompteurSeq(String sentence) {
		//Initiates the class with a sentence to analyze.
		this.sentence = sentence.toLowerCase();
		words = new ArrayList<String>();
		occurs = new HashMap<String, Integer>();
		
		//Gathers every word in the sentence.
		result = this.sentence.split("(?=[,.])|\\s+");
		
		//Builds the list of words in the sentence
		for(String s : result) {
			words.add(s);
		}
	}
	 
	/**
	 * Counts every time a word appears in the sentence
	 */
	public void countWords() {
		for(String s : words) {
			if(s.contentEquals(".") || s.contentEquals(","))
				continue;
			else {
				int count = 1;
				if(occurs.containsKey(s))
					count = occurs.get(s)+1;
				occurs.put(s, count);
			}
		}
	}
	
	/**
	 * According to the HashMap, gets the most occured word.
	 * @return the word in the HashMap with the most appearances.
	 */
	public String mostOccuredWord() {
		String res = result[0];
		for(String s : occurs.keySet()) {
			if(occurs.get(s) > occurs.get(res))
				res = s;
		}
		return res;
	}
	
	public static void main(String[] args) {
		CompteurSeq cs = new CompteurSeq("salut bonjour, bonjour. hihi");
		cs.countWords();
		System.out.println(cs);
		System.out.println(cs.mostOccuredWord());
	}
	
	@Override
	public String toString() {
		String res = "";
		for(String s : occurs.keySet()) {
			res += "Key : "+s+" -> "+String.valueOf(occurs.get(s))+"\n";
		}
		return res;
	}
	
	/**
	 * Whenever you change the sentence, resets the CompteurSeq to rebuild the list of words.
	 */
	public void reset() {
		words = new ArrayList<String>();
		occurs = new HashMap<String, Integer>();
		
		result = this.sentence.split("\\s");
		
		for(String s : result) {
			words.add(s);
		}
		
		this.countWords();
	}
	
	public void setSentence(String sentence) {
		this.sentence = sentence;
		this.reset();
	}

	public ArrayList<String> getWords() {
		return words;
	}

	public void setWords(ArrayList<String> words) {
		this.words = words;
	}

	public HashMap<String, Integer> getOccurs() {
		return occurs;
	}

	public void setOccurs(HashMap<String, Integer> occurs) {
		this.occurs = occurs;
	}

	public String[] getResult() {
		return result;
	}

	public void setResult(String[] result) {
		this.result = result;
	}

	public String getSentence() {
		return sentence;
	}
	
}

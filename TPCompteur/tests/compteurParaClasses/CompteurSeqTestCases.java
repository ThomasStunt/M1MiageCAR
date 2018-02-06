package compteurParaClasses;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import compteurParaClasses.CompteurSeq;

public class CompteurSeqTestCases {

	CompteurSeq cs;
	
	@Before
	public void before() {
		cs = new CompteurSeq("I test this shit");
	}
	
	@Test
	public void testGoodNumberOfWords() {
		cs.countWords();
		assertEquals(cs.getOccurs().size(), 4);
		for(String s : cs.getOccurs().keySet()) {
			assertEquals(cs.getOccurs().get(s), new Integer(1));
		}
	}
	
	@Test
	public void testMostOccuredWord() {
		cs.setSentence("I I test this shit");
		assertEquals(cs.mostOccuredWord(), "I");
	}
	
	@Test
	public void testIfSentenceIsModified() {
		assertEquals(cs.mostOccuredWord(), "I");
		cs.setSentence("I test this this shit");
		assertEquals(cs.getOccurs().get("this"), new Integer(2));
		assertEquals(cs.mostOccuredWord(), "this");
	}
}

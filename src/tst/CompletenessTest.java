package tst;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import app.FieldsCompletenessAnalyser;

public class CompletenessTest {
	
	FieldsCompletenessAnalyser analyser;
	
	@Before
	public void setup() {
		analyser = new FieldsCompletenessAnalyser();
	}
	

	@Test
	public void testFieldsCompleteness() {
		analyser.createField("Nome", "João");
		analyser.createField("Idade", "21");
		
		assertTrue(analyser.analyse());
	}

}

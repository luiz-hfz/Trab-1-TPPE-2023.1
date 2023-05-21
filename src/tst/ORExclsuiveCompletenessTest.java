package tst;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import app.FieldsCompletenessAnalyser;

public class ORExclsuiveCompletenessTest {
	
	FieldsCompletenessAnalyser person, address;
	
	@Before
	public void setup() {
		person = new FieldsCompletenessAnalyser();
		address = new FieldsCompletenessAnalyser();
	}
	
	@Test
	public void testTrueCompletenessAtomicFields() {
		person.createField("Nome", "João");
		person.createField("Idade", "");
		
		assertFalse(person.isComplete());
	}
	
	@Test
	public void testFalseCompletenessAtomicFields() {
		person.createField("Nome", "João");
		person.createField("Idade", "21");
		
		assertTrue(person.isComplete());
	}
	
	@Test
	public void testTrueCompletenessCompoundField() {
		address.createField("Bairro", "");
		address.createField("Rua", "Julio Cesar");
		address.createField("Numero", "");
		
		person.createField("Nome", "");
		person.createField("Idade", "");
		person.createField("Endereço", address);
		
		assertTrue(person.isComplete());
	}
	
	@Test
	public void testFalseCompletenessCompoundField() {
		address.createField("Bairro", "");
		address.createField("Rua", "Julio Cesar");
		address.createField("Numero", "");
		
		person.createField("Nome", "");
		person.createField("Idade", "21");
		person.createField("Endereço", address);
		
		assertFalse(person.isComplete());
	}

}

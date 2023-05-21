package tst;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import app.FieldsCompletenessAnalyser;

@RunWith(Parameterized.class)
public class ORExclsuiveCompletenessTest {
	
	private FieldsCompletenessAnalyser analyser;
	private Object[][] fields;
	private boolean isComplete;

	public ORExclsuiveCompletenessTest(Object[][] fields, boolean isComplete) {
		this.fields = fields;
		this.isComplete = isComplete;
	}
	
	@Parameters
	public static Collection<Object[]> getParameters(){
		Object[][] parameters = new Object[][] {

			// true, only atomic fields
			{new Object[][] {
				{"Nome", "João"},
				{"Idade", ""},
				{"Emprego", ""},
			}, true},

			// false, only nested fields
			{new Object[][] {
				{"Nome", "João"},
				{"Idade", "21"},
				{"Emprego", ""},
			}, false},

			// true, with nested fields
			{new Object[][] {
				{"Nome", "João"},
				{"Idade", ""},
				{"Emprego", ""},
				{"Endereço", new Object[][] {
					{"Rua", ""},
					{"Cidade", ""},
					{"CEP", ""}
				}}
			}, true},

			// false, with nested fields
			{new Object[][] {
				{"Nome", "João"},
				{"Idade", ""},
				{"Emprego", ""},
				{"Endereço", new Object[][] {
					{"Rua", ""},
					{"Cidade", "Brasília"},
					{"CEP", ""}
				}}
			}, false},
		};
		return Arrays.asList(parameters);
	}
	
	@Before
	public void setup() {
		analyser = new FieldsCompletenessAnalyser(fields);
	}

	@Test
	public void testeOrExclusivo() {
		assertEquals(analyser.isComplete(), isComplete);
	}

}

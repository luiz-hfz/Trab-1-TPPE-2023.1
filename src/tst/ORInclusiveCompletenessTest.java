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
public class ORInclusiveCompletenessTest {
	
	app.FieldsCompletenessAnalyser analyser;
	private Object[][] fields;
	private boolean isComplete;
	
	public ORInclusiveCompletenessTest(Object[][] fields, boolean isComplete) {
		this.fields = fields;
		this.isComplete = isComplete;
	}
	
	@Before
	public void setup() {
		analyser = new FieldsCompletenessAnalyser(fields);
	}
	
	@Parameters
	public static Collection<Object[]> getParameters(){
		Object[][] parameters = new Object[][] {
			{new Object[][] {
				{"Nome", ""},
				{"Idade", ""},
				{"Emprego", ""},
			}, false},
			{new Object[][] {
				{"Nome", "João"},
				{"Idade", "21"},
				{"Emprego", ""},
			}, true},
			{new Object[][] {
				{"Nome", ""},
				{"Idade", "21"},
				{"Emprego", ""},
				{"Endereço", new Object[][] {
					{"Rua", ""},
					{"Cidade", "Brasilia"},
					{"CEP", "71900100"}
				}}
			}, true},
			{new Object[][] {
				{"Nome", ""},
				{"Idade", ""},
				{"Emprego", ""},
				{"Endereço", new Object[][] {
					{"Rua", ""},
					{"Cidade", ""},
					{"CEP", ""}
				}}
			}, false},
		};
		return Arrays.asList(parameters);
	}
	
	@Test
	public void testeORInclusivo() {
		assertEquals(analyser.isInclusiveComplete(), isComplete);
	}
	
}

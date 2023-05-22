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
public class CompletenessCalculationTest {
	private FieldsCompletenessAnalyser person;
	private Object[][] fields;
	private double result;

	public CompletenessCalculationTest(Object[][] fields, double result) {
		this.fields = fields;
		this.result = result;
	}
	
	@Parameters
	public static Collection<Object[]> getParameters(){
		Object[][] parameters = new Object[][] {
			{new Object[][] {
				{"Nome", "Joao"},
	            {"Idade", "21"}
			}, 100.0},
			{new Object[][] {
				{"Nome", "João"},
				{"Idade", ""},
			}, 50.0},
			{new Object[][] {
				{"Nome", "João"},
				{"Idade", ""},
				{"Emprego", ""},
				{"Endereço", new Object[][] {
					{"Rua", ""},
					{"Cidade", "Brasilia"},
					{"CEP", ""}
				}}
			}, 33.33},
			{new Object[][] {
				{"Nome", "João"},
				{"Idade", ""},
				{"Emprego", ""},
				{"Endereço", new Object[][] {
					{"Rua", "ggg"},
					{"Cidade", "jjj"},
					{"CEP", "jjj"}
				}}
			}, 50.0},
		};
		return Arrays.asList(parameters);
	}
	
	@Before
	public void setup() {
		person = new FieldsCompletenessAnalyser(fields);
	}

	@Test
	public void testeOrExclusivo() {
		assertEquals(result, person.calculateCompleteness(), 0.01);
	}
}

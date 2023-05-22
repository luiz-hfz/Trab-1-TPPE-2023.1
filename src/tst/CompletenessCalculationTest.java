package tst;

import static org.junit.Assert.*;

import org.junit.Test;

import app.FieldsCompletenessAnalyser;

public class CompletenessCalculationTest {
	FieldsCompletenessAnalyser person;
	
	@Test
    public void testCalculateCompletenessWithCompleteFields() {
        Object[][] fields = {
            {"Nome", "Joao"},
            {"Idade", "21"}
        };
        
        FieldsCompletenessAnalyser person = new FieldsCompletenessAnalyser(fields);
        double result = person.calculateCompleteness();
        assertEquals(100, result, 0.01);
    }

}
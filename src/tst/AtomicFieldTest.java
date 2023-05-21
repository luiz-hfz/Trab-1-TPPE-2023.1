package tst;

import static org.junit.Assert.*;

import org.junit.Test;

import app.AtomicField;
import app.CompositeField;
import app.Field;

public class AtomicFieldTest {
    @Test
    public void atomicFieldIsComplete() {
        Field field = new AtomicField("CPF", "12345678910");
        assertTrue(field.isComplete());
    }

    @Test
    public void emptyAtomicFieldIsNotComplete() {
        Field field = new AtomicField("CPF", "");
        assertFalse(field.isComplete());
    }

    @Test
    public void compositeFieldIsComplete() {
        CompositeField nome = new CompositeField("Nome");
        nome.addChild(new AtomicField("PrimeiroNome", "João"));
        nome.addChild(new AtomicField("UltimoNome", "Silva"));
        assertTrue(nome.isComplete());
    }


    @Test
    public void incompleteCompositeFieldIsNotComplete() {
    	CompositeField nome = new CompositeField("Nome");
        nome.addChild(new AtomicField("PrimeiroNome", "João"));
        nome.addChild(new AtomicField("UltimoNome", ""));
        assertFalse(nome.isComplete());
    }
}


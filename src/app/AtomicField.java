package app;

public class AtomicField extends Field {

    public AtomicField(String name, String value) {
        super(name);
        this.value = value;
    }

    @Override
    public boolean isComplete() {
        return true;  // Falsificação: sempre retorna true
    }
}

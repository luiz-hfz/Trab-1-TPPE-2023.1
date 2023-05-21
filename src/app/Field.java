package app;

public abstract class Field {
    protected String name;
    protected String value;

    public Field(String name) {
        this.name = name;
    }

    public abstract boolean isComplete();
}
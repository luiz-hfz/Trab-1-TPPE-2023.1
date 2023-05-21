package app;

import java.util.List;
import java.util.ArrayList;

public class CompositeField extends Field {
    private List<Field> children;

    public CompositeField(String name) {
        super(name);
        children = new ArrayList<>();
    }

    public void addChild(Field field) {
        children.add(field);
    }

    @Override
    public boolean isComplete() {
        for (Field child : children) {
            if (!child.isComplete()) {
                return false;  // Se algum dos campos filhos não está completo, retorna false
            }
        }
        return true;  // Retorna true se todos os campos filhos estão completos
    }
}

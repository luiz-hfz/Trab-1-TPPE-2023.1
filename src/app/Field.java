package app;

import java.util.HashMap;
import java.util.Map;

public class Field {
    private Map<String, String> camposFilhos;

    public Field() {
        this.camposFilhos = new HashMap<>();
    }

    public void createField(String key, String value) {
        camposFilhos.put(key, value);
    }
}

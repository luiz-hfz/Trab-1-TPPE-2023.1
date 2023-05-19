package app;

import java.util.HashMap;
import java.util.Map;

public class FieldsCompletenessAnalyser {
	private Map<String, String> fields = new HashMap<>();

	public void createField(String key, String value) {
		fields.put(key, value);
	}
	
	public void printFields() {
		for (Map.Entry<String, String> pair : fields.entrySet()) {
		    System.out.println(String.format("Key: %s, Value is : %s", pair.getKey(), pair.getValue())); 
		}
	}

	public boolean analyse() {
		for (Map.Entry<String, String> pair : fields.entrySet()) {
		    if(pair.getValue().isEmpty()) return false;
		}
		
		return true;
	}

}

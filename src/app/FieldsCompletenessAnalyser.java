package app;

import java.util.HashMap;
import java.util.Map;

public class FieldsCompletenessAnalyser {
	private Map<String, Object> fields = new HashMap<>();

	public void createField(String key, Object value) {
		fields.put(key, value);
	}
	
	public void printFields() {
		for (Map.Entry<String, Object> pair : fields.entrySet()) {
		    System.out.println(String.format("Key: %s, Value is : %s", pair.getKey(), pair.getValue())); 
		}
	}
	
	public int analyse() {
		int completeFieldCount = 0;
		
		for (Map.Entry<String, Object> pair : fields.entrySet()) {
			Object value = pair.getValue();
			
			if(value instanceof FieldsCompletenessAnalyser) {
				FieldsCompletenessAnalyser child = (FieldsCompletenessAnalyser) value;
				completeFieldCount += child.analyse();
			}
			else {
				if (!((String) value).isEmpty()) completeFieldCount += 1;
			}
		}
		
		return completeFieldCount;
	}

	public boolean isComplete() {
		if(this.analyse() == 1) return true;
		
		return false;

	}
}

package app;

import java.util.HashMap;
import java.util.Map;

public class FieldsCompletenessAnalyser {
	private Map<String, Object> fields = new HashMap<>();

	public FieldsCompletenessAnalyser(Object[][] fields) {
		for (Object[] field : fields) {
			String key = (String) field[0];
			Object value = field[1];

			if (value instanceof Object[][]) {
				Object[][] nestedField = (Object[][]) value;
				FieldsCompletenessAnalyser child = new FieldsCompletenessAnalyser(nestedField);

				this.createField(key, child);
			}

			else
				this.createField(key, value);
		}
	}

	public void createField(String key, Object value) {
		fields.put(key, value);
	}

	public int analyse() {
		int completeFieldCount = 0;

		for (Map.Entry<String, Object> pair : fields.entrySet()) {
			Object value = pair.getValue();

			if (value instanceof FieldsCompletenessAnalyser) {
				FieldsCompletenessAnalyser child = (FieldsCompletenessAnalyser) value;
				completeFieldCount += child.analyse();
			} else {
				if (!((String) value).isEmpty())
					completeFieldCount += 1;
			}
		}

		return completeFieldCount;
	}

	public boolean isExclusiveComplete() {
		if (this.analyse() == 1)
			return true;

		return false;
	}

	public boolean isInclusiveComplete() {
		if (this.analyse() >= 1)
			return true;

		return false;
	}

	public double calculateCompleteness() {
		return 100.0;
	}
}

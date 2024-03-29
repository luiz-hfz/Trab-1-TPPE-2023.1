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

	public int analyseExclusive() {
		int completeFieldCount = 0;

		for (Map.Entry<String, Object> pair : fields.entrySet()) {
			Object value = pair.getValue();

			if (value instanceof FieldsCompletenessAnalyser) {
				FieldsCompletenessAnalyser child = (FieldsCompletenessAnalyser) value;
				if(child.isExclusiveComplete())
					completeFieldCount += 1;
			} else {
				if (!((String) value).isEmpty())
					completeFieldCount += 1;
			}
		}

		return completeFieldCount;
	}
	
	public int analyseInclusive() {
		int completeFieldCount = 0;

		for (Map.Entry<String, Object> pair : fields.entrySet()) {
			Object value = pair.getValue();

			if (value instanceof FieldsCompletenessAnalyser) {
				FieldsCompletenessAnalyser child = (FieldsCompletenessAnalyser) value;
				if(child.isInclusiveComplete())
					completeFieldCount += 1;
			} else {
				if (!((String) value).isEmpty())
					completeFieldCount += 1;
			}
		}

		return completeFieldCount;
	}

	public boolean isExclusiveComplete() {
		float result = this.analyseExclusive();

		if (result == 1)
			return true;

		return false;
	}

	public boolean isInclusiveComplete() {
		float result = this.analyseInclusive();

		if (result >= 1)
			return true;

		return false;
	}
	
	public double calculateCompleteness() {
        double result = 0;
        int numOfFields = fields.size();

        for (Map.Entry<String, Object> pair : fields.entrySet()) {
            Object value = pair.getValue();

            if (value instanceof FieldsCompletenessAnalyser) {
                FieldsCompletenessAnalyser child = (FieldsCompletenessAnalyser) value;
                result += (child.calculateCompleteness() * ((double) 1 / numOfFields));
            } else {
                if (!((String) value).isEmpty())
                    result += ((double) 1 / numOfFields) * 100;
            }
        }

        return result;
    }
}

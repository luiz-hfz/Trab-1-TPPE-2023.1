package app;

public class FieldsCompletenessAnalyser {
	private Field fields = new Field();

	public void createField(String key, String value) {
		fields.createField(key, value);
	}

	public boolean analyse() {
		return true;
	}

}

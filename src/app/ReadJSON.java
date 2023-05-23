package app;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.*;
import org.json.simple.parser.*;

public class ReadJSON {
    public FieldsCompletenessAnalyser getValuesFromJSON() {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("exemplo.json")) {
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            
            FieldsCompletenessAnalyser array = jsonObjectToArray(jsonObject);
            

            return array;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
		return null;
    }

    public static FieldsCompletenessAnalyser jsonObjectToArray(JSONObject jsonObject) {
        FieldsCompletenessAnalyser array = new FieldsCompletenessAnalyser(null);

        for (Object key : jsonObject.keySet()) {
            String chave = (String) key;
            Object valor = jsonObject.get(chave);

            if (valor instanceof JSONObject) {
                valor = jsonObjectToArray((JSONObject) valor);
            }
            
            array.createField(chave, valor);
        }

        return array;
    }
}

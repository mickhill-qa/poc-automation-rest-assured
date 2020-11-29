package baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.json.JSONObject;
import org.json.JSONTokener;

import cucumber.api.Scenario;

public class BaseSteps {

	public static Scenario scenario;

	public static void attachJsonInReport(String _json) {

		try {
			// Identando o JSON
			_json = new JSONObject(_json).toString(2);

			scenario.write(_json);
		} catch (ClassCastException cce) {
			cce.printStackTrace();
		}
	}
	
	public static JSONObject loadJsonApiContracts(String _nameFileSchemaJson) throws FileNotFoundException {
		
		InputStream inputStream = new FileInputStream(
				new File("src/test/resources/jsons/apiContracts/" + _nameFileSchemaJson));
		
		return new JSONObject(new JSONTokener(inputStream));
	}

	public static JSONObject loadJsonRequestBody(String _nameFileSchemaJson) throws FileNotFoundException {

		InputStream inputStream = new FileInputStream(
				new File("src/test/resources/jsons/requestBody/" + _nameFileSchemaJson));

		return new JSONObject(new JSONTokener(inputStream));
	}
}

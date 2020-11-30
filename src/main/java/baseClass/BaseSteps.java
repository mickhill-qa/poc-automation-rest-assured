package baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Assert;

import cucumber.api.Scenario;

public class BaseSteps {

	public static Scenario scenario;

	public static void attachStringInCucumberReport(String _string) {

		try {
			scenario.write(_string);
		} catch (ClassCastException cce) {
			cce.printStackTrace();
		}
	}
	
	public static void attachJsonInCucumberReport(String _json) {
		
		_json = new JSONObject(_json).toString(2);
		attachStringInCucumberReport(_json);
		
	}

	public static JSONObject loadJson(String _nameFile) throws FileNotFoundException {

		InputStream inputStream = new FileInputStream(
				new File("src/test/resources/jsons/" + _nameFile));

		return new JSONObject(new JSONTokener(inputStream));
	}
	
	public static void assertJsonEqualsSchema(JSONObject _json, JSONObject _schema) {
		
	    try {
	    	Schema schema = SchemaLoader.load(_schema);
	    	schema.validate(_json);
		} 
	    catch (ValidationException e) {
	    	Assert.fail(e.getErrorMessage() + " - " + e.getAllMessages());
		}
	}
}

package baseClass;

import org.json.JSONObject;

import cucumber.api.Scenario;

public class BaseSteps {
	
	public static Scenario scenario;

	
	public static void attachJsonInReport(String _json) {
		
		try 
		{
			// Identando o JSON
			_json = new JSONObject(_json).toString(2);
			
			scenario.write(_json);
		} 
		catch (ClassCastException cce) 
		{
			cce.printStackTrace();
		}
	}
}

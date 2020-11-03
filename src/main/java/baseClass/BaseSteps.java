package baseClass;

import cucumber.api.Scenario;

public class BaseSteps {
	
	public static Scenario scenario;

	
	public static void attachJsonInReport(String _json) {
		try 
		{
			scenario.write(_json);
		} 
		catch (ClassCastException cce) 
		{
			cce.printStackTrace();
		}
	}
}

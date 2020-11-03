package stepDefinitions;

import baseClass.BaseSteps;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

	@Before
	public void BeforeAllTest(Scenario _scenario) throws Throwable {
		BaseSteps.scenario = _scenario;
	}

	@After
	public void AfterAllTest() {
	}
}

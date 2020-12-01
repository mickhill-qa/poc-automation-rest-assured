package baseClass;

import io.restassured.RestAssured;

public class BaseAPI {

	public static void setUrlAPI(String url) {
		RestAssured.baseURI = url;
	}
}

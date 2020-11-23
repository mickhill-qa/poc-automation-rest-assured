package endpointModels;

import baseClass.BaseAPI;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoginEndpoint extends BaseAPI {
	
	private String pathEndpoint = "/api/login";
	private Response response;
	
	public void sendRequest(String json) {
		response = RestAssured.given()
		.contentType(ContentType.JSON)
		.body(json)
		.post(pathEndpoint);
	}
	
	public Response getResponse() {
		return response;
	}
}

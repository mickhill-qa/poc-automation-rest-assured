package stepDefinitions;

import org.junit.Assert;

import baseClass.BaseSteps;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TesteAPIRestSteps extends BaseSteps {
	
	private Response response;

	@Dado("^que o usuario autentica com dados valido$")
	public void o_usuario_autentica_com_dados_valido() throws Throwable {
	}

	@E("^o endereco da API e \"([^\"]*)\"$")
	public void o_endereco_da_API_e(String _urlAPI) throws Throwable {
		
		RestAssured.baseURI = _urlAPI;
	}

	@Dado("^que o usuario seleciona o endpoint POST: \"([^\"]*)\"$")
	public void o_usuario_seleciona_o_endpoint_POST(String _endpoint) throws Throwable {
		
		RestAssured.basePath = _endpoint;
	}

	@Quando("^o usuario tenta logar com dados validos$")
	public void o_usuario_tenta_logar_com_dados_validos() throws Throwable {
		
		String validRequest = 
				"{\n" + 
				"    \"email\": \"eve.holt@reqres.in\",\n" + 
				"    \"password\": \"cityslicka\"\n" + 
				"}";
		
		response = RestAssured.given()
				.contentType(ContentType.JSON)
                .body(validRequest)
                .post();
		
		BaseSteps.attachJsonInReport(validRequest);
	}

	@Entao("^a API retorn status code (\\d+)$")
	public void a_API_retorn_status_code(int _statusCode) throws Throwable {
		
		Assert.assertEquals(_statusCode, response.getStatusCode());
	}

	@E("^a API retorna o token de acesso$")
	public void a_API_retorna_o_token_de_acesso() throws Throwable {
		
		Assert.assertNotNull(response.jsonPath().get("token"));
		BaseSteps.attachJsonInReport(response.prettyPrint());
	}
}

package stepDefinitions;

import org.json.JSONObject;
import org.junit.Assert;

import baseClass.BaseSteps;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import endpointModels.LoginEndpoint;
import io.restassured.response.Response;

public class TesteAPIRestSteps extends BaseSteps {

	/**
	 * Endpoint necessario
	 **/
	LoginEndpoint endpointLogin = new LoginEndpoint();

	
	
	/**
	 * Precondicao
	 **/
	@Dado("^que o usuario autentica com dados valido$")
	public void o_usuario_autentica_com_dados_valido() {
	}

	@E("^o endereco da API e \"([^\"]*)\"$")
	public void o_endereco_da_API_e(String _urlAPI) {
		
		endpointLogin.setUrlAPI(_urlAPI);
	}

	
	
	/**
	 * FluxoBase
	 **/
	@Dado("^que o usuario seleciona o endpoint POST")
	public void o_usuario_seleciona_o_endpoint_POST() {
	}

	@Quando("^o usuario tenta logar com dados validos$")
	public void o_usuario_tenta_logar_com_dados_validos() {
		
		JSONObject bodyJson = new JSONObject();
		bodyJson.put("email", "eve.holt@reqres.in");
		bodyJson.put("password", "cityslicka");
		
		endpointLogin.sendRequest(bodyJson.toString());
		BaseSteps.attachJsonInReport(bodyJson.toString());
	}

	@Entao("^a API retorn status code (\\d+)$")
	public void a_API_retorn_status_code(int _statusCode) {
		
		Response response = endpointLogin.getResponse();
		
		Assert.assertEquals(_statusCode, response.getStatusCode());
	}

	@E("^a API retorna o token de acesso$")
	public void a_API_retorna_o_token_de_acesso() {
		
		Response response = endpointLogin.getResponse();
		Object tokenExist = response.jsonPath().get("token");
		
		Assert.assertNotNull(tokenExist);
		BaseSteps.attachJsonInReport(response.body().asString());
	}
}

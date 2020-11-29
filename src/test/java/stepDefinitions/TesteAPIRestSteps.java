package stepDefinitions;

import java.io.FileNotFoundException;
import baseClass.BaseSteps;
import endpointModels.LoginEndpoint;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.junit.Assert;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Quando;
import io.restassured.response.Response;

public class TesteAPIRestSteps extends BaseSteps {

	/**
	 * Endpoint necessario
	 **/
	LoginEndpoint endpointLogin = new LoginEndpoint();

	
	
	/**
	 * Precondicao
	 **/
	@Dado("^que a API possui o endereco \"([^\"]*)\"$")
	public void a_API_possui_o_endereco(String _urlAPI) {
		
		endpointLogin.setUrlAPI(_urlAPI);
	}

	
	
	/**
	 * FluxoBase
	 **/
	@Quando("^o usuario tenta logar com dados validos$")
	public void o_usuario_tenta_logar_com_dados_validos() throws FileNotFoundException {
		
		JSONObject bodyJson = BaseSteps.loadJsonRequestBody("login-dados-validos.json");
		
		endpointLogin.sendRequest(bodyJson.toString());
		BaseSteps.attachJsonInReport(bodyJson.toString());
	}

	@Entao("^a API retorna status code (\\d+)$")
	public void a_API_retorna_status_code(int _statusCode) {
		
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

	@E("^a API retorna o JSON de acordo com o contrato$")
	public void a_API_retorna_o_JSON_de_acordo_com_o_contrato() throws FileNotFoundException {
		
		Response response 		= endpointLogin.getResponse();
		JSONObject jsonResponse = new JSONObject( response.body().asString() );
		
		JSONObject jsonSchema 	= BaseSteps.loadJsonApiContracts("login-schema.json");
	    Schema schema 			= SchemaLoader.load(jsonSchema);
	    schema.validate(jsonResponse);
	}
}

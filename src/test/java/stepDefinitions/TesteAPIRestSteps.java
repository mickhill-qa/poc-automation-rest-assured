package stepDefinitions;

import baseClass.BaseSteps;
import endpointModels.LoginEndpoint;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

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
		
		JSONObject bodyJson = BaseSteps.loadJson("requestBody/login-dados-validos.json");
		
		endpointLogin.sendRequest(bodyJson.toString());
		BaseSteps.attachJsonInCucumberReport(bodyJson.toString());
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
		BaseSteps.attachJsonInCucumberReport(response.body().asString());
	}
	
	@E("^a API retorna com tempo de resposta menor que (\\d+) segundos$")
	public void a_API_retorna_com_tempo_de_resposta_menor_que_segundos(int _tempoEsperadoEmSegundos) {
		
		Response response = endpointLogin.getResponse();
		long tempoDeRespostaAPI = response.timeIn(TimeUnit.SECONDS);
		
		Assert.assertTrue(tempoDeRespostaAPI < _tempoEsperadoEmSegundos);
		BaseSteps.attachStringInCucumberReport(tempoDeRespostaAPI + " segundo(s)");
	}

	@E("^a API retorna o JSON de acordo com o contrato$")
	public void a_API_retorna_o_JSON_de_acordo_com_o_contrato() throws FileNotFoundException {
		
		Response response 		= endpointLogin.getResponse();
		JSONObject jsonResponse = new JSONObject( response.body().asString() );
		JSONObject jsonSchema 	= BaseSteps.loadJson("apiContracts/login-schema.json");
		
	    BaseSteps.assertJsonEqualsSchema(jsonResponse, jsonSchema);
		BaseSteps.attachJsonInCucumberReport(jsonSchema.toString());
	}
}

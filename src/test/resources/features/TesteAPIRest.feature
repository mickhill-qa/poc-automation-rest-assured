#language: pt
#Author: Mick Hill
Funcionalidade: Teste de API Rest

	Contexto:
		Dado que a API possui o endereco "https://reqres.in"


	Cenario: Login com Usuario Valido
		Quando o usuario tenta logar com dados validos
		Entao a API retorna status code 200
		E a API retorna o token de acesso
		E a API retorna o JSON de acordo com o contrato

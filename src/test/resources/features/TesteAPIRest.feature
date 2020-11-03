#language: pt
#Author: Mick Hill
Funcionalidade: Teste de API Rest

	Contexto:
		Dado que o usuario autentica com dados valido
		E o endereco da API e "https://reqres.in"


	Cenario: Login com Usuario Valido
    Dado que o usuario seleciona o endpoint POST: "/api/login" 
    Quando o usuario tenta logar com dados validos
    Entao a API retorn status code 200
    E a API retorna o token de acesso

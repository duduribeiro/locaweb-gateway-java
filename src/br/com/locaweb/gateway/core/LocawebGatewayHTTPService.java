package br.com.locaweb.gateway.core;

import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;


class LocawebGatewayHTTPService {
	private String ENDPOINT;
	private Client client;
	LocawebGatewayHTTPService() {
		ENDPOINT = LocawebGatewayConfig.isSandbox() ? "https://api-sandbox.gatewaylocaweb.com.br/v1/transacao" : "https://api.gatewaylocaweb.com.br/v1/transacao";
		client = new Client();
	}

	public String post(String json) {
		return post(null, json);
	}

	public String post(String path, String json) {
		ClientResponse response = getWebResource(path).post(ClientResponse.class, json);
		switch (response.getStatus()) {
		//TODO criar classes de exception para cada tipo de erro
		case 401:
			throw new RuntimeException("Você não tem permissão para acessar o dado requisitado, provavelmente o token enviado está incorreto");
		case 404:
			throw new RuntimeException("A transação ou recurso desejado não existe em nossos registros.");
		case 500:
			throw new RuntimeException("Ocorreu um erro interno no sistema do gateway, entre em contato com o suporte da locaweb.");
//		case 201:
//			return response.getEntity(String.class);
//		case 400:
//			return response.getEntity(String.class);
		default:
			return response.getEntity(String.class); //200 ou 400 geram objetos validos. (400 gera objeto com o conteúdo do erro dentro da classe Transacao )
		}
	}

	public String get(String path) {
		return get(path, null);
	}
	public String get(String path, String queryParam, String queryValue) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(queryParam, queryValue);

		return get(path, params);
	}
	public String get(String path, HashMap<String, String> queryParams) {
		ClientResponse response = getWebResource(path, queryParams).get(ClientResponse.class);
		switch (response.getStatus()) {
		case 200:
			return response.getEntity(String.class);
		case 401:
			throw new RuntimeException("Você não tem permissão para acessar o dado requisitado, provavelmente o token enviado está incorreto");
		case 404:
			throw new RuntimeException("A transação ou recurso desejado não existe em nossos registros.");
		case 500:
			throw new RuntimeException("Ocorreu um erro interno no sistema do gateway, entre em contato com o suporte da locaweb.");
		default:
			break;
		}
		return "";
	}

	private Builder getWebResource(String path, HashMap<String, String> queryParams ) {
		WebResource resource;
		if(path != null) {
			resource = client.resource(getEndpoint()).path(path);
		}else {
			resource = client.resource(getEndpoint());
		}

		if(queryParams != null && !queryParams.isEmpty()) {
			Iterator<String> it = queryParams.keySet().iterator();
			while(it.hasNext()) {
				String key = it.next();
				resource = resource.queryParam(key, queryParams.get(key));
			}

		}
		return resource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
	}

//	private Builder getWebResource() {
//		return getWebResource(null,null);
//	}

	private Builder getWebResource(String path) {
		return getWebResource(path, null);
	}

	private URI getEndpoint() {
		return UriBuilder.fromUri(ENDPOINT).build();
	}

}

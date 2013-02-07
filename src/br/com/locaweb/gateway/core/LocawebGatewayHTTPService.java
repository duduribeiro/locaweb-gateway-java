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
		if(LocawebGatewayConfig.isSandbox()) {
			ENDPOINT = "https://api-sandbox.gatewaylocaweb.com.br/v1/transacao";
		}
		else {
			ENDPOINT = "https://api.gatewaylocaweb.com.br/v1/transacao";
		}
		client = new Client();
	}

	public String post(String json) {
		return post(null, json);
	}

	public String post(String path, String json) {
		ClientResponse response = getWebResource(path).post(ClientResponse.class, json);
		switch (response.getStatus()) {
//		case 201:
//			return response.getEntity(String.class);
//		case 400:
//			return response.getEntity(String.class);
		default:
			return response.getEntity(String.class);
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

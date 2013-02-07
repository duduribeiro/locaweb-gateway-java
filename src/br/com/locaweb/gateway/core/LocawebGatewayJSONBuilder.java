package br.com.locaweb.gateway.core;

import br.com.locaweb.gateway.models.Transacao;
import br.com.locaweb.gateway.utils.EnumTypeAdapterFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class LocawebGatewayJSONBuilder {

	private String token;

	@SuppressWarnings("unused")
	private TransacaoPost transacao; //usado apenas para gerar o documento json

	private Gson gson;

	protected LocawebGatewayJSONBuilder() {
		token = LocawebGatewayConfig.getToken();
		this.gson = new GsonBuilder().registerTypeAdapterFactory(new EnumTypeAdapterFactory()).create();
	}

	protected String buildJSONTransacaoPost(TransacaoPost transacao) {
		this.transacao = transacao;
		return gson.toJson(this);
	}

	protected String buildJSONTokenPost() {
		this.transacao = null;
		token = LocawebGatewayConfig.getToken();
		return gson.toJson(this);
	}

	protected TransacaoWrapper fromJSONToTransacaoWrapper(String json) {
		return gson.fromJson(json, TransacaoWrapper.class);
	}

	protected Transacao fromJSONToTransacao(String json) {
		return fromJSONToTransacaoWrapper(json).getTransacao();
	}


	public String getToken() {
		return token;
	}


}

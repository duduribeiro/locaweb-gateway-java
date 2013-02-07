package br.com.locaweb.gateway.core;

import br.com.locaweb.gateway.models.Comprador;
import br.com.locaweb.gateway.models.Pagamento;
import br.com.locaweb.gateway.models.Pedido;
import br.com.locaweb.gateway.models.Transacao;

public class LocawebGateway {
//	private String token;
	private LocawebGatewayJSONBuilder json;
	private LocawebGatewayHTTPService http;

	public LocawebGateway(String token, Boolean sandbox) {
		if(token!=null) {
			if(LocawebGatewayConfig.getToken() == null) {
				throw new RuntimeException("Token não configurado");
			}

			LocawebGatewayConfig.setToken(token);
		}
		if(sandbox != null) {
			LocawebGatewayConfig.setSandbox(sandbox);
		}
		else if(sandbox == null && LocawebGatewayConfig.isSandbox() == null ) {
			LocawebGatewayConfig.setSandbox(false);
		}
		json = new LocawebGatewayJSONBuilder();
		http = new LocawebGatewayHTTPService();
	}

	public LocawebGateway(String token) {
		this(token, Boolean.FALSE);
	}

	public LocawebGateway() {
		this(null,null);
	}

	public LocawebGateway(Boolean sandbox) {
		this(null, sandbox);
	}

	public Transacao criarTransacao(String urlRetorno, Boolean capturar, Pedido pedido, Pagamento pagamento, Comprador comprador) {
		TransacaoPost postTransacao = new TransacaoPost();
		postTransacao.urlRetorno = urlRetorno;
		postTransacao.capturar = capturar;
		postTransacao.pedido = pedido;
		postTransacao.pagamento = pagamento;
		postTransacao.comprador = comprador;

		String transacaoJson = http.post(json.buildJSONTransacaoPost(postTransacao));
		return json.fromJSONToTransacao(transacaoJson);
	}

	public Transacao consultarTransacao(Integer id) {
		String transacaoJson = http.get(id.toString(), "token", LocawebGatewayConfig.getToken());
		return json.fromJSONToTransacao(transacaoJson);
	}

	public Transacao capturar(Integer id) {
		String jsonPost = json.buildJSONTokenPost();
		String transacaoJson = http.post(id.toString().concat("/capturar"), jsonPost);
		return json.fromJSONToTransacao(transacaoJson);
	}

}

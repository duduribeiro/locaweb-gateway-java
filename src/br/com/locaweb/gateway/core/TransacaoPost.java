package br.com.locaweb.gateway.core;

import br.com.locaweb.gateway.models.Comprador;
import br.com.locaweb.gateway.models.Pagamento;
import br.com.locaweb.gateway.models.Pedido;

import com.google.gson.annotations.SerializedName;

public class TransacaoPost {
	@SerializedName("url_retorno")
	protected String urlRetorno;
	protected Boolean capturar;
	protected Pedido pedido;
	protected Pagamento pagamento;
	protected Comprador comprador;
}

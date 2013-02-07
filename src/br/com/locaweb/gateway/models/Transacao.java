package br.com.locaweb.gateway.models;

import java.math.BigDecimal;

import com.google.gson.annotations.SerializedName;

public class Transacao {
	private Integer id;
	@SerializedName("url_retorno")
	private String urlRetorno;

	//variaveis de retorno
	private String status;
	@SerializedName("meio_pagamento")
	private MeioPagamento meioPagamento;
	@SerializedName("numero_pedido")
	private Integer numeroPedido;
	private BigDecimal total;
	@SerializedName("url_acesso")
	private String urlAcesso;
	private Detalhes detalhes;
	private Erro erro;


	//getters variaveis de retorno
	public Integer getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public MeioPagamento getMeioPagamento() {
		return meioPagamento;
	}

	public Integer getNumeroPedido() {
		return numeroPedido;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public String getUrlAcesso() {
		return urlAcesso;
	}

	public Detalhes getDetalhes() {
		return detalhes;
	}

	public Erro getErro() {
		return erro;
	}

	public boolean temErro() {
		return erro!=null;
	}

}

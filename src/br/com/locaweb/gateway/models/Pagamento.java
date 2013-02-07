package br.com.locaweb.gateway.models;

import com.google.gson.annotations.SerializedName;

public class Pagamento {
	private Bandeira bandeira;
	@SerializedName("meio_pagamento")
	private MeioPagamento meioPagamento;
	@SerializedName("cartao_numero")
	private String cartaoNumero;
	@SerializedName("cartao_cvv")
	private String cartaoCvv;
	@SerializedName("cartao_validade")
	private String cartaoValidade;
	private Integer parcelas;
	@SerializedName("tipo_operacao")
	private TipoOperacao tipoOperacao;

	public Bandeira getBandeira() {
		return bandeira;
	}

	public void setBandeira(Bandeira bandeira) {
		this.bandeira = bandeira;
	}

	public MeioPagamento getMeioPagamento() {
		return meioPagamento;
	}

	public void setMeioPagamento(MeioPagamento meioPagamento) {
		this.meioPagamento = meioPagamento;
	}

	public String getCartaoNumero() {
		return cartaoNumero;
	}

	public void setCartaoNumero(String cartaoNumero) {
		this.cartaoNumero = cartaoNumero;
	}

	public String getCartaoCvv() {
		return cartaoCvv;
	}

	public void setCartaoCvv(String cartaoCvv) {
		this.cartaoCvv = cartaoCvv;
	}

	public String getCartaoValidade() {
		return cartaoValidade;
	}

	public void setCartaoValidade(String cartaoValidade) {
		this.cartaoValidade = cartaoValidade;
	}

	public Integer getParcelas() {
		return parcelas;
	}

	public void setParcelas(Integer parcelas) {
		this.parcelas = parcelas;
	}

	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}
}

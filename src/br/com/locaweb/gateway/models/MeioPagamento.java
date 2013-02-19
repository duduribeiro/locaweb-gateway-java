package br.com.locaweb.gateway.models;

public enum MeioPagamento {
	REDECARD_WS,CIELO,REDECARD_WEB,ITAU_SHOPLINE,BOLETO_BRADESCO,BOLETO_BANCO_BRASIL,BOLETO_HSBC,BOLETO_CAIXA,BOLETO_ITAU;

	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
}

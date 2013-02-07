package br.com.locaweb.gateway.models;

public enum MeioPagamento {
	REDECARD_WS,CIELO;

	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
}

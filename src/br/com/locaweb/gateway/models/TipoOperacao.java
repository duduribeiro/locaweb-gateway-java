package br.com.locaweb.gateway.models;

public enum TipoOperacao {
	CREDITO_A_VISTA;

	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}

}

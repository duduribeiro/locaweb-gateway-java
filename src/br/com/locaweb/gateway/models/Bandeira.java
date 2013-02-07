package br.com.locaweb.gateway.models;

public enum Bandeira {
	VISA;

	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
}

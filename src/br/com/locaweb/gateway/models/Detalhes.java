package br.com.locaweb.gateway.models;

import com.google.gson.annotations.SerializedName;


public class Detalhes {
	//cielo
	private String tid;
	private String nsu;
	private String pan;
	private String arp;
	private String lr;

	//redecard
	@SerializedName("numero_sequencial")
	private String numeroSequencial;
	@SerializedName("numero_comprovante_venda")
	private String numeroComprovanteVenda;
	@SerializedName("numero_autenticacao")
	private String numeroAutenticacao;
	@SerializedName("numero_autorizacao")
	private String numeroAutorizacao;
	@SerializedName("url_comprovante")
	private String urlComprovante;


	//getters
	//cielo
	public String getTid() {
		return tid;
	}

	public String getNsu() {
		return nsu;
	}

	public String getPan() {
		return pan;
	}

	public String getArp() {
		return arp;
	}

	public String getLr() {
		return lr;
	}

	//redecard_ws
	public String getNumeroSequencial() {
		return numeroSequencial;
	}

	public String getNumeroComprovanteVenda() {
		return numeroComprovanteVenda;
	}

	public String getNumeroAutenticacao() {
		return numeroAutenticacao;
	}

	public String getNumeroAutorizacao() {
		return numeroAutorizacao;
	}

	public String getUrlComprovante() {
		return urlComprovante;
	}
}


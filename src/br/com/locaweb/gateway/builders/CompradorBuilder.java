package br.com.locaweb.gateway.builders;

import br.com.locaweb.gateway.models.Comprador;

public class CompradorBuilder {
	private Comprador comprador = new Comprador();

	public Comprador build() {
		return comprador;
	}

	public CompradorBuilder setNome(String nome) {
		this.comprador.setNome(nome);
		return this;
	}

	public CompradorBuilder setDocumento(String documento) {
		this.comprador.setDocumento(documento);
		return this;
	}

	public CompradorBuilder setEndereco(String endereco) {
		this.comprador.setEndereco(endereco);
		return this;
	}

	public CompradorBuilder setComplemento(String complemento) {
		this.comprador.setComplemento(complemento);
		return this;
	}

	public CompradorBuilder setNumero(String numero) {
		this.comprador.setNumero(numero);
		return this;
	}

	public CompradorBuilder setCep(String cep) {
		this.comprador.setCep(cep);
		return this;
	}

	public CompradorBuilder setBairro(String bairro) {
		this.comprador.setBairro(bairro);
		return this;
	}

	public CompradorBuilder setCidade(String cidade) {
		this.comprador.setCidade(cidade);
		return this;
	}

	public CompradorBuilder setEstado(String estado) {
		this.comprador.setEstado(estado);
		return this;
	}

}

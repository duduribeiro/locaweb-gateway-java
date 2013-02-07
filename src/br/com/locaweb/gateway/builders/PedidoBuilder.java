package br.com.locaweb.gateway.builders;

import java.math.BigDecimal;

import br.com.locaweb.gateway.models.Moeda;
import br.com.locaweb.gateway.models.Pedido;

public class PedidoBuilder {
	private Pedido pedido = new Pedido();

	public Pedido build() {
		return pedido;
	}

	public PedidoBuilder setNumero(Integer numero) {
		this.pedido.setNumero(numero);
		return this;
	}

	public PedidoBuilder setTotal(BigDecimal total) {
		this.pedido.setTotal(total);
		return this;
	}

	public PedidoBuilder setMoeda(Moeda moeda) {
		this.pedido.setMoeda(moeda);
		return this;
	}

	public PedidoBuilder setDescricao(String descricao) {
		this.pedido.setDescricao(descricao);
		return this;
	}
}

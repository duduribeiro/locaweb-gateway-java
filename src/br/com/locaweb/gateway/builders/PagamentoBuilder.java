package br.com.locaweb.gateway.builders;

import br.com.locaweb.gateway.models.Bandeira;
import br.com.locaweb.gateway.models.MeioPagamento;
import br.com.locaweb.gateway.models.Pagamento;
import br.com.locaweb.gateway.models.TipoOperacao;

public class PagamentoBuilder {
	private Pagamento pagamento = new Pagamento();

	public Pagamento build() {
		return pagamento;
	}

	public PagamentoBuilder setBandeira(Bandeira bandeira) {
		this.pagamento.setBandeira(bandeira);
		return this;
	}

	public PagamentoBuilder setMeioPagamento(MeioPagamento meioPagamento) {
		this.pagamento.setMeioPagamento(meioPagamento);
		return this;
	}

	public PagamentoBuilder setCartaoNumero(String cartaoNumero) {
		this.pagamento.setCartaoNumero(cartaoNumero);
		return this;
	}

	public PagamentoBuilder setCartaoCvv(String cvv) {
		this.pagamento.setCartaoCvv(cvv);
		return this;
	}

	public PagamentoBuilder setCartaoValidade(String cartaoValidade) {
		this.pagamento.setCartaoValidade(cartaoValidade);
		return this;
	}

	public PagamentoBuilder setParcelas(Integer parcelas) {
		this.pagamento.setParcelas(parcelas);
		return this;
	}

	public PagamentoBuilder setTipoOperacao(TipoOperacao tipoOperacao) {
		this.pagamento.setTipoOperacao(tipoOperacao);
		return this;
	}
}

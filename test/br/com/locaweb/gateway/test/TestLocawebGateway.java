package br.com.locaweb.gateway.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import br.com.locaweb.gateway.builders.CompradorBuilder;
import br.com.locaweb.gateway.builders.PagamentoBuilder;
import br.com.locaweb.gateway.builders.PedidoBuilder;
import br.com.locaweb.gateway.core.LocawebGateway;
import br.com.locaweb.gateway.core.LocawebGatewayConfig;
import br.com.locaweb.gateway.models.Bandeira;
import br.com.locaweb.gateway.models.Comprador;
import br.com.locaweb.gateway.models.MeioPagamento;
import br.com.locaweb.gateway.models.Moeda;
import br.com.locaweb.gateway.models.Pagamento;
import br.com.locaweb.gateway.models.Pedido;
import br.com.locaweb.gateway.models.TipoOperacao;
import br.com.locaweb.gateway.models.Transacao;

public class TestLocawebGateway {

	private Pagamento pagamento;
	private Comprador comprador;
	private Pedido pedido;
	private LocawebGateway gateway;
	@Before
	public void setUp() {
		pagamento = new PagamentoBuilder().setBandeira(Bandeira.VISA).setCartaoCvv("973").setCartaoNumero("4012001037141112").setCartaoValidade("082015")
		.setMeioPagamento(MeioPagamento.CIELO).setParcelas(1).setTipoOperacao(TipoOperacao.CREDITO_A_VISTA).build();

		comprador = new CompradorBuilder().setBairro("Centro").setCep("09710240").setCidade("S�o Paulo").setComplemento("Ap 82").setDocumento("12345678900")
				.setEndereco("Rua da Casa").setEstado("SP").setNome("Nome do comprador").setNumero("1").build();
		pedido = new PedidoBuilder().setDescricao("Pedido Test -- erro transacao sem url retorno").setMoeda(Moeda.REAL).setNumero(123).setTotal(new BigDecimal(300)).build();
		LocawebGatewayConfig.setToken("3a5bbed0-50d4-012f-8d73-0026bb5a6240");
		LocawebGatewayConfig.setSandbox(Boolean.TRUE);
		gateway = new LocawebGateway();

	}
	@Test
	public void testCapturarTransacao() {
		Transacao transacao = gateway.criarTransacao("http://api.teste.com.br/111", Boolean.TRUE, pedido, pagamento, comprador);
		assertNotNull(transacao);
		transacao = gateway.capturar(transacao.getId());
		assertEquals("paga", transacao.getStatus());
	}
}

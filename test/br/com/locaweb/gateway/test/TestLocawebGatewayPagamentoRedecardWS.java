package br.com.locaweb.gateway.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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

public class TestLocawebGatewayPagamentoRedecardWS {

	private Pagamento pagamento;
	private Comprador comprador;
	private Pedido pedido;
	private LocawebGateway gateway;
	@Before
	public void setUp() {
		pagamento = new PagamentoBuilder().setBandeira(Bandeira.VISA).setCartaoCvv("973").setCartaoNumero("4012001037141112").setCartaoValidade("082015")
		.setMeioPagamento(MeioPagamento.REDECARD_WS).setParcelas(1).setTipoOperacao(TipoOperacao.CREDITO_A_VISTA).build();

		comprador = new CompradorBuilder().setBairro("Centro").setCep("09710240").setCidade("São Paulo").setComplemento("Ap 82").setDocumento("12345678900")
				.setEndereco("Rua da Casa").setEstado("SP").setNome("Nome do comprador").setNumero("1").build();
		pedido = new PedidoBuilder().setDescricao("Pedido Test -- erro transacao sem url retorno").setMoeda(Moeda.REAL).setNumero(123).setTotal(new BigDecimal(300)).build();
		LocawebGatewayConfig.setToken("3a5bbed0-50d4-012f-8d73-0026bb5a6240");
		LocawebGatewayConfig.setSandbox(Boolean.TRUE);
		gateway = new LocawebGateway();

	}
	@Test
	public void testCriarTransacaoOk() {
		Transacao transacao = gateway.criarTransacao("http://urlretorno.com.br/111", Boolean.FALSE, pedido, pagamento, comprador);
		System.out.println("transacao criada: " + transacao.getId());
		assertFalse(transacao.temErro());
		assertNull(transacao.getErro());
		assertNotNull(transacao.getId());
		assertEquals(MeioPagamento.REDECARD_WS, transacao.getMeioPagamento());
		assertEquals(pedido.getNumero(), transacao.getNumeroPedido());
		assertEquals(pedido.getTotal().floatValue(), transacao.getTotal().floatValue(), .1);
		assertNotNull(transacao.getDetalhes());
	}

	@Test
	public void testConsultarTransacao() {
		Transacao transacao = gateway.consultarTransacao(11364);
		assertFalse(transacao.temErro());
		assertNull(transacao.getErro());
		assertEquals(new Integer(11364), transacao.getId());
		assertEquals(new Integer(123), transacao.getNumeroPedido());
		assertNotNull(transacao.getDetalhes());
	}

	@Test
	public void testDetalhesRedecardWS() {
		Transacao transacao = gateway.consultarTransacao(11364);
		assertEquals("8985", transacao.getDetalhes().getNumeroSequencial());
		assertEquals("9282", transacao.getDetalhes().getNumeroComprovanteVenda());
		assertEquals("9559", transacao.getDetalhes().getNumeroAutenticacao());
		assertEquals("9986", transacao.getDetalhes().getNumeroAutorizacao());
		assertEquals("https://ecommerce.redecard.com.br/pos_virtual/cupom.asp?DATA=20121201&TRANSACAO=201&NUMAUTOR=9986&NUMCV=9282&FILIACAO=1234567890", transacao.getDetalhes().getUrlComprovante());
	}



}

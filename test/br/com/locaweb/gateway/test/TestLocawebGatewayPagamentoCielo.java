package br.com.locaweb.gateway.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

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

public class TestLocawebGatewayPagamentoCielo {

	private Pagamento pagamentoCielo;
	private Comprador comprador;
	private Pedido pedido;
	private LocawebGateway gateway;
	@Before
	public void setUp() {
		pagamentoCielo = new PagamentoBuilder().setBandeira(Bandeira.VISA).setCartaoCvv("973").setCartaoNumero("4012001037141112").setCartaoValidade("082015")
		.setMeioPagamento(MeioPagamento.CIELO).setParcelas(1).setTipoOperacao(TipoOperacao.CREDITO_A_VISTA).build();

		comprador = new CompradorBuilder().setBairro("Centro").setCep("09710240").setCidade("São Paulo").setComplemento("Ap 82").setDocumento("12345678900")
				.setEndereco("Rua da Casa").setEstado("SP").setNome("Nome do comprador").setNumero("1").build();
		pedido = new PedidoBuilder().setDescricao("Pedido Test -- erro transacao sem url retorno").setMoeda(Moeda.REAL).setNumero(123).setTotal(new BigDecimal(300)).build();
		LocawebGatewayConfig.setToken("3a5bbed0-50d4-012f-8d73-0026bb5a6240");
		LocawebGatewayConfig.setSandbox(Boolean.TRUE);
		gateway = new LocawebGateway();

	}
	@Test
	public void testErroTransacaoSemUrlRetornoCielo() {
		Transacao transacao = gateway.criarTransacao("", false, pedido, pagamentoCielo, comprador);
		assertTrue(transacao.temErro());
		assertEquals("002", transacao.getErro().getCodigo());
	}

	@Test
	public void testErroTransacaoUrlInvalida() {
		Transacao transacao = gateway.criarTransacao("xxx", false, pedido, pagamentoCielo, comprador);
		assertTrue(transacao.temErro());
		assertEquals("002", transacao.getErro().getCodigo());
	}

	@Test
	public void testCriarTransacaoOk() {
		Transacao transacao = gateway.criarTransacao("http://urlretorno.com.br/111", Boolean.FALSE, pedido, pagamentoCielo, comprador);
		System.out.println("Transacao criada: " + transacao.getId());
		assertFalse(transacao.temErro());
		assertNull(transacao.getErro());
		assertNotNull(transacao.getId());
		assertEquals(MeioPagamento.CIELO, transacao.getMeioPagamento());
		assertEquals(pedido.getNumero(), transacao.getNumeroPedido());
		assertEquals(pedido.getTotal().floatValue(), transacao.getTotal().floatValue(), .1);
		assertNotNull(transacao.getDetalhes());
	}

	@Test
	public void testConsultarTransacao() {
		Transacao transacao = gateway.consultarTransacao(11349);
		assertFalse(transacao.temErro());
		assertNull(transacao.getErro());
		assertEquals(new Integer(11349), transacao.getId());
		assertEquals(new Integer(123), transacao.getNumeroPedido());
		assertNotNull(transacao.getDetalhes());
	}

	@Test
	public void testDetalhesCielo() {
		Transacao transacao = gateway.consultarTransacao(11349);
		assertEquals("10069930690C14BB1001", transacao.getDetalhes().getTid());
		assertEquals("791739", transacao.getDetalhes().getNsu());
		assertEquals("IqVz7P9zaIgTYdU41HaW/OB/d7Idwttqwb2vaTt8MT0=", transacao.getDetalhes().getPan());
		assertEquals("123456", transacao.getDetalhes().getArp());
		assertEquals("00", transacao.getDetalhes().getLr());
	}



}

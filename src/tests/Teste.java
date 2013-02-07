package tests;

import java.math.BigDecimal;

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

public class Teste {
	public static void main(String[] args) {
//		Pedido pedido = new PedidoBuilder().setDescricao("desc teste").setMoeda(Moeda.REAL).setNumero(50).setTotal(new BigDecimal(200.00)).build();
//
//		Pagamento pagamento = new PagamentoBuilder().setBandeira(Bandeira.VISA).setCartaoCvv("973").setCartaoNumero("4012001037141112").setCartaoValidade("082015")
//					.setMeioPagamento(MeioPagamento.CIELO).setParcelas(1).setTipoOperacao(TipoOperacao.CREDITO_A_VISTA).build();
//
//		Comprador comprador = new CompradorBuilder().setBairro("Centro").setCep("09710240").setCidade("São Paulo").setComplemento("Ap 82").setDocumento("12345678900")
//					.setEndereco("Rua da Casa").setEstado("SP").setNome("Nome do comprador").setNumero("1").build();
		LocawebGatewayConfig.setToken("3a5bbed0-50d4-012f-8d73-0026bb5a6240");
		LocawebGatewayConfig.setSandbox(Boolean.TRUE);
		LocawebGateway gateway = new LocawebGateway();
//
//		gateway.criarTransacao("", Boolean.TRUE, pedido, pagamento, comprador);

		Transacao transacao = gateway.capturar(11376);
		System.out.println(transacao);
	}
}

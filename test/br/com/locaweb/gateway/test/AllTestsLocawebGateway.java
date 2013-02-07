package br.com.locaweb.gateway.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestLocawebGatewayPagamentoCielo.class,
		TestLocawebGatewayPagamentoRedecardWS.class })
public class AllTestsLocawebGateway {
}

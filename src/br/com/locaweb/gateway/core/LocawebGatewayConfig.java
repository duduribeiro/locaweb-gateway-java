package br.com.locaweb.gateway.core;

public class LocawebGatewayConfig {
	private static String token;
	private static Boolean sandbox;

	public static String getToken() {
		return LocawebGatewayConfig.token;
	}

	public static void setToken(String token) {
		LocawebGatewayConfig.token = token;
	}

	public static Boolean isSandbox() {
		return LocawebGatewayConfig.sandbox;
	}

	public static void setSandbox(Boolean sandbox) {
		LocawebGatewayConfig.sandbox = sandbox;
	}
}

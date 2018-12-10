package edu.asselvi.rpc;

import javax.xml.ws.Endpoint;

public class PublicaCalculadora {
	public static void main(String[] args) {
		System.out.println("Calculadora no ar, esperando clientes...");
		Endpoint.publish("http://localhost:7790/rpc/calculadora", new ImplementaCalculadora());
	}
}

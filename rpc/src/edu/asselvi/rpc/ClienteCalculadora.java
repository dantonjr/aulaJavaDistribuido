package edu.asselvi.rpc;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class ClienteCalculadora {
	public static void main(String[] args) throws MalformedURLException {
		URL url = new URL("http://localhost:7790/rpc/calculadora?wsdl");
		QName qname = new QName("http://rpc.asselvi.edu/", "ImplementaCalculadoraService");  
		Service service = Service.create(url, qname);  
		ICalculadora calculadora = service.getPort(ICalculadora.class);  
		System.out.println("Adição: " + calculadora.adicao(20, 10));  
		System.out.println("Subtração: " + calculadora.subtracao(20, 10));  
		System.out.println("Divisão: " + calculadora.divisao(20, 10));  
		System.out.println("Multiplicacao: " + calculadora.multiplicacao(20, 10));  

	}
}

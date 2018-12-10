package edu.asselvi.rpc;

import javax.jws.WebService;

@WebService(endpointInterface = "edu.asselvi.rpc.ICalculadora")
public class ImplementaCalculadora implements ICalculadora {

	@Override
	public int adicao(int a, int b) {
		return a + b;
	}

	@Override
	public int subtracao(int a, int b) {
		return a - b;
	}

	@Override
	public int multiplicacao(int a, int b) {
		return a * b;
	}

	@Override
	public int divisao(int a, int b) {
		return a / b;
	}

}

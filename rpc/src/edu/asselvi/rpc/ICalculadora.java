package edu.asselvi.rpc;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface ICalculadora {
	@WebMethod
	public int adicao(int a, int b);
	@WebMethod
	public int subtracao(int a, int b);
	@WebMethod
	public int multiplicacao(int a, int b);
	@WebMethod
	public int divisao(int a, int b);
}

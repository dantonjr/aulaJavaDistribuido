package edu.asselvi.model.bd;

public class BDException extends Exception {

	private static final long serialVersionUID = 1L;

	public BDException(EErrosBD erro, String msg) {
		super(erro.getErro() + " - " + msg);
	}
}

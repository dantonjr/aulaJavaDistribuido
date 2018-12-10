package edu.asselvi.controller;

import edu.asselvi.bean.Pessoa;
import edu.asselvi.model.bd.BDException;
import edu.asselvi.model.bd.dao.PessoaDAO;

public class ControllerPessoa {
	public static boolean insere(Pessoa pessoa) throws BDException {
		if (pessoa instanceof Pessoa) {
			PessoaDAO pessoaDAO = new PessoaDAO();
			return pessoaDAO.insereAcesso(pessoa);
		}
		return false;
	}
	
	public static Pessoa consulta(int codigo) throws BDException {
		if (codigo > 0) {
			PessoaDAO pessoaDAO = new PessoaDAO();
			return pessoaDAO.consulta(codigo);
		}
		return null;
	}
}

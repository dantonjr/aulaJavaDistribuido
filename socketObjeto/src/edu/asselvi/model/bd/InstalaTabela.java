package edu.asselvi.model.bd;

import edu.asselvi.model.bd.dao.PessoaDAO;

public class InstalaTabela {
	public static void main(String[] args) throws BDException {
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.criaTabela();
	}
}

package edu.asselvi.model.bd;

public enum EErrosBD {
	CRIA_TABELA ("Erro ao criar a tabela especificada"),
	OPEN_CONEXAO ("Erro ao conectar com o Banco de Dados"),
	CLOSE_CONEXAO ("Falha ao fechar a conexão com o Banco de Dados"),
	CONSULTA ("Erro ao consultar dados na tabela especificada"),
	INSERIR ("Erro ao inserir dados na tabela especificada"),
	ATUALIZAR ("Erro ao atualizar os dados da tabela especificada"),
	EXCLUIR ("Erro ao excluir o registro selecionado");
	
	private final String erro;

	public String getErro() {
		return erro;
	}
	
	private EErrosBD(String erro) {
		this.erro = erro;
	}
}

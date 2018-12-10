package edu.asselvi.model.bd.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import edu.asselvi.bean.Pessoa;
import edu.asselvi.model.bd.BDException;
import edu.asselvi.model.bd.EErrosBD;
import edu.asselvi.model.bd.conexao.Conexao;

public class PessoaDAO {
	
	public boolean criaTabela() throws BDException {
		Connection conexao = Conexao.getConexao();
		try {
			Statement st = conexao.createStatement();
			st.execute("CREATE TABLE pessoa (" +
							"codigo		INTEGER NOT NULL PRIMARY KEY," +
							"nome		VARCHAR(45) NOT NULL," + 
							"nascimento	DATE NOT NULL," +
							"sexo		VARCHAR(1) NOT NULL," +
							"ativo		VARCHAR(1) NOT NULL," + 
							"salario	FLOAT NOT NULL" +
					  ");");
			return true;
		} catch (Exception e) {
			throw new BDException(EErrosBD.CRIA_TABELA, e.getMessage());
		} finally {
			Conexao.closeConexao();
		}
	}
	
	public boolean insereAcesso(Pessoa pessoa) throws BDException {
		Connection conexao = Conexao.getConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("INSERT INTO pessoa (codigo, nome, nascimento, sexo, ativo, salario) VALUES (?, ?, ?, ?, ?, ?);");
			pst.setInt(1, pessoa.getCodigo());
			pst.setString(2, pessoa.getNome());
			pst.setDate(3, new Date(pessoa.getNascimento().getTime()));
			pst.setString(4, String.valueOf(pessoa.getSexo()));
			pst.setString(5, pessoa.isAtivo() ? "S" : "N");
			pst.setFloat(6, pessoa.getSalario());
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BDException(EErrosBD.INSERIR, e.getMessage());
		} finally {
			Conexao.closeConexao();
		}
	}
	
	public Pessoa consulta(int codigo) throws BDException {
		Connection conexao = Conexao.getConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM pessoa WHERE codigo = ?;;");
			pst.setInt(1, codigo);
			ResultSet rs = pst.executeQuery();
			return rs.first() ?
					new Pessoa(rs.getInt("codigo"),
							   rs.getString("nome"),
							   rs.getDate("nascimento"),
							   rs.getString("sexo").charAt(0),
							   rs.getString("ativo").charAt(0) == 'S',
							   rs.getFloat("salario"))
					: null;
		} catch (Exception e) {
			throw new BDException(EErrosBD.CONSULTA, e.getMessage());
		} finally {
			Conexao.closeConexao();
		}
	}
}

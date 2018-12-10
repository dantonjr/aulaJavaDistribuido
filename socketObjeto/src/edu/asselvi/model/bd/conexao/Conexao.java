package edu.asselvi.model.bd.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

import edu.asselvi.model.bd.BDException;
import edu.asselvi.model.bd.EErrosBD;
import edu.asselvi.util.Padroes;

public class Conexao {

	private static Connection con = null;
	
	public static Connection getConexao() throws BDException {
		try {
			Class.forName(Padroes.DRIVER);
			con = DriverManager.getConnection(Padroes.CAMINHO + Padroes.SERVIDOR_BD + ":" + Padroes.PORTA_BD + "/" + Padroes.BASE + Padroes.PARAMETROS, Padroes.USUARIO, Padroes.SENHA);
			return con;
		} catch (Exception e) {
			throw new BDException(EErrosBD.OPEN_CONEXAO, e.getMessage());
		}
	}
	
	public static void closeConexao() throws BDException {
		try {
			if (con instanceof Connection) con.close();
			con = null;
		} catch (Exception e) {
			throw new BDException(EErrosBD.CLOSE_CONEXAO, e.getMessage());
		}
	}
}

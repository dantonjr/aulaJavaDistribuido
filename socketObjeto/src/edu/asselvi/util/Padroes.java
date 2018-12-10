package edu.asselvi.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Padroes {
	// Formatação
	public static final SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
	public static final DecimalFormat numeroFormatado = new DecimalFormat("##0.00");
	
	// Servidor Socket
	public static final int PORTA = 9600;
	public static final String SERVIDOR = "localhost";
	
	// Servidor BD
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String CAMINHO = "jdbc:mysql://";
	public static final String SERVIDOR_BD = "localhost";
	public static final String PORTA_BD = "3306";
	public static final String BASE = "dantonjr";
	public static final String PARAMETROS = "?useSSL=true";
	public static final String USUARIO = "alunos";
	public static final String SENHA = "social";
}
package edu.asselvi.cs.cliente;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.ParseException;

import edu.asselvi.bean.Pessoa;
import edu.asselvi.util.Padroes;
import edu.asselvi.util.Util;

public class ClienteObjeto {
	
	private static BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
	
	public static int montaMenu() throws NumberFormatException, IOException {
		System.out.println("*** MENU PRINCIPAL ***");
		System.out.println("1 - Consulta Objeto");
		System.out.println("2 - Insere Objeto");
		System.out.println("3 - Sair");
		System.out.print("Escolha opção: ");
		return Integer.parseInt(teclado.readLine());
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException, ParseException {
		int opcao;
		while ((opcao = montaMenu()) != 3) {
			int codigo;
			switch (opcao) {
			case 1:
				System.out.print("Informe código: ");
				codigo = Integer.parseInt(teclado.readLine());
				enviarObjetoServidor(new Integer(codigo));
				break;
			case 2:
				System.out.print("Informe código......: ");
				codigo = Integer.parseInt(teclado.readLine());
				System.out.print("Informe nome........: ");
				String nome = teclado.readLine();
				System.out.print("Informe nascimento..: ");
				String nascimento = teclado.readLine();
				System.out.print("Informe sexo........: ");
				char sexo = Character.toUpperCase(teclado.readLine().charAt(0));
				System.out.print("Informe ativo.......: ");
				boolean ativo = Character.toUpperCase(teclado.readLine().charAt(0)) == 'S';
				System.out.print("Informe salário.....: ");
				float salario = Float.parseFloat(teclado.readLine());
				enviarObjetoServidor(new Pessoa(codigo, nome, Padroes.dataFormatada.parse(nascimento), sexo, ativo, salario));
				break;
			default:
				System.out.println("Opção inválida...\n");
				break;
			}
		}
	}
	
	private static void enviarObjetoServidor(Object objeto) {
		try {
			Socket servidorSocket = new Socket(Padroes.SERVIDOR, Padroes.PORTA);
			System.out.println("O cliente se conectou ao servidor: " + Padroes.SERVIDOR + " na porta: " + Padroes.PORTA);
			BufferedOutputStream bufferServidor = new BufferedOutputStream(servidorSocket.getOutputStream());
			
			byte[] bytea = Util.serializarObjeto(objeto);
			bufferServidor.write(bytea);
			bufferServidor.flush();
	
			byte[] objectAsByte = new byte[servidorSocket.getReceiveBufferSize()];
			BufferedInputStream bf = new BufferedInputStream(servidorSocket.getInputStream());
			bf.read(objectAsByte);
			Object recebido = (Object) Util.getObjectFromByte(objectAsByte);
			
			// 3
			if (recebido instanceof Pessoa) {
				System.out.println(recebido.toString());
			} else if (recebido instanceof Integer) {
				System.out.println("Gravado com sucesso!!");
			}
			
			bufferServidor.close();
			servidorSocket.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

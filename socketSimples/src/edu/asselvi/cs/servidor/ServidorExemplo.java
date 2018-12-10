package edu.asselvi.cs.servidor;

import java.net.ServerSocket;
import java.net.Socket;

import edu.asselvi.cs.Constantes;

public class ServidorExemplo {
	private static ServerSocket serverSocket;
	private static Socket clienteSocket;
	private static Conexao conexao;
	private static String mensagem;
	
	public ServidorExemplo() {
		try {
			serverSocket = new ServerSocket(Constantes.PORTA, Constantes.TAMANHO_FILA);
			System.out.println("Criei o Servlet Socket na porta: " + Constantes.PORTA + " com uma fila de tamanho: " + Constantes.TAMANHO_FILA + " mensagen(s)");
		} catch (Exception e) {
			System.out.println("Servidor n�o foi pro ar...\nVerifique!!!");
		}
	}
	
	static boolean connect() {
		boolean ret = false;
		try {
			clienteSocket = serverSocket.accept();
			System.out.println("Porta: " + clienteSocket.getPort() + " " + clienteSocket.getLocalPort());
			ret = true;
		} catch (Exception e) {
			System.out.println("Erro de conex�o...\n" + e.getMessage());
			ret = false;
		}
		return ret;
	}
	
	public static void main(String[] args) {
		new ServidorExemplo();
		conexao = new Conexao();
		System.out.println("*** SERVIDOR NO AR ***\n" + conexao.toString() + "\nTamanho do Buffer: " + Constantes.TAMANHO_BUFFER);
		while (true) {
			System.out.println("Aguardando conex�o do cliente...");
			if (connect()) {
				for (int i = 0; i < Constantes.TAMANHO_FILA; i++) {
					mensagem = Conexao.receive(clienteSocket);
					System.out.println("Mensagem " + i + " recebida: " + mensagem + " tamanho: " + mensagem.length());
					conexao.send(clienteSocket, "Mensagem " + i + " recebida: " + mensagem);
				}
				try {
					clienteSocket.close();
				} catch (Exception e) {
					System.out.println("N�o desconectei cliente...\n" + e.getMessage());
				}
			} else {
				try {
					serverSocket.close();
				} catch (Exception e) {
					System.out.println("N�o desconectei servidor...\n" + e.getMessage());
				}
			}
		}
	}
}

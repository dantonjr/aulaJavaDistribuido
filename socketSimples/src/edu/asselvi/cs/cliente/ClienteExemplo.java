package edu.asselvi.cs.cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import edu.asselvi.cs.Constantes;

public class ClienteExemplo {

	static Conexao conexao;
	static Socket socket;

	public ClienteExemplo() {
		try {
			socket = new Socket(Constantes.SERVIDOR, Constantes.PORTA);
		} catch (Exception e) {
			System.out.println("Nao consegui resolver o host...");
		}
	}

	public static void main(String args[]) {
		String msg;
		String texto;
		new ClienteExemplo();
		conexao = new Conexao();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// fica num loop de n mensagens
		System.out.println("Envie " + Constantes.TAMANHO_FILA + " mensagen(s)");
		for (int i = 0; i < Constantes.TAMANHO_FILA; i++) {
			try {
				System.out.print("Mensagem " + i + ": ");
				msg = in.readLine();
				conexao.send(socket, msg);
				texto = conexao.receive(socket);
				System.out.println(texto);
			} catch (Exception e) {
				System.out.println("Erro na leitura " + e.getMessage());
			}
		}
		try {
			socket.close();
			System.out.println("Desconectado...");
		} catch (Exception e) {
			System.out.println("Nao desconectei..." + e);
		}
	}

}

package edu.asselvi.cs.cliente;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import edu.asselvi.cs.Constantes;

public class Conexao {

	public void send(Socket socket, String txt) {
		OutputStream out = null;
		try {
			out = socket.getOutputStream();
			out.write(txt.getBytes());
		} catch (Exception e) {
			System.out.println("Deu pau no OutputStream: " + e.getMessage());
		}
	}

	public String receive(Socket socket) {
		InputStream in = null;
		int bt = 0;
		byte btxt[] = new byte[Constantes.TAMANHO_BUFFER];
		String txt = "";
		try {
			in = socket.getInputStream();
			bt = in.read(btxt);
			if (bt > 0) {
				txt = new String(btxt);
				txt = txt.substring(0, bt);
			}
		} catch (Exception e) {
			System.out.println("Excecao na leitura do InputStream: " + e.getMessage());
		}
		return txt;
	}

}
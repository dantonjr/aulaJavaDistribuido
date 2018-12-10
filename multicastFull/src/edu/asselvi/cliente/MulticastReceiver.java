package edu.asselvi.cliente;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import edu.asselvi.util.Arquivo;
import edu.asselvi.util.Constantes;

public class MulticastReceiver {
	public static void main(String[] args) {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		MulticastSocket socket = null;
		DatagramPacket pacoteEntrada = null;
		byte[] bufferEntrada = new byte[Constantes.TAMANHO_BUFFER];
		try {
			System.out.print("Informe ID do Receptor: ");
			String ID = teclado.readLine();
			// Preparando para entrar no grupo
			socket = new MulticastSocket(Constantes.PORTA);
			InetAddress endereco = InetAddress.getByName(Constantes.GRUPO);
			socket.joinGroup(endereco);
			System.out.println("Aguardando servidor...");
			int arquivo = 0;
			String nomeArq = "";
			List<String> conteudo = new ArrayList<String>();
			while (true) {
				pacoteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
				socket.receive(pacoteEntrada);
				String msg = new String(bufferEntrada, 0, pacoteEntrada.getLength());
				switch (arquivo) {
				case 0:
					if (msg.equals("#@!inicio!@#")) {
						System.out.println("Início da recepção de arquivo...");
						arquivo = 1;
					} else {
						System.out.println("De " + pacoteEntrada.getAddress() + " Msg : " + msg);
					}
					break;
				case 1:
					nomeArq = msg;
					System.out.println("Recebendo arquivo: " + nomeArq);
					arquivo = 2;
					break;
				case 2:
					if (msg.equals("#@!fim!@#")) {
						System.out.println("Arquivo recebido...");
						arquivo = 3;
					} else {
						conteudo.add(msg);
					}
					break;
				case 3:
					System.out.println("Gravando arquivo...");
					nomeArq = System.getProperty("user.dir") + "\\arqSaida\\" + ID + "_" + nomeArq;
					Arquivo.gravaArquivo(nomeArq, conteudo, false);
					String md5Arq = Arquivo.getMD5(nomeArq);
					System.out.println("MD5 arquivo local:\t" + md5Arq);
					System.out.println("MD5 arquivo recebido:\t" + msg);
					arquivo = 0;
					conteudo.clear();
					if (!md5Arq.equals(msg)) {
						System.out.println("MD5 não confere, provável erro no recebimento do arquivo...");
						File arq = new File(nomeArq);
						arq.delete();
					}
					break;
				}
			}
		} catch (IOException | NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
		}
	}
}

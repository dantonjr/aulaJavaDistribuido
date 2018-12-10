package edu.asselvi.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.security.NoSuchAlgorithmException;

import edu.asselvi.util.Arquivo;
import edu.asselvi.util.Constantes;

public class MulticastSender {
	private static final BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
	
	private static int menu() throws NumberFormatException, IOException {
		System.out.println("*** MENU PRINCIPAL ***");
		System.out.println("\t1 - Mensagens");
		System.out.println("\t2 - Arquivo");
		System.out.println("\t3 - Sair");
		System.out.print("Entre sua opção: ");
		return Integer.parseInt(teclado.readLine());
	}

	private static String enviaMensagem(String mensagem) throws IOException {
		byte[] bufferSaida = mensagem.getBytes();
		InetAddress address = InetAddress.getByName(Constantes.GRUPO);
		DatagramPacket pacoteSaida = new DatagramPacket(bufferSaida, bufferSaida.length, address, Constantes.PORTA);
		DatagramSocket socket = new DatagramSocket();
		socket.send(pacoteSaida);
		socket.close();
		return "Servidor enviou: " + mensagem;
	}
	
	public static void main(String[] args) {
		System.out.println("Servidor inicializado...");
		try {
			boolean continua = true;
			while (continua) {
				switch (menu()) {
				case 1:
					System.out.print("Entre com a mensagem: ");
					String msg = teclado.readLine();
					while (!msg.equalsIgnoreCase("fim")) {
						System.out.println(enviaMensagem(msg));
						System.out.print("Entre com a próxima mensagem: ");
						msg = teclado.readLine();
					}
					break;
				case 2:
					System.out.print("Entre com o nome do arquivo: ");
					String nomeArq = teclado.readLine();
					System.out.println("Inicialização do envio do arquivo: " + enviaMensagem("#@!inicio!@#"));
					System.out.println(enviaMensagem(nomeArq));
					nomeArq = System.getProperty("user.dir") + "\\arqEntrada\\" + nomeArq;
					for (String linha : Arquivo.leArquivo(nomeArq)) {
						System.out.println(enviaMensagem(linha));
					}
					System.out.println("Finalização do envio do arquivo: " + enviaMensagem("#@!fim!@#"));
					System.out.println(enviaMensagem(Arquivo.getMD5(nomeArq)));
					break;
				case 3 :
					continua = false;
					break;
				default:
					System.out.println("Opção inválida...");
					break;
				}
			}
		} catch (IOException | NoSuchAlgorithmException e) {
			System.out.println(e);
		}
		System.out.println("Servidor encerrado...");
	}
}
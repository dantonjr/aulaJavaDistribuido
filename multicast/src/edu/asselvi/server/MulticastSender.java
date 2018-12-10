package edu.asselvi.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import edu.asselvi.util.Constantes;

public class MulticastSender {
	public static void main(String[] args) {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		DatagramSocket socket = null;
		DatagramPacket pacoteSaida = null;
		byte[] bufferSaida;
		try {
			socket = new DatagramSocket();
			while (true) {
				System.out.print("Entre com a mensagem: ");
				String msg = teclado.readLine();
				bufferSaida = msg.getBytes();

				// Envia para o multicast endereço IP e porta
				InetAddress address = InetAddress.getByName(Constantes.GRUPO);
				pacoteSaida = new DatagramPacket(bufferSaida, bufferSaida.length, address, Constantes.PORTA);
				socket.send(pacoteSaida);
				System.out.println("Servidor enviou: " + msg + "\n");
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
package edu.asselvi.cliente;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import edu.asselvi.util.Constantes;

public class MulticastReceiver {
	public static void main(String[] args) {
		MulticastSocket socket = null;
		DatagramPacket pacoteEntrada = null;
		byte[] bufferEntrada = new byte[Constantes.TAMANHO_BUFFER];
		try {
			// Preparando para entrar no grupo
			socket = new MulticastSocket(Constantes.PORTA);
			InetAddress endereco = InetAddress.getByName(Constantes.GRUPO);
			socket.joinGroup(endereco);
			System.out.println("Aguardando servidor...");
			while (true) {
				pacoteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
				socket.receive(pacoteEntrada);
				String msg = new String(bufferEntrada, 0, pacoteEntrada.getLength());
				System.out.println("De " + pacoteEntrada.getAddress() + " Msg : " + msg);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
//socket.leaveGroup(endereco);
//socket.close();

package edu.asselvi.cs.servidor;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import edu.asselvi.bean.Pessoa;
import edu.asselvi.controller.ControllerPessoa;
import edu.asselvi.model.bd.BDException;
import edu.asselvi.util.Padroes;
import edu.asselvi.util.Util;

public class ServerObjeto {
	public static void main(String args[]) throws BDException {
		while (true) {
			try {
				// 1
				ServerSocket serverSocket = new ServerSocket(Padroes.PORTA);
				System.out.println("Porta: " + Padroes.PORTA + " aberta!");
				System.out.println("Aguardando mensagem dos clientes...");
				Socket clienteSocket = serverSocket.accept();
				System.out.println("Nova conexão com o cliente " + clienteSocket.getInetAddress().getHostAddress());
				
				// 2
				byte[] objectAsByte = new byte[clienteSocket.getReceiveBufferSize()];
				BufferedInputStream bf = new BufferedInputStream(clienteSocket.getInputStream());
				bf.read(objectAsByte);

				// 3
				Object recebido = (Object) Util.getObjectFromByte(objectAsByte);
				if (recebido instanceof Integer) {
					System.out.println("Consultar ojeto para cliente");
					
					OutputStream saida = clienteSocket.getOutputStream();
					saida.write(Util.serializarObjeto(ControllerPessoa.consulta(((Integer) recebido).intValue())));
					saida.flush();
					
				} else if (recebido instanceof Pessoa) {
					System.out.println("*** RECEBI UMA PESSOA ***");
					System.out.println("Gravando a pessoa:\n\t" + recebido.toString());
					try {
						ControllerPessoa.insere((Pessoa) recebido);
						OutputStream saida = clienteSocket.getOutputStream();
						saida.write(Util.serializarObjeto(new Integer(1)));
						saida.flush();
					} catch (BDException e) {
						System.out.println("Problema ao inserir a pessoa...\n" + e.getMessage());
						// mensagem no servidor - precisa mandar para o cliente
					}
				} else {
					System.out.println("Objeto recebido não é compativel"); // Essa mensagem fica no servidor
				}

				// 4
				serverSocket.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}

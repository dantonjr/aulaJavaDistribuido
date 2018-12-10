package edu.asselvi.desserializa;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import edu.asselvi.bean.Pessoa;

public class DesserializaObjeto {
	public static void main(String[] args) {
		String caminho = System.getProperty("user.dir") + "\\dados\\pessoas.bin";
		System.out.println("Leitura do arquivo BINÁRIO em:\n\t" + caminho);
		try {
			ObjectInputStream desserializa = new ObjectInputStream(new FileInputStream(caminho));
			Pessoa p1 = (Pessoa) desserializa.readObject();
			Pessoa p2 = (Pessoa) desserializa.readObject();
			desserializa.close();
			System.out.println(p1);
			System.out.println(p2);
		} catch (Exception e) {
			System.out.println("Problemas com a desserialização...\n\t" + e.getMessage());
		}
	}
}

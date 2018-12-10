package edu.asselvi.desserializa;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import edu.asselvi.bean.Pessoa;

public class DesserializaXML {
	public static void main(String[] args) {
		String caminho = System.getProperty("user.dir") + "\\dados\\pessoas.xml";
		System.out.println("Leitura do arquivo XML em:\n\t" + caminho);
		try {
			XMLDecoder desserializa = new XMLDecoder(new BufferedInputStream(new FileInputStream(caminho)));
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

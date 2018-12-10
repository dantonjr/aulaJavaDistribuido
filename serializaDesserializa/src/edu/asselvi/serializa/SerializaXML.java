package edu.asselvi.serializa;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

import edu.asselvi.bean.Pessoa;

public class SerializaXML {
	public static void main(String[] args) {
		Calendar calendario = Calendar.getInstance();
		System.out.println("Cria��o dos objetos pessoa...");
		calendario.set(1977, Calendar.FEBRUARY, 27);
		Pessoa p = new Pessoa(10, "Danton Junior", 'M', calendario.getTime(), 500);
		calendario.set(1980, Calendar.MARCH, 10);
		Pessoa pp = new Pessoa(18, "Maria da Silva", 'F', calendario.getTime(), 1450);
		boolean sucesso = false;
		try {
			String caminho = System.getProperty("user.dir") + "\\dados\\pessoas.xml";
			System.out.println("Gravar no arquivo XML em:\n\t" + caminho);
			XMLEncoder serializa = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(caminho)));
			serializa.writeObject(p);
			serializa.writeObject(pp);
			serializa.flush();
			serializa.close();
			sucesso = true;
		} catch (Exception e) {
			System.out.println("Problemas com a serializa��o...\n\t" + e.getMessage());
		} finally {
			System.out.print(sucesso ? "Serializa��o com sucesso!!!" : "");
		}
	}
}

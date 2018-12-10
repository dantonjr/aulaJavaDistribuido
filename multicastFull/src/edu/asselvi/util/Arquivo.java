package edu.asselvi.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {
	public static void gravaArquivo(String nomeArq, List<String> linhas, boolean fimArq) throws IOException {
		PrintWriter arquivo = new PrintWriter(new FileWriter(new File(nomeArq), fimArq), true);
		for (int i = 0; i < linhas.size() - 1; i++) {
			arquivo.println(linhas.get(i));
		}
		if (linhas.size() != 0) {
			arquivo.print(linhas.get(linhas.size() - 1));
		}
		arquivo.close();
	}

	public static List<String> leArquivo(String nomeArq) throws IOException {
		BufferedReader arquivo = null;
		List<String> linhas = new ArrayList<String>();
		String linha = "";
		arquivo = new BufferedReader(new FileReader(new File(nomeArq)));
		while ((linha = arquivo.readLine()) != null) {
			linhas.add(linha);
		}
		arquivo.close();
		return linhas;
	}

	public static String getMD5(String nomeArq) throws NoSuchAlgorithmException, IOException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		FileInputStream arquivo = new FileInputStream(nomeArq);

		byte[] dados = new byte[1024];

		int leitura = 0;
		while ((leitura = arquivo.read(dados)) != -1) {
			md5.update(dados, 0, leitura);
		}
		byte[] md5Bytes = md5.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			sb.append(Integer.toString((md5Bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		arquivo.close();
		return sb.toString();
	}
}

package edu.asselvi.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Util {
	public static Object getObjectFromByte(byte[] objectAsByte) {
		Object obj = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			bis = new ByteArrayInputStream(objectAsByte);
			ois = new ObjectInputStream(bis);
			obj = ois.readObject();

			bis.close();
			ois.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return obj;
	}

	public static byte[] serializarObjeto(Object objeto) {
		try {
			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			ObjectOutputStream ous = new ObjectOutputStream(bao);
			ous.writeObject(objeto);
			return bao.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

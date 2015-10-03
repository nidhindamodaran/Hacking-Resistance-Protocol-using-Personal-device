package com.hrp.hrp_util;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author user
 */
public class Convertion {

	public byte[] getBytes(Object obj) {
		ObjectOutputStream oos = null;
		byte[] data = null;

		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			oos.close();
			bos.close();
			data = bos.toByteArray();
		} catch (Exception ex) {
			Logger.getLogger(Convertion.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return data;
	}
}

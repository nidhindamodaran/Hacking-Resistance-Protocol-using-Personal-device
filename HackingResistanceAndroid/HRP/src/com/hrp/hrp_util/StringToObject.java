package com.hrp.hrp_util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import android.util.Base64;

/**
 * 
 * @author Administrator
 */
public class StringToObject {

	public Object getObject(String value) {
		Object placeObject = null;
		ObjectInputStream inputStream = null;
		try {
			String response = value;
			byte[] decode = Base64.decode(response, Base64.DEFAULT);
			ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(
					decode);
			inputStream = new ObjectInputStream(arrayInputStream);
			Object readObject = inputStream.readObject();
			placeObject = readObject;
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(StringToObject.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(StringToObject.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (NullPointerException e) {
			return null;
		} finally {
			try {
				inputStream.close();
			} catch (IOException ex) {
				Logger.getLogger(StringToObject.class.getName()).log(
						Level.SEVERE, null, ex);
				return null;
			} catch (NullPointerException e2) {
				// TODO: handle exception
				return null;
			}
		}
		return placeObject;
	}
}

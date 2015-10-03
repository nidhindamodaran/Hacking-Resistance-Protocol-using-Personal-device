package com.hrp.hrp_service;

import java.io.File;
import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.hrp.hrp_util.AppConstants;
import com.hrp.hrp_util.Convertion;

import android.content.Context;
import android.util.Base64;

public class WebService {

	String SOAP_ACTION = "";
	String response = "";
	public Context context;

	public String upload(SoapObject object) {
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);

		envelope.setOutputSoapObject(object);
		HttpTransportSE transport = new HttpTransportSE(AppConstants.URL);
		try {
			transport.call(SOAP_ACTION, envelope);
			SoapPrimitive primitive = (SoapPrimitive) envelope.getResponse();
			response = primitive.toString();
			return response;
		} catch (IOException e) {
			return "error";
		} catch (XmlPullParserException e) {
			return "error";
		} catch (NullPointerException e) {
			return "error";
		} catch (ClassCastException e1) {
			// TODO: handle exception
			return "error";
		}

	}

	public String checkLogin(String username, String password, String imei) {
		// TODO Auto-generated method stub
		SOAP_ACTION = AppConstants.NAME_SPACE + AppConstants.LOGIN_CHECK;
		SoapObject object = new SoapObject(AppConstants.NAME_SPACE,
				AppConstants.LOGIN_CHECK);
		object.addProperty("username", username);
		object.addProperty("password", password);
		object.addProperty("imei", imei);
		String result = upload(object);
		return result;
	}

	public String getPassword(String username, String imei) {
		// TODO Auto-generated method stub
		SOAP_ACTION = AppConstants.NAME_SPACE + AppConstants.GET_PASSWORD;
		SoapObject object = new SoapObject(AppConstants.NAME_SPACE,
				AppConstants.GET_PASSWORD);
		object.addProperty("username", username);
		object.addProperty("imei", imei);
		String result = upload(object);
		return result;
	}

	public String invalidateFiles(String username, String imei) {
		// TODO Auto-generated method stub
		SOAP_ACTION = AppConstants.NAME_SPACE + AppConstants.GET_PASSWORD;
		SoapObject object = new SoapObject(AppConstants.NAME_SPACE,
				AppConstants.GET_PASSWORD);
		object.addProperty("username", username);
		object.addProperty("imei", imei);
		String result = upload(object);
		return result;
	}

}

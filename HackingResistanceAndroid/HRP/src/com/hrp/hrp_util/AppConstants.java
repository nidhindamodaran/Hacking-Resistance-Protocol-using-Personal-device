package com.hrp.hrp_util;

import java.io.File;

import android.os.Environment;

public interface AppConstants {

	// webservice config
	String IP = "192.168.43.252";
	String URL = "http://" + IP + ":8084/HackingResistanceWeb/AppMgr?wsdl";
	String NAME_SPACE = "http://ws.hrp.com/";
	// for webservice methods
	String LOGIN_CHECK = "oneTimeLogin";
	String GET_PASSWORD = "generateKey";
	String SEND_FILE_TO_WEB = null;
	// checking
	String ERROR = "error";
	String TRUE = "true";
	String NULL_STRING = "";
	// for shared preferences
	String USERNAME = "USERNAME";
	String PASSWORD = "PASSWORD";
	String IMEI = "IMEI";
	// for creating dir
	String HOME_DIR = Environment.getExternalStorageDirectory().getPath()
			+ File.separator + "HackingRP/";

}

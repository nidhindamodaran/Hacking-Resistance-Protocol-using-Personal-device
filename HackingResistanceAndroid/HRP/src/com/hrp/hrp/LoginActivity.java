package com.hrp.hrp;

import java.io.File;

import com.hrp.hrp_service.WebService;
import com.hrp.hrp_util.AppConstants;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	EditText usernameEditText = null;
	EditText passwordEditText = null;
	Button loginButton = null;
	TelephonyManager manager = null;
	SharedPreferences preferences = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		preferences = PreferenceManager
				.getDefaultSharedPreferences(LoginActivity.this);
		String username = preferences.getString(AppConstants.USERNAME, "");
		String password = preferences.getString(AppConstants.PASSWORD, "");
		// String imei = preferences.getString(AppConstants.IMEI, "");
		if (username.equals("") && password.equals("")) {
			setContentView(R.layout.activity_login);
			setcomponents();
			File homeFile = new File(AppConstants.HOME_DIR);
			if (!homeFile.isDirectory()) {
				boolean mkdirs = homeFile.mkdirs();
				Log.d("TAG", mkdirs + "");
			}
		} else {
			startActivity(new Intent(LoginActivity.this, HomeActivity.class));
			finish();
		}

	}

	private void setcomponents() {
		// TODO Auto-generated method stub
		usernameEditText = (EditText) findViewById(R.id.usernameEditText);
		passwordEditText = (EditText) findViewById(R.id.passwordEditText);
		loginButton = (Button) findViewById(R.id.loginButton);
		// getting telephony service
		manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		Log.d("TAG", manager.getDeviceId());
		loginButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (usernameEditText.getText().toString().equals("")) {
					usernameEditText.setError("Enter username");
				} else if (passwordEditText.getText().toString().equals("")) {
					passwordEditText.setError("Enter your password");

				} else {
					new CheckLogin().execute(usernameEditText.getText()
							.toString(), passwordEditText.getText().toString(),
							manager.getDeviceId());
				}

			}
		});
	}

	// check login
	private class CheckLogin extends AsyncTask<String, String, String> {
		ProgressDialog dialog = new ProgressDialog(LoginActivity.this);

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String rsp = new WebService().checkLogin(params[0], params[1],
					params[2]);
			return rsp;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			dialog.setMessage("Loading...!");
			dialog.show();
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			dialog.dismiss();
			if (!result.equals(AppConstants.ERROR)) {
				if (result.equals(AppConstants.TRUE)) {
					PreferenceManager
							.getDefaultSharedPreferences(LoginActivity.this)
							.edit()
							.putString(AppConstants.USERNAME,
									usernameEditText.getText().toString())
							.putString(AppConstants.PASSWORD,
									passwordEditText.getText().toString())
							.putString(AppConstants.IMEI, manager.getDeviceId())
							.commit();
					startActivity(new Intent(LoginActivity.this,
							HomeActivity.class));
					finish();
				} else {
					Toast.makeText(getApplicationContext(),
							"Invalid username, password or imei...!",
							Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(getApplicationContext(), "NetWork error...!",
						Toast.LENGTH_SHORT).show();
			}
		}

	}
}

package com.hrp.hrp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hrp.hrp_refer.GifAnimationDrawable;
import com.hrp.hrp_service.WebService;
import com.hrp.hrp_util.AppConstants;
import com.hrp.hrp_util.Appvariable;

public class HomeActivity extends Activity {
	ImageView genrateKey = null;
	Timer timer = null;
	Timer timer2 = null;
	MediaPlayer mediaPlayer = null;
	Handler myHandler = new Handler();
	Handler myHandlerForAnimation = new Handler();
	TimerTask timerTask = null;
	TimerTask timerTask2 = null;
	ImageView animationView = null;
	TextView messageTextView = null;
	int flag = 0;
	int flag1 = 60;
	GifAnimationDrawable animationDrawable = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		setComponents();
	}

	private void setComponents() {
		// TODO Auto-generated method stub
		genrateKey = (ImageView) findViewById(R.id.buttonImageView);
		mediaPlayer = MediaPlayer.create(HomeActivity.this, R.raw.rawbell);
		animationView = (ImageView) findViewById(R.id.animatedImageView);
		messageTextView = (TextView) findViewById(R.id.messageTextView);
		Typeface typeface = Typeface.createFromAsset(getAssets(),
				"trs-million rg.ttf");
		messageTextView.setTypeface(typeface);
		messageTextView.setText("60");

		genrateKey.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (messageTextView.getText().toString().equals("60")) {
					genrateKey
							.setBackgroundResource(R.drawable.generatekeybutton_back);
					timer = new Timer();
					timer2 = new Timer();
					new MakeFiles().execute("");

				} else {

				}

			}
		});
	}

	protected void updateGui() {
		// TODO Auto-generated method stub
		myHandler.post(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				flag1 = flag1 - 1;
				if (messageTextView.getText().toString().equals("0")) {
					timer.cancel();
					timerTask.cancel();
					timerTask = null;
					timer = null;
					mediaPlayer.start();
					flag1 = 0;
					messageTextView.setText("60");
					new Invalidate().execute("");
				} else {
					messageTextView.setText("" + flag1);
					genrateKey
							.setBackgroundResource(R.drawable.generatekeybutton_back);
				}

			}
		});
	}

	private void animationUi() {
		// TODO Auto-generated method stub

		myHandlerForAnimation.post(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				flag = flag + 1;
				if (flag == 1) {
					animationView.setImageResource(R.drawable.staege_one_first);
				} else if (flag == 2) {
					animationView.setImageResource(R.drawable.staege_second);
				} else if (flag == 3) {
					animationView.setImageResource(R.drawable.staege_third);
				} else if (flag == 4) {
					animationView.setImageResource(R.drawable.staege_forth);
				} else if (flag == 5) {
					flag = 0;
				}

			}
		});
	}

	// make a file for generating a key
	private class MakeFiles extends AsyncTask<String, String, String> {
		ProgressDialog dialog = new ProgressDialog(HomeActivity.this);
		String val = "true";
		File makedFile = null;

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			// getting value from sharedpreferences
			String username = PreferenceManager.getDefaultSharedPreferences(
					HomeActivity.this).getString(AppConstants.USERNAME, "");
			String imei = PreferenceManager.getDefaultSharedPreferences(
					HomeActivity.this).getString(AppConstants.IMEI, "");

			// getting value from webservice
			String pswd = new WebService().getPassword(username, imei);

			StringBuffer stringBuffer = new StringBuffer(pswd);
			while (stringBuffer.length() < 128) {
				stringBuffer.append(pswd);
			}
			try {
				// making file for writing sha-1 key
				byte[] data = stringBuffer.toString().getBytes("UTF-8");
				MessageDigest sha = MessageDigest.getInstance("SHA-1");
				byte[] digest = sha.digest(data);
				String filename = AppConstants.HOME_DIR + "password.key";
				makedFile = new File(filename);

				if (makedFile.exists()) {
					makedFile.delete();
				}
				FileOutputStream outputStream = new FileOutputStream(makedFile);
				outputStream.write(digest);
				outputStream.flush();
				outputStream.close();
				Appvariable.MAKED_FILE = makedFile;

			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				val = "error";
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				val = "error";
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				val = "error";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				val = "error";
			}
			return val;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			dialog.dismiss();
			if (!result.equals(AppConstants.ERROR)) {
				if (result.equals(AppConstants.TRUE)) {
					dialog("");
				} else {
					Toast.makeText(getApplicationContext(),
							"Error while loading values", Toast.LENGTH_SHORT)
							.show();
				}
			} else {
				Toast.makeText(getApplicationContext(), "NetWork error...!",
						Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			dialog.setMessage("Loading...!");
			dialog.setIndeterminate(true);
			dialog.show();

		}

	}

	public void dialog(String msg) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new Builder(HomeActivity.this);
		builder.setTitle("Alert");
		builder.setMessage("Yor key file is generated...!\n yor time will start when ok press");
		builder.setIcon(R.drawable.ic_launcher);
		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				animationView.setVisibility(View.VISIBLE);
				messageTextView.setVisibility(View.VISIBLE);
				timer.schedule(timerTask = new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						updateGui();

					}

				}, 0l, 1000);

				timer2.schedule(timerTask2 = new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						animationUi();
					}
				}, 0l, 100);
				dialog.dismiss();
			}
		}).create().show();
	}

	// call file for invalidate file
	private class Invalidate extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String username = PreferenceManager.getDefaultSharedPreferences(
					HomeActivity.this).getString(AppConstants.USERNAME, "");
			String imei = PreferenceManager.getDefaultSharedPreferences(
					HomeActivity.this).getString(AppConstants.IMEI, "");
			String rsp = new WebService().invalidateFiles(username, imei);
			return rsp;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			finish();
			startActivity(new Intent(HomeActivity.this, HomeActivity.class));
		}
	}
}

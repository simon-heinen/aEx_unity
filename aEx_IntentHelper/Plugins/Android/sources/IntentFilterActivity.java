package com.bitstars.unity.intentfilter;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.unity3d.player.UnityPlayer;

public class IntentFilterActivity extends Activity {

	private static final String LOG_TAG = IntentFilterActivity.class
			.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final String gameObjectName = "IntentFilter";
		final String methodName = "OnIntentFilter";
		final String messageToSend = getIntent().getData().toString();

		/*
		 * Get the main activity launch it and wait until the unity player is
		 * ready to receive the URL via UnityPlayer.UnitySendMessage()
		 */
		String myOwnPackageName = getApplicationContext().getPackageName();
		Intent mainLauncherIntent = getPackageManager()
				.getLaunchIntentForPackage(myOwnPackageName);
		startActivity(mainLauncherIntent);

		new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... params) {
				Log.d(LOG_TAG, "Will now wait until unity player is ready..");
				while (UnityPlayer.currentActivity == null) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				Log.d(LOG_TAG, "Unity is ready, sending event: "
						+ gameObjectName + "." + methodName + "("
						+ messageToSend + ")");
				UnityPlayer.UnitySendMessage(gameObjectName, methodName,
						messageToSend);
			}
		}.execute();

	}
}

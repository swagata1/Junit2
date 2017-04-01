package com.mindtree.fsmmindtree;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.mindtree.fsmmindtree.common.AppConfig;
import com.mindtree.fsmmindtree.common.AppConstants;
import com.mindtree.fsmmindtree.common.AppUtils;
import com.mindtree.fsmmindtree.data.PushRequestObject;

import java.io.IOException;

import de.greenrobot.event.EventBus;

public class GCMIntentService extends IntentService {
	private GoogleCloudMessaging gcm;
	private String mPushRegId;
	private Context mContext;
	private EventBus bus = EventBus.getDefault();

	public GCMIntentService() {
		super("GCMIntentService");

		mContext = this;
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		try {
			if (gcm == null) {
				gcm = GoogleCloudMessaging.getInstance(mContext);

				mPushRegId = gcm.register(AppConfig.GOOGLE_SENDER_ID);
				PushRequestObject pushRequestObject = new PushRequestObject();
				pushRequestObject.setGcmregid(mPushRegId);

				bus.post(pushRequestObject);

				// Save it to shared preference.
				AppUtils.saveStringSetting(mContext, AppConstants.GCM_ID, mPushRegId);
			}
		} catch (IOException ex) {
		}
	}

}

package com.mindtree.fsmmindtree;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.mindtree.fsmmindtree.common.AppConstants;
import com.mindtree.fsmmindtree.common.AppUtils;
import com.mindtree.fsmmindtree.data.AckPushRequestObject;
import com.mindtree.fsmmindtree.data.AckPushResponseObject;
import com.mindtree.fsmmindtree.model.LoginService;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Receiver class for push notification. This has been added in this packages so
 * that its easy to define in manifest file.
 * 
 */

public class GcmBroadcastReceiver extends WakefulBroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		AckPushRequestObject requestObject = new AckPushRequestObject();
		requestObject.setGcmregid(AppUtils.getStringSetting(context, AppConstants.GCM_ID));

		//  Send push ack
		LoginService loginService = new LoginService();
		loginService.getApi()
				.ackPush(requestObject)
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Observer<AckPushResponseObject>() {
					@Override
					public void onCompleted() {
					}

					@Override
					public void onError(Throwable e) {

					}

					@Override
					public void onNext(AckPushResponseObject ackPushResponseObject) {
                        Log.i("Push ack", "Push Ack sent successfully...");
					}
				});

		// Explicitly specify that GcmMessageHandler will handle the intent.
		ComponentName comp = new ComponentName(context.getPackageName(),
				GcmMessageHandler.class.getName());

		// Start the service, keeping the device awake while it is launching.
		startWakefulService(context, (intent.setComponent(comp)));
		setResultCode(Activity.RESULT_OK);
	}
}

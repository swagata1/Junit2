package com.mindtree.fsmmindtree.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.mindtree.fsmmindtree.data.location.LocationRequestObject;
import com.mindtree.fsmmindtree.data.location.LocationResponseObject;
import com.mindtree.fsmmindtree.data.location.UserLocationObject;
import com.mindtree.fsmmindtree.model.UserLocationService;

import de.greenrobot.event.EventBus;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sunilbagunji on 08/12/15.
 */
public class UserLocationPresenter {
    private UserLocationService mUserLocationService;
    private EventBus bus = EventBus.getDefault();
    private Context mContext;
    private ProgressDialog pd;

    public UserLocationPresenter(Context context) {
        mContext = context;

        mUserLocationService = new UserLocationService();
    }

    public void getUserLocationList() {
        callApi();
    }

    public void callApi() {
        mUserLocationService.getApi()
                .getUserLocationList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserLocationObject>() {
                    @Override
                    public void onCompleted() {
                        Log.i("onCompleted", "onCompleted.....s");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("onError", "onError.....s" + e.getMessage());
                    }

                    @Override
                    public void onNext(UserLocationObject userLocationResponseObject) {
                        bus.post(userLocationResponseObject);
                    }
                });
    }

    public void callAddUserLocationApi(LocationRequestObject loationRequestObject) {
        mUserLocationService.getApi()
                .addUserLocation(loationRequestObject)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LocationResponseObject>() {
                    @Override
                    public void onCompleted() {
                        Log.i("onCompleted", "onCompleted.....s");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("onError", "onError.....s" + e.getMessage());
                    }

                    @Override
                    public void onNext(LocationResponseObject LocationResponseObject) {
                        bus.post(LocationResponseObject);
                    }
                });
    }

}


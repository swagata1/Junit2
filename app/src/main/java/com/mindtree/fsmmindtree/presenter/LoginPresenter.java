package com.mindtree.fsmmindtree.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.mindtree.fsmmindtree.common.AppConstants;
import com.mindtree.fsmmindtree.common.AppUtils;
import com.mindtree.fsmmindtree.common.RuntimeData;
import com.mindtree.fsmmindtree.data.LoginRequestObject;
import com.mindtree.fsmmindtree.data.LoginResponseObject;
import com.mindtree.fsmmindtree.data.PushRequestObject;
import com.mindtree.fsmmindtree.data.PushResponseObject;
import com.mindtree.fsmmindtree.model.LoginService;
import com.mindtree.fsmmindtree.view.RegisterActivity;
import com.mindtree.fsmmindtree.view.TaskListActivity;

import de.greenrobot.event.EventBus;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sunilbagunji on 08/12/15.
 */
public class LoginPresenter {
    private LoginRequestObject loginRequestObject;
    private LoginService mLoginService;
    private EventBus bus = EventBus.getDefault();
    private Context mContext;
    private ProgressDialog pd;

    public LoginPresenter(Context context, LoginRequestObject loginRequestObject) {
        mContext = context;

        this.loginRequestObject = loginRequestObject;
        mLoginService = new LoginService();
    }

    public void loginUser() {
        //  Validate inputs.
        if (validateInputs()) {
            LoginResponseObject responseObject = new LoginResponseObject();
            responseObject.setAccesstoken(null);

            bus.post(responseObject);
        } else {
            callApi();
        }
    }

    public boolean validateInputs() {
        boolean error = false;

        if (TextUtils.isEmpty(loginRequestObject.getUsername())) {
            error = true;
        }
        if (TextUtils.isEmpty(loginRequestObject.getPassword())) {
            error = true;
        }

        if (TextUtils.isEmpty(loginRequestObject.getUsertype())) {
            error = true;
        }

        return error;
    }

    public void callApi() {
        mLoginService.getApi()
                .login(loginRequestObject)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResponseObject>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        LoginResponseObject loginResponseObject = new LoginResponseObject();
                        loginResponseObject.setAccesstoken(null);

                        bus.post(loginResponseObject);
                    }

                    @Override
                    public void onNext(LoginResponseObject loginResponseObject) {
                        RuntimeData.sLoginResponseObject = loginResponseObject;
                        bus.post(loginResponseObject);
                    }
                });
    }

    public void callRegPushApi(PushRequestObject pushRequestObject) {
        mLoginService.getApi()
                .registerForPush(pushRequestObject)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PushResponseObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PushResponseObject pushResponseObject) {
                        bus.post(pushResponseObject);
                    }
                });
    }

    public void navigateToHome() {
        Intent intent = new Intent(mContext, TaskListActivity.class);
        mContext.startActivity(intent);
    }

    public void navigateToRegister() {
        Intent intent = new Intent(mContext, RegisterActivity.class);
        mContext.startActivity(intent);
    }


    public void setUserType(Context context, int userType) {
        //  save selected user to shared preference.
        AppUtils.saveIntSetting(context, AppConstants.SETTING_USERTYPE, userType);
    }

    public int getUserType(Context context) {
        return AppUtils.getIntSetting(context, AppConstants.SETTING_USERTYPE);
    }

    public void setUsername(Context context, String username) {
        AppUtils.saveStringSetting(context, AppConstants.SETTING_USERNAME, username);
    }

    public String getUsername(Context context) {
        return AppUtils.getStringSetting(context, AppConstants.SETTING_USERNAME);
    }
}


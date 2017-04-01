package com.mindtree.fsmmindtree.presenter;

import android.text.TextUtils;

import com.mindtree.fsmmindtree.data.RegisterRequestObject;
import com.mindtree.fsmmindtree.data.RegisterResponseObject;
import com.mindtree.fsmmindtree.model.RegisterService;

import de.greenrobot.event.EventBus;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sunilbagunji on 08/12/15.
 */
public class RegisterPresenter {
    private RegisterRequestObject registerRequestObject;
    private RegisterService mRegisterService;
    private EventBus bus = EventBus.getDefault();

    public RegisterPresenter(RegisterRequestObject requestObject) {
            registerRequestObject = requestObject;
            mRegisterService = new RegisterService();
        }

        public void registerUser() {
            //  Validate inputs.
            if(validateInputs()) {
                RegisterResponseObject registerResponseObject = new RegisterResponseObject();
                registerResponseObject.setResult(false);

                bus.post(registerResponseObject);
            } else {
                callApi();
            }
        }

    public boolean validateInputs() {
        boolean error = false;

        if (TextUtils.isEmpty(registerRequestObject.getFirstname())){
            error = true;
        }

        if (TextUtils.isEmpty(registerRequestObject.getLastname())){
            error = true;
        }

        if (TextUtils.isEmpty(registerRequestObject.getUsername())){
           error = true;
        }
        if (TextUtils.isEmpty(registerRequestObject.getPassword())){

            error = true;
        }

        if(TextUtils.isEmpty(registerRequestObject.getEmail())){
            error = true;
        } else {
            //  Check for valid email id
            if(android.util.Patterns.EMAIL_ADDRESS.matcher(registerRequestObject.getEmail()).matches()) {
                return false;
            } else {
                return true;
            }
        }

        if (TextUtils.isEmpty(String.valueOf(registerRequestObject.getMobilenum()))){
            error = true;
        } else {
            //  Check for valid mobile number
            if(String.valueOf(registerRequestObject.getMobilenum()).matches("^(?:0091|\\\\+91|0)[7-9][0-9]{9}$")) {
                return false;
            } else {
                return true;
            }
        }

        if (TextUtils.isEmpty(registerRequestObject.getUsertype())){
            error = true;
        }


        return error;
    }

    public void callApi() {
        mRegisterService.getApi()
                .register(registerRequestObject)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterResponseObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        RegisterResponseObject registerResponseObject = new RegisterResponseObject();
                        registerResponseObject.setResult(false);

                        bus.post(registerResponseObject);
                    }

                    @Override
                    public void onNext(RegisterResponseObject registerResponseObject) {
                        bus.post(registerResponseObject);
                    }
                });
    }

}


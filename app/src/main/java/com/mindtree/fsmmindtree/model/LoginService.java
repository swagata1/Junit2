package com.mindtree.fsmmindtree.model;

import com.mindtree.fsmmindtree.common.RestClient;
import com.mindtree.fsmmindtree.data.AckPushRequestObject;
import com.mindtree.fsmmindtree.data.AckPushResponseObject;
import com.mindtree.fsmmindtree.data.LoginRequestObject;
import com.mindtree.fsmmindtree.data.LoginResponseObject;
import com.mindtree.fsmmindtree.data.PushRequestObject;
import com.mindtree.fsmmindtree.data.PushResponseObject;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by sunilbagunji on 08/12/15.
 */
public class LoginService {
    private LoginApi mLoginApi;

    public LoginService() {
        RestClient restClient = RestClient.getInstance();
        mLoginApi = restClient.getRestAdapter().create(LoginApi.class);
    }

    public LoginApi getApi() {
        return mLoginApi;
    }

    public interface LoginApi {
        @POST("/authenticateuser")
        public Observable<LoginResponseObject> login(@Body LoginRequestObject loginRequestObject);

        @POST("/registerfornotification")
        public Observable<PushResponseObject> registerForPush(@Body PushRequestObject pushRequestObject);

        @POST("/acknowledgenotification")
        public Observable<AckPushResponseObject> ackPush(@Body AckPushRequestObject ackPushRequestObject);
    }
}

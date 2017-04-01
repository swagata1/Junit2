package com.mindtree.fsmmindtree.model;

import com.mindtree.fsmmindtree.common.RestClient;
import com.mindtree.fsmmindtree.data.RegisterRequestObject;
import com.mindtree.fsmmindtree.data.RegisterResponseObject;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by sunilbagunji on 08/12/15.
 */
public class RegisterService {
    private RegisterApi mRegisterApi;

    public RegisterService() {
        RestClient restClient = RestClient.getInstance();
        mRegisterApi = restClient.getRestAdapter().create(RegisterApi.class);
    }

    public RegisterApi getApi() {
        return mRegisterApi;
    }

    public interface RegisterApi {
        @POST("/registeruser")
        public Observable<RegisterResponseObject> register(@Body RegisterRequestObject register);
    }

}

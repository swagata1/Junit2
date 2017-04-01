package com.mindtree.fsmmindtree.common;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by sunilbagunji on 08/12/15.
 */
public class RestClient {
    private static RestClient sRestClient = null;
    private static RestAdapter sRestAdapter = null;

    private RestClient() {
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "application/json");
//                request.addHeader("authorization", "aad123jk098abcd");//"aad123jk098aa"); //aad123jk098abcd
                if(RuntimeData.sLoginResponseObject != null && RuntimeData.sLoginResponseObject.getAccesstoken() != null) {
                    request.addHeader("authorization", RuntimeData.sLoginResponseObject.getAccesstoken());
                }
            }
        };


        sRestAdapter = new RestAdapter.Builder()
                    .setEndpoint(AppConfig.BASE_URL)
                    .setRequestInterceptor(requestInterceptor)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();
    }

    public static RestClient getInstance() {
        if(sRestClient == null) {
            sRestClient = new RestClient();
        }
        return sRestClient;
    }

    public RestAdapter getRestAdapter() {
        return sRestAdapter;
    }
}

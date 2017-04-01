package com.mindtree.fsmmindtree.model;

import com.mindtree.fsmmindtree.common.RestClient;
import com.mindtree.fsmmindtree.data.location.LocationRequestObject;
import com.mindtree.fsmmindtree.data.location.LocationResponseObject;
import com.mindtree.fsmmindtree.data.location.UserLocationObject;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by sunilbagunji on 08/12/15.
 */
public class UserLocationService {
    private UserLocationApi mUserLocationApi;

    public UserLocationService() {
        RestClient restClient = RestClient.getInstance();
        mUserLocationApi = restClient.getRestAdapter().create(UserLocationApi.class);
    }

    public UserLocationApi getApi() {
        return mUserLocationApi;
    }

    public interface UserLocationApi {
        @GET("/getuserlocation")
        public Observable<UserLocationObject> getUserLocationList();

        @POST("/adduserlocation")
        public Observable <LocationResponseObject> addUserLocation(@Body LocationRequestObject locationRequestObject);
    }
}

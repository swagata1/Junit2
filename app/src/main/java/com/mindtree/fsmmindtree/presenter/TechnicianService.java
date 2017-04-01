package com.mindtree.fsmmindtree.presenter;

import com.mindtree.fsmmindtree.common.RestClient;
import com.mindtree.fsmmindtree.data.technician.TechnicianResponseObject;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by sunilbagunji on 08/12/15.
 */
public class TechnicianService {
    private TechnicianApi mTechnicianApi;

    public TechnicianService() {
        RestClient restClient = RestClient.getInstance();
        mTechnicianApi = restClient.getRestAdapter().create(TechnicianApi.class);
    }

    public TechnicianApi getApi() {
        return mTechnicianApi;
    }

    public interface TechnicianApi {
        @GET("/gettechnicians")
        public Observable<TechnicianResponseObject> getTechnicianList();
    }

}

package com.mindtree.fsmmindtree.model;

import com.mindtree.fsmmindtree.common.RestClient;
import com.mindtree.fsmmindtree.data.task.TaskResponseObject;
import com.mindtree.fsmmindtree.data.task.UpdateRequestObject;
import com.mindtree.fsmmindtree.data.task.UpdateResponseObject;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by sunilbagunji on 08/12/15.
 */
public class TaskService {
    private TaskApi mTaskApi;

    public TaskService() {
        RestClient restClient = RestClient.getInstance();
        mTaskApi = restClient.getRestAdapter().create(TaskApi.class);
    }

    public TaskApi getApi() {
        return mTaskApi;
    }

    public interface TaskApi {
        @GET("/gettask")
        public Observable<TaskResponseObject> getTaskList();

        @POST("/updatetask")
        public Observable<UpdateResponseObject> assignTask(@Body UpdateRequestObject updateRequestObject);
    }

}

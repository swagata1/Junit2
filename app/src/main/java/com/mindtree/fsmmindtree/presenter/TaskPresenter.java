package com.mindtree.fsmmindtree.presenter;

import android.content.Context;
import android.util.Log;

import com.mindtree.fsmmindtree.common.RuntimeData;
import com.mindtree.fsmmindtree.data.task.Task;
import com.mindtree.fsmmindtree.data.task.TaskResponseObject;
import com.mindtree.fsmmindtree.data.task.UpdateRequestObject;
import com.mindtree.fsmmindtree.data.task.UpdateResponseObject;
import com.mindtree.fsmmindtree.model.TaskService;

import java.util.Collections;
import java.util.Comparator;

import de.greenrobot.event.EventBus;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sunilbagunji on 08/12/15.
 */
public class TaskPresenter {
    private TaskService mTechnicianService;
    private EventBus bus = EventBus.getDefault();
    private Context mContext;


    public TaskPresenter(Context context) {
        mContext = context;

        mTechnicianService = new TaskService();
    }

    public void getTasksList() {
        callTaskListApi();
    }

    public void callTaskListApi() {
        mTechnicianService.getApi()
                .getTaskList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TaskResponseObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
//                        bus.post(null);
                        Log.i("Error", e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(TaskResponseObject taskResponseObject) {
                        bus.post(taskResponseObject);
                    }
                });
    }

    public void callAssignTaskApi(UpdateRequestObject updateRequestObject) {
        mTechnicianService.getApi()
                .assignTask(updateRequestObject)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdateResponseObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
//                        bus.post(null);
                        Log.i("Error", e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(UpdateResponseObject updateResponseObject) {
                        bus.post(updateResponseObject);
                    }
                });
    }

   public void sortByDate() {
       Collections.sort(RuntimeData.sTaskList, new Comparator<Task>() {
           @Override
           public int compare(Task t1, Task t2) {
               if (t1.getAssigneddate() > t2.getAssigneddate()) {
                   bus.post(RuntimeData.sTaskList);

                   return 1;
               } else {
                   bus.post(RuntimeData.sTaskList);

                   return -1;
               }
           }
       });
   }

    public void sortByType() {
        Collections.sort(RuntimeData.sTaskList, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                if (t1.getRequesttype() > t2.getRequesttype()) {
                    bus.post(RuntimeData.sTaskList);

                    return 1;
                } else {
                    bus.post(RuntimeData.sTaskList);

                    return -1;
                }
            }
        });
    }

    public void sortByPriority() {
        Collections.sort(RuntimeData.sTaskList, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                if (t1.getPriority() > t2.getPriority()) {
                    bus.post(RuntimeData.sTaskList);

                    return 1;
                } else {
                    bus.post(RuntimeData.sTaskList);

                    return -1;
                }
            }
        });
    }

    public void sortByStatus() {
        Collections.sort(RuntimeData.sTaskList, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                if (t1.getStatus() > t2.getStatus()) {
                    bus.post(RuntimeData.sTaskList);

                    return 1;
                } else {
                    bus.post(RuntimeData.sTaskList);

                    return -1;
                }
            }
        });
    }


}


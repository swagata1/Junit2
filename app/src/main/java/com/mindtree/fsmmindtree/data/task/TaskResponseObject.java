package com.mindtree.fsmmindtree.data.task;

/**
 * Created by sunilbagunji on 10/12/15.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TaskResponseObject {

    @SerializedName("tasks")
    @Expose
    private List<Task> tasks = new ArrayList<Task>();

    /**
     *
     * @return
     * The tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     *
     * @param tasks
     * The tasks
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

}
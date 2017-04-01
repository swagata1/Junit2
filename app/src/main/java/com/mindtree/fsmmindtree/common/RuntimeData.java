package com.mindtree.fsmmindtree.common;

import com.mindtree.fsmmindtree.data.LoginResponseObject;
import com.mindtree.fsmmindtree.data.technician.Result;
import com.mindtree.fsmmindtree.data.task.Task;

import java.util.ArrayList;

/**
 * Created by sunilbagunji on 08/12/15.
 */
public class RuntimeData {
    public static LoginResponseObject sLoginResponseObject;

    public static Task sSelectedTask;

    public static ArrayList <Result> sTechicianList;

    public static Result sSelectedTechician;

    public static ArrayList<Task> sTaskList;

}

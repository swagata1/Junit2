package com.mindtree.fsmmindtree.data.technician;

/**
 * Created by sunilbagunji on 10/12/15.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TechnicianResponseObject {

    @SerializedName("result")
    @Expose
    private List<Result> result = new ArrayList<Result>();

    /**
     *
     * @return
     * The result
     */
    public List<Result> getResult() {
        return result;
    }

    /**
     *
     * @param result
     * The result
     */
    public void setResult(List<Result> result) {
        this.result = result;
    }

}



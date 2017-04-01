package com.mindtree.fsmmindtree.data.location;

/**
 * Created by sunilbagunji on 12/12/15.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UserLocationObject {

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

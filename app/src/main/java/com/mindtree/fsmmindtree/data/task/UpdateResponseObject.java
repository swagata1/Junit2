package com.mindtree.fsmmindtree.data.task;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sunilbagunji on 08/12/15.
 */
public class UpdateResponseObject {
    @SerializedName("result")
    @Expose
    private Boolean result;

    /**
     *
     * @return
     * The result
     */
    public Boolean getResult() {
        return result;
    }

    /**
     *
     * @param result
     * The result
     */
    public void setResult(Boolean result) {
        this.result = result;
    }

}

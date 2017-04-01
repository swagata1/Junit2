package com.mindtree.fsmmindtree.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sunilbagunji on 08/12/15.
 */
public class PushResponseObject {
    @SerializedName("result")
    @Expose
    private boolean result;

    /**
     *
     * @return
     * The result
     */
    public boolean getResult() {
        return result;
    }

    /**
     *
     * @param result
     * The result
     */
    public void setResult(boolean result) {
        this.result = result;
    }

}

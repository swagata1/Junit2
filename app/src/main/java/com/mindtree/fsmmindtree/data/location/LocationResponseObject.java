package com.mindtree.fsmmindtree.data.location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sunilbagunji on 08/12/15.
 */
public class LocationResponseObject {
    @SerializedName("result")
    @Expose
    private String result;

    /**
     *
     * @return
     * The result
     */
    public String getResult() {
        return result;
    }

    /**
     *
     * @param result
     * The result
     */
    public void setResult(String result) {
        this.result = result;
    }

}

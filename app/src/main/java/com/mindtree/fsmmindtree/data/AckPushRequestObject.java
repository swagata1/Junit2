package com.mindtree.fsmmindtree.data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AckPushRequestObject {
    @SerializedName("gcmregid")
    @Expose
    private String gcmregid;

    /**
     *
     * @return
     * The gcmregid
     */
    public String getGcmregid() {
        return gcmregid;
    }

    /**
     *
     * @param gcmregid
     * The gcmregid
     */
    public void setGcmregid(String gcmregid) {
        this.gcmregid = gcmregid;
    }
}
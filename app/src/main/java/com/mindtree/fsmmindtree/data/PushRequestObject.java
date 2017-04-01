package com.mindtree.fsmmindtree.data;

/**
 * Created by sunilbagunji on 18/12/15.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PushRequestObject {
    @SerializedName("gcmregid")
    @Expose
    private String gcmregid;
    @SerializedName("username")
    @Expose
    private String username;

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

    /**
     *
     * @return
     * The username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     * The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

}



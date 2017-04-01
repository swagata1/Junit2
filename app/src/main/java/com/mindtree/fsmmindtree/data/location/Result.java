package com.mindtree.fsmmindtree.data.location;

/**
 * Created by sunilbagunji on 12/12/15.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Result {

    @SerializedName("assignedto")
    @Expose
    private String assignedto;
    @SerializedName("location")
    @Expose
    private List<Location> location = new ArrayList<Location>();

    /**
     *
     * @return
     * The assignedto
     */
    public String getAssignedto() {
        return assignedto;
    }

    /**
     *
     * @param assignedto
     * The assignedto
     */
    public void setAssignedto(String assignedto) {
        this.assignedto = assignedto;
    }

    /**
     *
     * @return
     * The location
     */
    public List<Location> getLocation() {
        return location;
    }

    /**
     *
     * @param location
     * The location
     */
    public void setLocation(List<Location> location) {
        this.location = location;
    }

}
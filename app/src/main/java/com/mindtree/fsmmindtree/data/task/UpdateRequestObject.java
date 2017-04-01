package com.mindtree.fsmmindtree.data.task;

/**
 * Created by sunilbagunji on 14/12/15.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateRequestObject {
    @SerializedName("taskid")
    @Expose
    private Integer taskid;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("assignedto")
    @Expose
    private String assignedto;
    @SerializedName("barcode")
    @Expose
    private String barcode;
    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("priority")
    @Expose
    private Integer priority;
    @SerializedName("assignedby")
    @Expose
    private String assignedby;
    @SerializedName("plannedstartdate")
    @Expose
    private Long plannedstartdate;
    @SerializedName("plannedenddate")
    @Expose
    private Long plannedenddate;
    @SerializedName("actualstartdate")
    @Expose
    private Long actualstartdate;
    @SerializedName("actualenddate")
    @Expose
    private Long actualenddate;
    @SerializedName("assigneddate")
    @Expose
    private Long assigneddate;

    /**
     *
     * @return
     * The taskid
     */
    public Integer getTaskid() {
        return taskid;
    }

    /**
     *
     * @param taskid
     * The taskid
     */
    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    /**
     *
     * @return
     * The type
     */
    public Integer getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

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
     * The barcode
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     *
     * @param barcode
     * The barcode
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     *
     * @return
     * The comments
     */
    public String getComments() {
        return comments;
    }

    /**
     *
     * @param comments
     * The comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     *
     * @return
     * The priority
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     *
     * @param priority
     * The priority
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     *
     * @return
     * The assignedby
     */
    public String getAssignedby() {
        return assignedby;
    }

    /**
     *
     * @param assignedby
     * The assignedby
     */
    public void setAssignedby(String assignedby) {
        this.assignedby = assignedby;
    }

    /**
     *
     * @return
     * The plannedstartdate
     */
    public Long getPlannedstartdate() {
        return plannedstartdate;
    }

    /**
     *
     * @param plannedstartdate
     * The plannedstartdate
     */
    public void setPlannedstartdate(Long plannedstartdate) {
        this.plannedstartdate = plannedstartdate;
    }

    /**
     *
     * @return
     * The plannedenddate
     */
    public Long getPlannedenddate() {
        return plannedenddate;
    }

    /**
     *
     * @param plannedenddate
     * The plannedenddate
     */
    public void setPlannedenddate(Long plannedenddate) {
        this.plannedenddate = plannedenddate;
    }

    /**
     *
     * @return
     * The actualstartdate
     */
    public Long getActualstartdate() {
        return actualstartdate;
    }

    /**
     *
     * @param actualstartdate
     * The actualstartdate
     */
    public void setActualstartdate(Long actualstartdate) {
        this.actualstartdate = actualstartdate;
    }

    /**
     *
     * @return
     * The actualenddate
     */
    public Long getActualenddate() {
        return actualenddate;
    }

    /**
     *
     * @param actualenddate
     * The actualenddate
     */
    public void setActualenddate(Long actualenddate) {
        this.actualenddate = actualenddate;
    }

    /**
     *
     * @return
     * The assigneddate
     */
    public Long getAssigneddate() {
        return assigneddate;
    }

    /**
     *
     * @param assigneddate
     * The assigneddate
     */
    public void setAssigneddate(Long assigneddate) {
        this.assigneddate = assigneddate;
    }

}

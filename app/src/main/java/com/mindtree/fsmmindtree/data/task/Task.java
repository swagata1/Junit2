package com.mindtree.fsmmindtree.data.task;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Task {

    @SerializedName("taskid")
    @Expose
    private Integer taskid;
    @SerializedName("requesttype")
    @Expose
    private Integer requesttype;
    @SerializedName("requestid")
    @Expose
    private Integer requestid;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("assignedto")
    @Expose
    private String assignedto;

    public String getAssignedby() {
        return assignedby;
    }

    public void setAssignedby(String assignedby) {
        this.assignedby = assignedby;
    }

    @SerializedName("assignedby")
    @Expose
    private String assignedby;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("barcode")
    @Expose
    private String barcode;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("priority")
    @Expose
    private Integer priority;
    @SerializedName("comments")
    @Expose
    private String comments;
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
    @SerializedName("customername")
    @Expose
    private String customername;
    @SerializedName("customeraddress")
    @Expose
    private String customeraddress;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("buildingname")
    @Expose
    private String buildingname;
    @SerializedName("floornumber")
    @Expose
    private Integer floornumber;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("images")
    @Expose
    private List<Image> images = new ArrayList<>();

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
     * The requesttype
     */
    public Integer getRequesttype() {
        return requesttype;
    }

    /**
     *
     * @param requesttype
     * The requesttype
     */
    public void setRequesttype(Integer requesttype) {
        this.requesttype = requesttype;
    }

    /**
     *
     * @return
     * The requestid
     */
    public Integer getRequestid() {
        return requestid;
    }

    /**
     *
     * @param requestid
     * The requestid
     */
    public void setRequestid(Integer requestid) {
        this.requestid = requestid;
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
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
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
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
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

    /**
     *
     * @return
     * The customername
     */
    public String getCustomername() {
        return customername;
    }

    /**
     *
     * @param customername
     * The customername
     */
    public void setCustomername(String customername) {
        this.customername = customername;
    }

    /**
     *
     * @return
     * The customeraddress
     */
    public String getCustomeraddress() {
        return customeraddress;
    }

    /**
     *
     * @param customeraddress
     * The customeraddress
     */
    public void setCustomeraddress(String customeraddress) {
        this.customeraddress = customeraddress;
    }

    /**
     *
     * @return
     * The state
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @param state
     * The state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return
     * The city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     * The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     * The buildingname
     */
    public String getBuildingname() {
        return buildingname;
    }

    /**
     *
     * @param buildingname
     * The buildingname
     */
    public void setBuildingname(String buildingname) {
        this.buildingname = buildingname;
    }

    /**
     *
     * @return
     * The floornumber
     */
    public Integer getFloornumber() {
        return floornumber;
    }

    /**
     *
     * @param floornumber
     * The floornumber
     */
    public void setFloornumber(Integer floornumber) {
        this.floornumber = floornumber;
    }

    /**
     *
     * @return
     * The latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     * The latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     * The longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     * The longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return
     * The images
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     *
     * @param images
     * The images
     */
    public void setImages(List<Image> images) {
        this.images = images;
    }

}
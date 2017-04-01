package com.mindtree.fsmmindtree.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sunilbagunji on 08/12/15.
 */
public class LoginResponseObject {
    @SerializedName("accesstoken")
    @Expose
    private String accesstoken;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("usertype")
    @Expose
    private Integer usertype;

    /**
     *
     * @return
     * The accesstoken
     */
    public String getAccesstoken() {
        return accesstoken;
    }

    /**
     *
     * @param accesstoken
     * The accesstoken
     */
    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    /**
     *
     * @return
     * The firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     *
     * @param firstname
     * The firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     *
     * @return
     * The lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     *
     * @param lastname
     * The lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The usertype
     */
    public Integer getUsertype() {
        return usertype;
    }

    /**
     *
     * @param usertype
     * The usertype
     */
    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

}

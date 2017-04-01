package com.mindtree.fsmmindtree.common;

/**
 * Created by sunilbagunji on 04/11/15.
 */
public class AppConstants {
    public class USERS {
        public static final int USER_SUPERVISOR = 1;
        public static final int USER_TECHNICAN = 2;
        public static final int USER_CUSTOMER = 3;
        public static final int USER_TECHNICIAN_STAFF = 4;

    }

    public static final String APP_PREFERENCE = "app_prefs";

    public static final String SETTING_USERTYPE = "usertype";
    public static final String SETTING_USERNAME = "username";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String JSON_TYPE = "application/json";
    public static final String BASE_URL = "https://fieldservicemanagement.azure-mobile.net/api";

    //  Fonts
    public class Fonts {
        public static final int FONT_NORMAL = 1;
        public static final int FONT_BOLD = 2;
    }

    public static final String GCM_ID = "gcm_id";


    public static final int STATUS_UNASSIGNED = 1;
    public static final int STATUS_ASSIGNED = 2;
    public static final int STATUS_INPROGRESS = 3;
    public static final int STATUS_COMPLETED = 4;

    public static final int TYPE_ASSIGN = 1;
    public static final int TYPE_UPDATE = 2;

    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_MEDIUM = 2;
    public static final int PRIORITY_LOW = 3;

    //  SORT
    public static final String SORT_KEY = "sort_key";
    public static final int SORT_DATE = 1;
    public static final int SORT_TYPE = 2;
    public static final int SORT_PRIORITY = 3;
    public static final int SORT_STATUS = 4;
}

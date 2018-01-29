package com.skripsi.user.etm.util;

/**
 * Created by USER on 1/14/2018.
 */

public class Constant {

    /* Base url untuk get API */
    //public static final String BASE_URL = "http://iix31.rumahweb.com:2082/";
    public static final String BASE_URL = "http://yippytech.com:2028/";
    //public static final String BASE_URL = "http://192.168.43.175:2028/";

    /* url url get api untuk user */
    public static final String ENDPOINT_LOGIN = BASE_URL + "user/auth";
    public static final String ENDPOINT_CEK_EMAIL_USER = BASE_URL + "user/cek_email";
    public static final String ENDPOINT_SAVE_PASSWORD_USER = BASE_URL + "user/ganti_pass";

    /* penyimpanan untuk session login */
    public static final String KEY_SHAREDPREFS_USER = "user";
    public static final String KEY_SHAREDPREFS_LOGIN_STATUS = "statusLogin";
    public static final String KEY_SHAREDPREFS_USER_DATA = "data";
    public static final String KEY_SHAREDPREFS_SISWA_DATA = "data";
    public static final String KEY_SHAREDPREFS_TOKEN = "token";

    /* url url untuk get api siswa */
    public static final String ENDPOINT_GET_SISWA = BASE_URL + "siswa/getdata";
    public static final String ENDPOINT_GET_SISWA_BY_ID = BASE_URL + "siswa/getdata/";
    public static final String ENDPOINT_GET_SISWA_BY_SEARCH = BASE_URL + "siswa/search_data/";
    public static final String ENDPOINT_ADD_SISWA = BASE_URL + "siswa/add_siswa";
    public static final String ENDPOINT_EDIT_SISWA = BASE_URL + "siswa/edit_siswa/";
    public static final String ENDPOINT_EXPORT_SISWA = BASE_URL + "siswa/export_siswa";

    /* url url untuk get api sms marketing */
    public static final String ENDPOINT_GET_JADWAL = BASE_URL + "sms/getdata";
}

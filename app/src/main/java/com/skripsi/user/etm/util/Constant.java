package com.skripsi.user.etm.util;

/**
 * Created by USER on 1/14/2018.
 */

public class Constant {

    /* Base url untuk get API */
    public static final String BASE_URL = "http://yippytech.com:2028/";

    /* gunakan link dibawah ini apabila menggunakan server local*/
//    public static final String BASE_URL = "http://192.168.43.175:2028/";

    /* url url get api untuk user */
    public static final String ENDPOINT_LOGIN = BASE_URL + "user/auth";
    public static final String ENDPOINT_CEK_EMAIL_USER = BASE_URL + "user/cek_email";
    public static final String ENDPOINT_SAVE_PASSWORD_USER = BASE_URL + "user/ganti_pass";
    public static final String ENDPOINT_ADD_USER = BASE_URL + "user/register";

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
    public static final String ENDPOINT_IMPORT_SISWA = BASE_URL + "siswa/upload";
    public static final String ENDPOINT_GET_RELASI = BASE_URL + "siswa/ambil_relasi";

    /* url url untuk get api jadwal sms marketing */
    public static final String ENDPOINT_GET_JADWAL = BASE_URL + "sms/getdata";
    public static final String ENDPOINT_EDIT_JADWAL = BASE_URL + "sms/edit_jadwal/";

    /* url url untuk get api telemarketing */
    public static final String ENDPOINT_GET_TELEMARKETING = BASE_URL + "telemarketing/getdata";
    public static final String ENDPOINT_GETDATA_TELEMARKETING = BASE_URL + "telemarketing/getdata/";
    public static final String ENDPOINT_GET_TELEMARKETING_BY_ID = BASE_URL + "telemarketing/getdatabyid/";
    public static final String ENDPOINT_IMPORT_TELEMARKETING = BASE_URL + "telemarketing/import_tele/";
    public static final String ENDPOINT_EDIT_TELE_SISWA = BASE_URL + "telemarketing/edit_siswa/";

    /* url url untuk get api smsmarketing */
    public static final String ENDPOINT_GET_SMSMARKETING = BASE_URL + "sms/getdatasiswa";
    public static final String ENDPOINT_GETDATA_SMSMARKETING = BASE_URL + "sms/getdata/";
    public static final String ENDPOINT_GET_SMSMARKETING_BY_ID = BASE_URL + "sms/getdatasiswa/";
    public static final String ENDPOINT_IMPORT_SMSMARKETING = BASE_URL + "sms/import_tele/";
    public static final String ENDPOINT_EDIT_SMS_SISWA = BASE_URL + "sms/edit_siswa/";

    /* url url untuk get api emailmarketing */
    public static final String ENDPOINT_GET_EMAILMARKETING = BASE_URL + "emailmarketing/getdata";
    public static final String ENDPOINT_GETDATA_EMAILMARKETING = BASE_URL + "emailmarketing/getdata/";
    public static final String ENDPOINT_GET_EMAILMARKETING_BY_ID = BASE_URL + "emailmarketing/getdatabyid/";
    public static final String ENDPOINT_IMPORT_EMAILMARKETING = BASE_URL + "emailmarketing/import_tele/";
    public static final String ENDPOINT_EDIT_EMAIL_SISWA = BASE_URL + "emailmarketing/edit_siswa/";
}

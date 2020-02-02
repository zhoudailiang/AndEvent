package cn.zhoudl.eventlib;

import android.util.Log;

public class Logger {

    private static final String TAG = "AndEvent";

    private static final boolean DEBUG = true;

    public static void i(String msg) {
        if (DEBUG) {
            Log.i(TAG, msg);
        }
    }

}

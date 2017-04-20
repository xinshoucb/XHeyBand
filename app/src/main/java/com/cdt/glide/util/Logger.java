package com.cdt.glide.util;

import android.util.Log;

import com.cdt.glide.BuildConfig;

public class Logger {

    private static final String TAG = "Logger";
    public static int LOGLEVEL = BuildConfig.DEBUG ? 5 : 0;
    public static boolean VERBOSE = LOGLEVEL > 4;
    public static boolean DEBUG = true;
    public static boolean INFO = LOGLEVEL > 2;
    public static boolean WARN = LOGLEVEL > 1;
    public static boolean ERROR = true;

    public static void setDebugMode(boolean debugMode){
        LOGLEVEL = debugMode ? 5 : 0;
        VERBOSE = LOGLEVEL > 4;
        DEBUG = LOGLEVEL > 3;
        INFO = LOGLEVEL > 2;
        WARN = LOGLEVEL > 1;
        ERROR = LOGLEVEL > 0;
    }

    public static void v(String tag, String msg) {
        if (DEBUG)
            Log.v(tag, msg == null ? "" : msg);
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (DEBUG)
            Log.v(tag, msg == null ? "" : msg, tr);
    }

    public static void v(String msg) {
        if (DEBUG)
            Log.v(TAG, msg == null ? "" : msg);
    }

    public static void v(String msg, Throwable tr) {
        if (DEBUG)
            Log.v(TAG, msg == null ? "" : msg, tr);
    }

    public static void d(String tag, String msg) {
        if (DEBUG)
            Log.d(tag, msg == null ? "" : msg);
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (DEBUG)
            Log.d(tag, msg == null ? "" : msg, tr);
    }

    public static void d(String msg) {
        if (DEBUG)
            Log.d(TAG, msg == null ? "" : msg);
    }

    public static void d(String msg, Throwable tr) {
        if (DEBUG)
            Log.d(TAG, msg == null ? "" : msg, tr);
    }

    public static void e(String tag, String msg) {
        if (ERROR)
            Log.e(tag, msg == null ? "" : msg);
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (ERROR)
            Log.e(tag, msg == null ? "" : msg, tr);
    }

    public static void e(String msg) {
        if (ERROR)
            Log.e(TAG, msg == null ? "" : msg);
    }

    public static void e(String msg, Throwable tr) {
        if (ERROR)
            Log.e(TAG, msg == null ? "" : msg, tr);
    }

    /**
     * 使用Log来显示调试信息,因为log在实现上每个message有4k字符长度限制
     * 所以这里使用自己分节的方式来输出足够长度的message
     *
     * @param tag
     * @param str void
     */
    public static void debugLongInfo(String tag, String str) {
        if (!DEBUG) return;

        str = str.trim();
        int index = 0;
        int maxLength = 3500;
        String sub;
        while (index < str.length()) {
            // java的字符不允许指定超过总的长度end
            if (str.length() <= index + maxLength) {
                sub = str.substring(index);
            } else {
                sub = str.substring(index, index + maxLength);
            }

            index += maxLength;
            Log.d(tag, sub.trim());
        }
    }

}

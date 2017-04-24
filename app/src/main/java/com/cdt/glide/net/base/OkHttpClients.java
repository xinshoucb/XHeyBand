package com.cdt.glide.net.base;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class OkHttpClients {


    private static OkHttpClient mClient;
    private static int CONNECT_TIMEOUT = 15000;

    private OkHttpClients() {
    }

    public static OkHttpClient getOkHttpClient() {
        if (mClient == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            mClient = new OkHttpClient
                    .Builder()
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                    .readTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                    .writeTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                    .addInterceptor(loggingInterceptor)
                    .build();
        }
        return mClient;
    }
}

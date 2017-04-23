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
            // TODO: David 6/15/16 这里也需要能够在请求的时候自定义配置level，而且能够在runtime 配置
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

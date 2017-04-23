package com.cdt.glide.net.base;

import java.lang.reflect.ParameterizedType;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * 作为客户端 网络连接的顶层类,子类通过泛型传入endpoint，代表一个网络请求服务。
 * 每一个子类下面的所有请求都应该属于同一个host的请求。
 * 子类应该都是单例，而且最好在启动的时候初始化。
 */
public abstract class BaseClient<T> {


    private final T endpoint;

    protected BaseClient(String baseUrl) {

        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class<T> type = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        endpoint = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
//                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(OkHttpClients.getOkHttpClient())
                .build()
                .create(type);

    }

    /**
     * 注意，默认的 BaseURl 是 OFFICIAL_YOUKU_DOMAIN,
     * 如果当前请求的base-url(host) 不同，需要在请求上方添加 headers("base-url: www.youku.com...")
     * 拿到endpoint 之后，直接执行就会去请求并自动反序列化了
     * @return
     */
    public T getEndpoint() {
        return endpoint;
    }
}

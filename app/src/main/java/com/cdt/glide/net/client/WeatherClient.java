package com.cdt.glide.net.client;

import com.cdt.glide.net.Entity.WeatherJson;
import com.cdt.glide.net.base.BaseClient;
import com.cdt.glide.net.endpoint.WeatherEndpoint;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WeatherClient extends BaseClient<WeatherEndpoint> {

    public WeatherClient(String baseUrl) {
        super(baseUrl);
    }

    public Observable<WeatherJson> getWeather(String cityId){
        return getEndpoint().getWeather(cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    };

}

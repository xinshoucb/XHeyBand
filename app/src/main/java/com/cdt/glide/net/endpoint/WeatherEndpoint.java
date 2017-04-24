package com.cdt.glide.net.endpoint;

import com.cdt.glide.net.Entity.WeatherJson;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface WeatherEndpoint {
    @GET("adat/sk/{cityId}.html")
    Observable<WeatherJson> getWeather(@Path("cityId") String cityId);
}

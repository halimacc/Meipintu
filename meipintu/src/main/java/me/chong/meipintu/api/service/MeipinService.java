package me.chong.meipintu.api.service;


import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Chong on 2015/10/4.
 */
public interface MeipinService {
    @GET("index")
    Observable<String> getMeipinItems(@Query("idx") int skip);
}

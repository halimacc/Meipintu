package me.chong.meipintu.api.service;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Chong on 2015/10/4.
 */
public interface MeipinService {

    @GET("/index?idx={skip}")
    Observable<String> getMeipinItems(@Path("skip") int skip);
}

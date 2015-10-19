package me.chong.meipintu.data.api.service;

import android.support.annotation.StringRes;

import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Chong on 2015/10/4.
 */
public interface MeipinService {

    @GET("index")
    Observable<String> getMeipinItems(@Query("idx") int skip);
}

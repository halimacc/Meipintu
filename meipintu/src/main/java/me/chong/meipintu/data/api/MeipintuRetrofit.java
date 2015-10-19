package me.chong.meipintu.data.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

import me.chong.meipintu.data.api.assist.StringConverterFactory;
import me.chong.meipintu.data.api.service.MeipinService;
import me.chong.meipintu.data.model.MeipinItem;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Chong on 2015/10/5.
 */
public final class MeipintuRetrofit implements Meipintu {
    public static final String BASE_URL = "http://meipintu.com/";

    private MeipinService meipinService;

    private static volatile MeipintuRetrofit instance;

    public static MeipintuRetrofit getInstance() {
        if (instance == null) {
            synchronized (MeipintuRetrofit.class) {
                if (instance == null) {
                    instance = new MeipintuRetrofit();
                }
            }
        }
        return instance;
    }

    private MeipintuRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(StringConverterFactory.create())
                .baseUrl(HttpUrl.parse(BASE_URL))
                .build();

        meipinService = retrofit.create(MeipinService.class);
    }

    @Override
    public Observable<List<MeipinItem>> getMeipinItem(int skip) {
        return meipinService.getMeipinItems(skip)
                .map(MeipinItem::fromHtml)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

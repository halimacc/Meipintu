package me.chong.meipintu.api;

import retrofit.Retrofit;

/**
 * Created by Chong on 2015/10/4.
 */
public class ConnectionManager {
    public static final String BASE_URL = "http://meipintu.com";

    private static volatile Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (Retrofit.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .build();
                }
            }
        }
        return retrofit;
    }

    public <T> T createService(final Class<T> service) {
        return getRetrofit().create(service);
    }
}

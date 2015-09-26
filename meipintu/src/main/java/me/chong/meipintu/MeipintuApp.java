package me.chong.meipintu;

import android.app.Application;

import dagger.Component;

/**
 * Created by Chong on 2015/9/26.
 */

public final class MeipintuApp extends Application {
    private MeipintuComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerMeipintuComponent.builder()
                .meipintuModule(new MeipintuModule(this))
                .build();
    }

    public MeipintuComponent getComponent(){
        return component;
    }
}

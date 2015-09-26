package me.chong.meipintu;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.chong.meipintu.ui.UiModule;

/**
 * Created by Chong on 2015/9/26.
 */
@Module
public final class MeipintuModule {
    private final MeipintuApp app;

    public MeipintuModule(MeipintuApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return app;
    }
}

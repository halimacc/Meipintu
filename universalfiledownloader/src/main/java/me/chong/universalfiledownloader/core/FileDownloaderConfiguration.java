package me.chong.universalfiledownloader.core;

import android.content.Context;
import android.content.res.Resources;

import java.util.concurrent.Executor;

/**
 * Created by Chong on 2015/9/30.
 */
public final class FileDownloaderConfiguration {

    final Resources resources;

    final Executor taskExecutor;
    final boolean customExecutor;

    public FileDownloaderConfiguration(Builder builder) {
        resources = builder.context.getResources();
        taskExecutor = builder.taskExecutor;
        customExecutor = builder.customExecutor;
    }

    public static class Builder {
        private Context context;

        /** {@value} */
        public static final int DEFAULT_THREAD_POOL_SIZE = 3;
        /** {@value} */
        public static final int DEFAULT_THREAD_PRIORITY = Thread.NORM_PRIORITY - 2;

        private Executor taskExecutor = null;
        private boolean customExecutor = false;

        private int threadPoolSize = DEFAULT_THREAD_POOL_SIZE;
        private int threadPriority = DEFAULT_THREAD_PRIORITY;

        public Builder(Context context) {
            this.context = context.getApplicationContext();
        }

        public FileDownloaderConfiguration build() {
            initEmptyFieldWithDefaultValues();
            return new FileDownloaderConfiguration(this);
        }

        private void initEmptyFieldWithDefaultValues() {
            if (taskExecutor == null) {
                taskExecutor = DefaultConfigurationFactory
                        .createExecutor(threadPoolSize, threadPriority);
            } else {
                customExecutor = true;
            }


        }
    }
}

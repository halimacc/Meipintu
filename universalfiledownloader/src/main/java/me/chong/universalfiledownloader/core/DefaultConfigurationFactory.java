package me.chong.universalfiledownloader.core;

import android.support.annotation.NonNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Chong on 2015/9/30.
 */
public class DefaultConfigurationFactory {

    /**
     * Creates default implementation of task executor
     */
    public static Executor createExecutor(int threadPoolSize, int threadPriority) {
        BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();
        return new ThreadPoolExecutor(threadPoolSize, threadPoolSize, 0L, TimeUnit.MILLISECONDS, taskQueue,
                createThreadFactory(threadPriority, "ufd-pool-"));
    }


    /**
     * Creates default implementation of {@linkplain ThreadFactory thread factory} for task executor
     */
    private static ThreadFactory createThreadFactory(int threadPriority, String threadNamePrefix) {
        return new DefaultThreadFactory(threadPriority, threadNamePrefix);
    }


    private static class DefaultThreadFactory implements ThreadFactory {

        private static final AtomicInteger poolNumber = new AtomicInteger(1);

        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;
        private final int threadPriority;

        DefaultThreadFactory(int threadPriority, String threadNamePrefix) {
            this.threadPriority = threadPriority;
            group = Thread.currentThread().getThreadGroup();
            namePrefix = threadNamePrefix + poolNumber.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(@NonNull Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon()) t.setDaemon(false);
            t.setPriority(threadPriority);
            return t;
        }
    }
}

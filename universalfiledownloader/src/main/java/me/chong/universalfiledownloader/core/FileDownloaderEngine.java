package me.chong.universalfiledownloader.core;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Chong on 2015/10/1.
 */
public class FileDownloaderEngine {

    final FileDownloaderConfiguration configuration;

    private Executor taskExecutor;

    private final AtomicBoolean paused = new AtomicBoolean(false);
    private final AtomicBoolean networkDenied = new AtomicBoolean(false);
    private final AtomicBoolean slowNetwork = new AtomicBoolean(false);

    private final Object pauseLock = new Object();

    FileDownloaderEngine(FileDownloaderConfiguration configuration) {
        this.configuration = configuration;
        taskExecutor = configuration.taskExecutor;
    }


}

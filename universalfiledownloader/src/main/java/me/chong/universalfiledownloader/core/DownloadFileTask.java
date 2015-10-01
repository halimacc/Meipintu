package me.chong.universalfiledownloader.core;

import java.util.logging.Handler;

/**
 * Created by Chong on 2015/10/1.
 */
public class DownloadFileTask implements Runnable {

    private final FileDownloaderEngine engine;
    private final FileDownloadingInfo fileDownloadingInfo;
    private final Handler handler;

    public DownloadFileTask(FileDownloaderEngine engine, FileDownloadingInfo fileDownloadingInfo, Handler handler) {
        this.engine = engine;
        this.fileDownloadingInfo = fileDownloadingInfo;
        this.handler = handler;
    }

    @Override
    public void run() {

    }
}

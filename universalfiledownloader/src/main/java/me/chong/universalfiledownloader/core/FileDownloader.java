package me.chong.universalfiledownloader.core;

import android.util.Log;

/**
 * Created by Chong on 2015/9/30.
 */
public class FileDownloader {

    public static final String TAG = FileDownloader.class.getSimpleName();

    private static final String LOG_INIT_CONFIG = "Initialize FileDownloader with configuration";

    private static final String WARNING_RE_INIT_CONFIG = "Try to initialize FileDownloader which had already been initialized before. " + "To re-init FileDownloader with new configuration call FileDownloader.destroy() at first.";

    private static final String ERROR_INIT_CONFIG_WITH_NULL = "FileDownloader configuration can not be initialized with null";

    public static volatile FileDownloader instance;
    private FileDownloaderConfiguration configuration;


    public static FileDownloader getInstance() {
        if (instance == null) {
            synchronized (FileDownloader.class) {
                if (instance == null) {
                    instance = new FileDownloader();
                }
            }
        }
        return instance;
    }

    protected FileDownloader() {
    }

    public synchronized void init(FileDownloaderConfiguration configuration) {
        if (configuration == null) {
            throw new IllegalArgumentException(ERROR_INIT_CONFIG_WITH_NULL);
        }
        if (this.configuration == null) {
            Log.d(TAG, LOG_INIT_CONFIG);
            this.configuration = configuration;
        } else {
            Log.w(TAG, WARNING_RE_INIT_CONFIG);
        }
    }

    public void downloadFile(String uri, String path) {

    }

}

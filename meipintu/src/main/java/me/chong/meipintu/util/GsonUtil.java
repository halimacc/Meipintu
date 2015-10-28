package me.chong.meipintu.util;

import com.google.gson.Gson;

import java.util.HashMap;


/**
 * Created by Chong on 2015/10/28.
 */
public class GsonUtil {
    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> clz) {
        return new Gson().fromJson(json, clz);
    }
}

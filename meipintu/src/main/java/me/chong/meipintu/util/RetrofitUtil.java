package me.chong.meipintu.util;

import java.lang.annotation.Annotation;

/**
 * Created by Chong on 2015/10/5.
 */
public class RetrofitUtil {
    /**
     * Returns true if {@code annotations} contains an instance of {@code cls}.
     */
    public static boolean isAnnotationPresent(Annotation[] annotations,
                                       Class<? extends Annotation> cls) {
        for (Annotation annotation : annotations) {
            if (cls.isInstance(annotation)) {
                return true;
            }
        }
        return false;
    }
}

package me.chong.meipintu.util;

import android.content.Context;

/**
 * Created by Chong on 2015/11/25.
 */
public class SizeUtil {

    public static int dp2px(Context context, float dipValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }

    public static int px2dp(Context context, float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }
}

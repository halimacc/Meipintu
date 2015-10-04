package me.chong.meipintu.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chong on 2015/9/29.
 */
public class MeipinItem {
    public String title;
    public String pictureUri;

    public static List<MeipinItem> generateData() {
        List<MeipinItem> items = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            MeipinItem item = new MeipinItem();
            item.pictureUri = "http://a3.att.hudong.com/55/30/19300001363332131650303124952_s.jpg";
            items.add(item);
        }
        return items;
    }
}

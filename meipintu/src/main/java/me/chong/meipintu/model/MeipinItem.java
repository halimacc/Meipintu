package me.chong.meipintu.model;

import android.net.Uri;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chong on 2015/9/29.
 */
public class MeipinItem {
    public String id;
    public String title;
    public boolean isGif;
    public String thumbnailUri;
    public String uri;

    public static List<MeipinItem> fromHtml(String html) {
        List<MeipinItem> items = new ArrayList<>();

        Elements elements = Jsoup.parse(html)
                .body()
                .getElementsByClass("feed_item");

        for (Element element : elements) {
            items.add(new MeipinItem(element));
        }

        return items;
    }

    // parse from html element div#feed_item
    public MeipinItem(Element root) {
        id = root.attr("id");

        Element imga = root.child(0)  // div#item_inner
                .child(0)   // div#item_img
                .child(0);  // a

        this.thumbnailUri = imga.child(0)   // img
                .attr("src");

        if (imga.attributes().hasKey("onmouseover")) {
            String tmp = imga.attr("onmouseover");
            int start = tmp.indexOf(",") + 2;
            int end = tmp.indexOf("'", start);
            this.uri = tmp.substring(start, end);
            this.isGif = true;
        } else this.uri = this.thumbnailUri;

        Element item_info = root.child(0)   // div#item_inner
                .child(1);  // div#item_info

        this.title = item_info.child(1)  // h3
                .child(0)   // a
                .attr("title");
    }
}

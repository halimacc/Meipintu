package me.chong.meipintu.data.model;

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
    public String pictureUri;

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
        pictureUri = root.child(0)  // div#iten_inner
                .child(0)   // div#item_img
                .child(0)   // a
                .child(0)   // img
                .attr("src");

        Element item_info = root.child(0)   // div#item_inner
                .child(1);  // div#item_info

        title = item_info.child(1)  // h3
                .child(0)   // a
                .attr("title");
    }
}

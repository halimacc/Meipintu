package me.chong.meipintu.data.api;

import java.util.List;

import me.chong.meipintu.data.model.MeipinItem;
import rx.Observable;

/**
 * Created by Chong on 2015/10/5.
 */
public interface Meipintu {
    Observable<List<MeipinItem>> getMeipinItem(int skip);
}

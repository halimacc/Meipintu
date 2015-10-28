package me.chong.meipintu.api;

import java.util.List;

import me.chong.meipintu.model.MeipinItem;
import rx.Observable;

/**
 * Created by Chong on 2015/10/5.
 */
public interface Meipintu {
    Observable<List<MeipinItem>> getMeipinItem(int skip);
}

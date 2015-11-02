package me.chong.meipintu.ui.view;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import me.chong.meipintu.ui.PictureActivity;

/**
 * Created by conra on 2015/10/31.
 */
public class CustomProgressBar extends Drawable {

    private int level = 0;
    private Paint paint_bg;

    public CustomProgressBar() {

    }

    @Override
    protected boolean onLevelChange(int level) {
        this.level = level;
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        canvas.drawCircle(width / 2, height / 2, 100, paint_bg);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }
}

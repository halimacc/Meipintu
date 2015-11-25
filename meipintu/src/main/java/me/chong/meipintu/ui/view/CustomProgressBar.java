package me.chong.meipintu.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import me.chong.meipintu.R;
import me.chong.meipintu.util.SizeUtil;

/**
 * Created by conra on 2015/10/31.
 */
public class CustomProgressBar extends Drawable {

    private static final int RADIUS_OUT_DP = 20;
    private static final int RADIUS_IN_DP = 18;

    private Paint paintF = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paintB = new Paint(Paint.ANTI_ALIAS_FLAG);

    private int radiusOutPx;
    private int radiusInPx;
    private RectF rectArc;
    private int level = 0;

    public CustomProgressBar(Context context) {
        this.radiusOutPx = SizeUtil.dp2px(context, RADIUS_OUT_DP);
        this.radiusInPx = SizeUtil.dp2px(context, RADIUS_IN_DP);

        paintB.setAlpha(50);
        paintB.setColor(context.getResources().getColor(R.color.primary_gray));

        paintF.setAlpha(66);
        paintF.setColor(Color.WHITE);
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
        if (rectArc == null)
            rectArc = new RectF(width / 2 - radiusInPx, height / 2 -radiusInPx, width / 2 + radiusInPx, height / 2 + radiusInPx);
        else rectArc.set(width / 2 - radiusInPx, height / 2 -radiusInPx, width / 2 + radiusInPx, height / 2 + radiusInPx);

        canvas.drawCircle(width / 2, height / 2, radiusOutPx, paintB);
        paintF.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(width / 2, height / 2, radiusOutPx, paintF);
        paintF.setStyle(Paint.Style.FILL);
        canvas.drawArc(rectArc, 0, this.level  * 3.6f, true, paintF);
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

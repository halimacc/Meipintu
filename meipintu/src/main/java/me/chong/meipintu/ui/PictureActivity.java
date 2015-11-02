package me.chong.meipintu.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import me.chong.meipintu.R;
import me.chong.meipintu.model.MeipinItem;
import me.chong.meipintu.ui.view.ZoomableDraweeView;
import me.chong.meipintu.util.GsonUtil;

public class PictureActivity extends AppCompatActivity {
    public static final String ARG_MEIPIN_ITEM = "arg_meipin_item";

    private ZoomableDraweeView sdv_picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        sdv_picture = (ZoomableDraweeView) findViewById(R.id.sdv_picture);

        MeipinItem item = GsonUtil.fromJson(getIntent().getStringExtra(ARG_MEIPIN_ITEM), MeipinItem.class);

        DraweeController ctrl = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse(item.pictureUri))
                .setAutoPlayAnimations(true)
                .build();

        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources())
                .setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER)
                .setProgressBarImage(new ProgressBarDrawable())
                .build();

        sdv_picture.setController(ctrl);
        sdv_picture.setHierarchy(hierarchy);

    }

}

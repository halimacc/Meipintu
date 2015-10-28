package me.chong.meipintu.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import me.chong.meipintu.R;
import me.chong.meipintu.model.MeipinItem;
import me.chong.meipintu.util.GsonUtil;

public class PictureActivity extends AppCompatActivity {
    public static final String ARG_MEIPIN_ITEM = "arg_meipin_item";

    private SimpleDraweeView sdv_picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        sdv_picture = (SimpleDraweeView) findViewById(R.id.sdv_picture);

        MeipinItem item = GsonUtil.fromJson(getIntent().getStringExtra(ARG_MEIPIN_ITEM), MeipinItem.class);
        sdv_picture.setImageURI(Uri.parse(item.pictureUri));
    }

}

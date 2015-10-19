package me.chong.meipintu.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import me.chong.meipintu.R;
import me.chong.meipintu.data.model.MeipinItem;

/**
 * Created by Chong on 2015/9/29.
 */
public class MeipinItemAdapter extends RecyclerView.Adapter<MeipinItemAdapter.ViewHolder> {

    private Fragment fragment;
    private List<MeipinItem> mItems;

    public MeipinItemAdapter(Fragment fragment) {
        this.fragment = fragment;
        this.mItems = new ArrayList<>();
    }

    public void addAll(int index, List<MeipinItem> items) {
        this.mItems.addAll(index, items);
        notifyItemRangeInserted(index, items.size());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MeipinItem item = mItems.get(position);
        holder.tv_title.setText(item.title);
        Glide.with(fragment)
                .load(item.pictureUri)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(holder.iv_picture);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meipin, parent, false);
        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_picture;
        private TextView tv_title;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            iv_picture = (ImageView) itemView.findViewById(R.id.iv_picture);
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

}

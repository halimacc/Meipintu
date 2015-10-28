package me.chong.meipintu.ui.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import me.chong.meipintu.R;
import me.chong.meipintu.model.MeipinItem;
import me.chong.meipintu.ui.PictureActivity;
import me.chong.meipintu.util.GsonUtil;

/**
 * Created by Chong on 2015/9/29.
 */
public class MeipinItemAdapter extends RecyclerView.Adapter<MeipinItemAdapter.ViewHolder> implements View.OnClickListener {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = TYPE_ITEM + 1;

    private Fragment fragment;
    private List<MeipinItem> mItems;
    private OnLoadMoreListener mListener;
    private View item_load_more;

    public MeipinItemAdapter(Fragment fragment, OnLoadMoreListener listener) {
        this.fragment = fragment;
        this.mItems = new ArrayList<>();
        this.mListener = listener;
    }

    public void addAll(int index, List<MeipinItem> items) {
        this.mItems.addAll(index, items);
        notifyItemRangeInserted(index, items.size());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (!isFooter(position)) {
            MeipinItem item = mItems.get(position);
            holder.tv_title.setText(item.title);
            holder.sdv_picture.setImageURI(Uri.parse(item.pictureUri));
            holder.itemView.setTag(R.id.tag_key, item);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isFooter(position))
            return TYPE_FOOTER;
        return TYPE_ITEM;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            default:
            case TYPE_ITEM:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meipin, parent, false);
                view.setOnClickListener(this);
                return new ViewHolder(false, view);
            case TYPE_FOOTER:
                item_load_more = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_load_more, parent, false);
                item_load_more.setOnClickListener(this);
                return new ViewHolder(true, item_load_more);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == item_load_more) {
            if (mListener != null)
                mListener.onLoadMore();
            return;
        }
        int id = v.getId();
        if (id == R.id.cv_meipin_item) {
            MeipinItem item = (MeipinItem) v.getTag(R.id.tag_key);
            Intent intent = new Intent(fragment.getContext(), PictureActivity.class);
            intent.putExtra(PictureActivity.ARG_MEIPIN_ITEM, GsonUtil.toJson(item));
            fragment.getContext().startActivity(intent);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public boolean isFooter;
        public SimpleDraweeView sdv_picture;
        public TextView tv_title;

        public ViewHolder(boolean isFooter, View itemView) {
            super(itemView);
            this.isFooter = isFooter;
            if (!this.isFooter) {
                tv_title = (TextView) itemView.findViewById(R.id.tv_title);
                sdv_picture = (SimpleDraweeView) itemView.findViewById(R.id.sdv_picture);
                sdv_picture.getHierarchy().setProgressBarImage(new ProgressBarDrawable());
            }
        }
    }

    private boolean isFooter(int position) {
        return position == mItems.size();
    }

    @Override
    public int getItemCount() {
        return mItems.size() + 1;
    }


    public interface OnLoadMoreListener {
        void onLoadMore();
    }

}

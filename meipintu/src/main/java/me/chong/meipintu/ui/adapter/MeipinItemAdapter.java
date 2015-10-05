package me.chong.meipintu.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

import me.chong.meipintu.R;
import me.chong.meipintu.data.model.MeipinItem;

/**
 * Created by Chong on 2015/9/29.
 */
public class MeipinItemAdapter extends RecyclerView.Adapter<MeipinItemAdapter.ViewHolder> {

    private List<MeipinItem> mItems;

    public MeipinItemAdapter() {
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
        Ion.with(holder.iv_picture)
                .load(item.pictureUri);
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

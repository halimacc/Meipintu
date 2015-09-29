package me.chong.meipintu.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.chong.meipintu.R;
import me.chong.meipintu.data.model.MeipinItem;

/**
 * Created by Chong on 2015/9/29.
 */
public class MeipinItemAdapter extends RecyclerView.Adapter<MeipinItemAdapter.ViewHolder>{

    private List<MeipinItem> mItems;

    public MeipinItemAdapter() {
        this.mItems = new ArrayList<>();
    }

    public MeipinItemAdapter(List<MeipinItem> items) {
        this.mItems = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meipin, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}

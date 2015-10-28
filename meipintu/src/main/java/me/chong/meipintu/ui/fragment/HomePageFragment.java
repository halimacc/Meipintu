package me.chong.meipintu.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.chong.meipintu.R;
import me.chong.meipintu.api.Meipintu;
import me.chong.meipintu.api.MeipintuRetrofit;
import me.chong.meipintu.ui.adapter.MeipinItemAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment {

    private MeipinItemAdapter meipinItemAdapter;

    private Meipintu meipintu;
    private int currentIndex = 0;

    public HomePageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, null);
        initList(view);

        if (meipintu == null) {
            meipintu = MeipintuRetrofit.getInstance();
        }

        meipintu.getMeipinItem(currentIndex)
                .subscribe(meipinItems -> meipinItemAdapter.addAll(0, meipinItems),
                        Throwable::printStackTrace);

        return view;
    }

    private void initList(View view) {
        RecyclerView rv_meipin_list = (RecyclerView) view.findViewById(R.id.rv_meipin_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv_meipin_list.setLayoutManager(layoutManager);
        meipinItemAdapter = new MeipinItemAdapter(this, this::loadMoreItems);
        rv_meipin_list.setAdapter(meipinItemAdapter);
    }

    private void loadMoreItems() {
        int PAGE_SIZE = 5;
        currentIndex += PAGE_SIZE;
        meipintu.getMeipinItem(currentIndex)
                .subscribe(meipinItems -> meipinItemAdapter.addAll(meipinItemAdapter.getItemCount() - 1, meipinItems),
                        Throwable::printStackTrace);
    }

}

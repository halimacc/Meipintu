package me.chong.meipintu.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import me.chong.meipintu.R;
import me.chong.meipintu.api.Meipintu;
import me.chong.meipintu.api.MeipintuRetrofit;
import me.chong.meipintu.ui.adapter.MeipinItemAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment {

    private static final int PAGE_SIZE = 5;

    private MeipinItemAdapter meipinItemAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

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

        meipinItemAdapter.loading(true);
        meipintu.getMeipinItem(currentIndex)
                .subscribe(meipinItems -> {
                            meipinItemAdapter.addAll(0, meipinItems);
                            mLayoutManager.scrollToPosition(0);
                        },
                        Throwable::printStackTrace,
                        () -> meipinItemAdapter.loading(false)
                );

        return view;
    }

    private void initList(View view) {
        RecyclerView rv_meipin_list = (RecyclerView) view.findViewById(R.id.rv_meipin_list);
        mLayoutManager = new LinearLayoutManager(getActivity());
        rv_meipin_list.setLayoutManager(mLayoutManager);
        meipinItemAdapter = new MeipinItemAdapter(this, this::loadMoreItems);
        rv_meipin_list.setAdapter(meipinItemAdapter);
    }

    private void loadMoreItems() {
        if (meipinItemAdapter.isLoading())
            return;
        currentIndex += PAGE_SIZE;
        meipinItemAdapter.loading(true);
        meipintu.getMeipinItem(currentIndex)
                .subscribe(meipinItems -> meipinItemAdapter.addAll(meipinItemAdapter.getItemCount() - 1, meipinItems),
                        Throwable::printStackTrace,
                        () -> meipinItemAdapter.loading(false)
                );
    }

}

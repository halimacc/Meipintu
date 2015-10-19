package me.chong.meipintu.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.chong.meipintu.R;
import me.chong.meipintu.data.api.Meipintu;
import me.chong.meipintu.data.api.MeipintuRetrofit;
import me.chong.meipintu.ui.adapter.MeipinItemAdapter;
import rx.functions.Action0;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment {

    private final int PAGE_SIZE = 5;

    private MeipinItemAdapter meipinItemAdapter;
    private RecyclerView.LayoutManager layoutManager;

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

    private boolean loading = false;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    private void initList(View view) {
        RecyclerView rv_meipin_list = (RecyclerView) view.findViewById(R.id.rv_meipin_list);
        layoutManager = new LinearLayoutManager(getActivity());
        rv_meipin_list.setLayoutManager(layoutManager);
        meipinItemAdapter = new MeipinItemAdapter(this);
        rv_meipin_list.setAdapter(meipinItemAdapter);
        rv_meipin_list.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = layoutManager.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                pastVisiblesItems = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();

                if (!loading) {
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        loading = true;
                        currentIndex += PAGE_SIZE;
                        meipintu.getMeipinItem(currentIndex)
                                .subscribe(meipinItems -> {
                                            meipinItemAdapter.addAll(meipinItems.size() - 1, meipinItems);
                                        }, Throwable::printStackTrace,
                                        () -> loading = false);
                    }
                }
            }
        });
    }

}

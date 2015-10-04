package me.chong.meipintu.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.chong.meipintu.R;
import me.chong.meipintu.model.MeipinItem;
import me.chong.meipintu.ui.adapter.MeipinItemAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment {

    private RecyclerView rv_meipin_list;

    public HomePageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, null);
        rv_meipin_list = (RecyclerView) view.findViewById(R.id.rv_meipin_list);
        rv_meipin_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_meipin_list.setAdapter(new MeipinItemAdapter(MeipinItem.generateData()));

        return view;
    }


}

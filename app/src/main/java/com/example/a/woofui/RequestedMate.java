package com.example.a.woofui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by apple on 2018/3/4.
 */

public class RequestedMate extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycle_view_list,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        String[] data=new String[]{"A","A","A","A","A","A","A","A","A","A"};
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(data,R.layout.list_item_requested);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }
}

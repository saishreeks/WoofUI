package com.example.a.woofui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by apple on 2018/3/4.
 */

public class PostMate extends Fragment {


    public PostMate(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.post_mate,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        String[] data=new String[]{"A","A","A","A","A","A","A","A","A","A"};
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(data, R.layout.list_item_post_mate);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        FloatingActionButton btn_add = view.findViewById(R.id.postBtn);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostMateInfo pmi_dialog = new PostMateInfo();
                pmi_dialog.show(getFragmentManager(),"dialog");
            }
        });
        return view;
    }
}

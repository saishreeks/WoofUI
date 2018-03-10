package com.example.a.woofui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.api.ApiVolley;
import com.example.a.model.WalkInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by saishree on 2/27/18.
 */

public class HistoryDogMate extends Fragment {

    private List<WalkInfo> walkerName;
    private WalkInfo[] walkInfoList;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ApiVolley api = new ApiVolley(getActivity().getApplicationContext());
        WalkInfo walkInfo = new WalkInfo();
        api.getMateInfo(this, walkInfo);
        View rootView = inflater.inflate(R.layout.fragment_history_dogwalk, container, false);
        walkerName = new ArrayList<>();
        recyclerView = rootView.findViewById(R.id.recyclerView_history_walk);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        return rootView;
    }


    public void displayInUI(WalkInfo[] walkInfo) {
        walkInfoList = walkInfo;
        walkerName = Arrays.asList(walkInfo);
        adapter.notifyDataSetChanged();
    }

    private String dateFormatter(Date date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy");
        return formatter.format(date).toString();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private CardView mCardView;
        TextView walker;
        TextView walked;
        TextView walkDate;
        TextView walkTime;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.dog_walk_card);
            walker = (TextView) itemView.findViewById(R.id.walker_name_history);
            walked = (TextView) itemView.findViewById(R.id.walked_name);
            walkDate = (TextView) itemView.findViewById(R.id.history_walk_date);
            walkTime = (TextView) itemView.findViewById(R.id.history_walk_time);
        }
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {


        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dog_walk_history_card, viewGroup, false);
            RecyclerViewHolder rvh = new RecyclerViewHolder(v);
            return rvh;
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int i) {
            String date = null, time = null;
            if (walkerName.size() > 0) {
                holder.walker.setText(walkInfoList[i].getWalkerId().getName());
                holder.walked.setText(walkInfoList[i].getDogId().getName());
                try {
                    date = walkInfoList[i].getWalkInfoDate() == null ? null : dateFormatter(walkInfoList[i].getWalkInfoDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                holder.walkDate.setText(date);
                try {
                    time = walkInfoList[i].getWalkTime() == null ? null : dateFormatter(walkInfoList[i].getWalkTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                holder.walkTime.setText(time);
            }
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public int getItemCount() {
            return walkerName.size();
        }
    }
}
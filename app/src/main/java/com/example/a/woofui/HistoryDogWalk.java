package com.example.a.woofui;

/**
 * Created by saishree on 2/27/18.
 */

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a.api.ApiVolley;
import com.example.a.model.WalkInfo;
import com.squareup.picasso.Callback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class HistoryDogWalk extends Fragment {


    private List<WalkInfo> walkerName;
    private WalkInfo[] walkInfoList;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    ImageView userImage;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ApiVolley api = new ApiVolley(getActivity().getApplicationContext());
        WalkInfo walkInfo = new WalkInfo();
        api.getWalkersInfo(this, walkInfo);
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
            userImage = (ImageView) itemView.findViewById(R.id.profile_image);
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
        public void onBindViewHolder(final RecyclerViewHolder holder, int i) {
            String date = null, time = null;
            if (walkerName.size() > 0) {
                String imagePath = getString(R.string.picDownload_api) + "/" +walkInfoList[i].getWalkerId().getOwnerId();
//findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                Glide.with(getContext()).load(imagePath).into(userImage);
//                Picasso.with(getContext()).load(imagePath).into(userImage, new Callback() {
//                    @Override
//                    public void onSuccess() {
//                        holder.itemView.findViewById(R.id.loadingPanel_history).setVisibility(View.GONE);
//                    }
//
//                    @Override
//                    public void onError() {
//
//                    }
//                });
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                },5000);


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

        private void finish() {
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

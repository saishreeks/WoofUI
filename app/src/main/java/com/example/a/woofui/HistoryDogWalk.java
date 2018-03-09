package com.example.a.woofui;

/**
 * Created by saishree on 2/27/18.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a.api.ApiVolley;
import com.example.a.model.WalkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryDogWalk extends Fragment {


    private List<String> walkHistoryDetails;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ApiVolley api = new ApiVolley(getActivity().getApplicationContext());
        WalkInfo walkInfo = new WalkInfo();
        api.getWalkersInfo(this, walkInfo);
        View rootView = inflater.inflate(R.layout.fragment_history_dogwalk, container, false);
        walkHistoryDetails = new ArrayList<>();

         recyclerView = rootView.findViewById(R.id.recyclerView_history_walk);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
         adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        return rootView;

    }


    public void showToast(WalkInfo walkInfo, JSONArray response) {
        System.out.println(response);
            walkHistoryDetails.add("dfaf");
            walkHistoryDetails.add("TestA");
            walkHistoryDetails.add("testB");
//        recyclerView.setAdapter(null);
//        recyclerView.setLayoutManager(null);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.notifyDataSetChanged();
//        TextView walkerName = (TextView) getView().findViewById(R.id.walker_name_history);
//        TextView walkedName = (TextView) getView().findViewById(R.id.walked_name);
//        TextView date = (TextView) getView().findViewById(R.id.history_walk_date);
//        TextView time = (TextView) getView().findViewById(R.id.history_walk_time);
//        String nameWalker = walkInfo.getWalkerId().getName();// resp.optString("walker");
//        String nameWalked = resp.optString("walked");
//        String dateWalked = null;
//        try {
//            dateWalked = dateFormatter(walkInfo.getWalkInfoDate());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        String timeWalked = String.valueOf(walkInfo.getWalkTime());
////        walkerName.setText(nameWalker);
////        walkedName.setText(nameWalked);
//        date.setText(dateWalked);
//        time.setText(timeWalked);
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
            if(walkHistoryDetails.size()>0) {
                holder.walker.setText(walkHistoryDetails.get(i));
//            holder.walked.setText(toDisplays.get(i).dogWalked);
//            holder.walkDate.setText(toDisplays.get(i).dateWalked);
//            holder.walkTime.setText(toDisplays.get(i).timeWalked);
            }

        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);

        }

        @Override
        public int getItemCount() {
            return walkHistoryDetails.size();
        }
    }


}

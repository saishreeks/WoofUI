package com.example.a.woofui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.a.api.ApiVolley;
import com.example.a.model.Matereq;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by apple on 2018/3/4.
 */

public class RequestedMate extends Fragment {

    RequestedMateRecyclerAdapter adapter;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycle_view_list,container,false);
        recyclerView = view.findViewById(R.id.recyclerview);
        ApiVolley api=new ApiVolley(getContext());
        SharedPreferences pref=getActivity().getSharedPreferences("UserObject", Context.MODE_PRIVATE);
        api.getRequestedMateList(this,pref.getInt("ownerId",0));
        return view;
    }
    public  void populateData(List<Matereq> list)
    {
        String url=getResources().getString(R.string.image_url);
        adapter=new RequestedMateRecyclerAdapter(url,list,getFragmentManager());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public  void mateCanceled(Boolean status){

        String text="Cancelled Successfully";
        if(!status)
            text="Some error occured";
        Snackbar.make(getActivity().findViewById(R.id.container),text,Snackbar.LENGTH_SHORT).show();

    }



}
class RequestedMateRecyclerAdapter extends RecyclerView.Adapter<com.example.a.woofui.RequestedRecyclerAdapter.ViewHolder> implements View.OnClickListener{
    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.cancel:
                Toast.makeText(view.getContext(),"cancel"+view.getTag(),Toast.LENGTH_SHORT).show();
                break;

        }


    }



    List<Matereq> data;
    int layout;
    String url;
    FragmentManager fragmentManager;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,date,time;
        Button cancel;
        CircleImageView profileImg;
        public ViewHolder(View view) {
            super(view);
            profileImg=(CircleImageView)view.findViewById(R.id.profile_image);
            name=(TextView)view.findViewById(R.id.name);
            date=(TextView)view.findViewById(R.id.date);
            time=(TextView)view.findViewById(R.id.time);
            cancel=(Button)view.findViewById(R.id.cancel);
            //this.textView=(Viw)textView;

        }
    }
    public RequestedMateRecyclerAdapter(String url, List<Matereq> dataSet, FragmentManager frag) {

        this.fragmentManager=frag;
        this.url=url;
        this.data=dataSet;

    }


    @Override
    public void onBindViewHolder(final com.example.a.woofui.RequestedRecyclerAdapter.ViewHolder holder, int position) {

        //  holder.textView.setText(dataSet[position]);
        //holder.profileImg.setImageURI();
        ImageLoader imageLoader =ApiVolley.getImageLoader();
        //"
        imageLoader.get(  url+data.get(position).getReqId().getDogId().getPic(), new ImageLoader.ImageListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("IMG", "Image Load Error: " + error.getMessage());
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                if (response.getBitmap() != null) {
                    // load image into imageview
                    holder.profileImg.setImageBitmap(response.getBitmap());
                }
            }
        });
        holder.name.setText(data.get(position).getReqId().getDogId().getName());
        holder.date.setText(data.get(position).getMateReqDate().toString());
        holder.time.setText(data.get(position).getMateReqDate().toString());
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ApiVolley api=new ApiVolley();
                Matereq matereq =data.get(holder.getAdapterPosition());

                //Set current owner

                api.cancelAMate((RequestedMate) fragmentManager.findFragmentByTag("requestedMate"), matereq.getMateReqId());
                data.remove(holder.getAdapterPosition());

                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(), data.size());

                Toast.makeText(view.getContext(),"Request"+view.getTag(),Toast.LENGTH_SHORT).show();

            }
        });
        holder.cancel.setTag(data.get(position).getMateReqId());


    }


    @Override
    public com.example.a.woofui.RequestedRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_requested,parent,false);
        com.example.a.woofui.RequestedRecyclerAdapter.ViewHolder viewHolder=new com.example.a.woofui.RequestedRecyclerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}



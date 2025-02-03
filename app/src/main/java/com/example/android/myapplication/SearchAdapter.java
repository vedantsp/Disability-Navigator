package com.example.android.myapplication;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by DELL on 24/03/2018.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    Context context;
    ArrayList<String> nameList;
    ArrayList<String> placeList;
    ArrayList<String> imageList;

    public SearchAdapter(Context context, ArrayList<String> placeList, ArrayList<String> nameList, ArrayList<String> imageList) {
        this.context = context;
        this.nameList = nameList;
        this.placeList = placeList;
        this.imageList = imageList;
    }

    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.searchlist_item,parent,false);
        return new SearchAdapter.SearchViewHolder(view);
    }
    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {

        holder.fullAddr.setText(placeList.get(position));
        holder.fullName.setText(nameList.get(position));

        //holder.photo.setImage(imageList.get(position));
        Glide.with(context).load(imageList.get(position)).asBitmap().placeholder(R.mipmap.ic_launcher_round).into(holder.photo);
    }



    @Override
    public int getItemCount() {
        return nameList.size();
    }


    class SearchViewHolder extends RecyclerView.ViewHolder{

        ImageView photo;
        TextView fullName;
        TextView fullAddr;

        public SearchViewHolder(View itemView) {
            super(itemView);
            photo=itemView.findViewById(R.id.profilePhoto);
            fullName=itemView.findViewById(R.id.fullName);
            fullAddr=itemView.findViewById(R.id.fullAddress);
        }
    }





}

package com.example.android.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import static com.example.android.myapplication.R.id.got_latitude;
import static com.example.android.myapplication.R.id.got_longitude;
import static com.example.android.myapplication.R.id.got_name;
import static com.example.android.myapplication.R.id.got_addr;
import static com.example.android.myapplication.R.id.got_image;
/**
 * Created by DELL on 23/03/2018.
 */


public class ImageListAdapter extends ArrayAdapter<ImageUpload> {
    private Activity context;
    private int resource;
    private List<ImageUpload> listImage;
    public ImageListAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<ImageUpload> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        listImage=objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View view=inflater.inflate(resource,null);
        TextView txtView = (TextView) view.findViewById(got_name);
        TextView txtView1 = (TextView) view.findViewById(got_addr);
        ImageView img= (ImageView) view.findViewById(got_image);
        TextView lat=(TextView)view.findViewById(got_latitude);
        TextView longg=(TextView)view.findViewById(got_longitude);
        Button got_map =(Button)view.findViewById(R.id.got_map);



        txtView.setText(listImage.get(position).getName());
        txtView1.setText(listImage.get(position).getAddr());
        lat.setText(listImage.get(position).getLat());
        longg.setText(listImage.get(position).getLongg());
        Glide.with(context).load(listImage.get(position).getUrl()).into(img);
        return view;

    }

}


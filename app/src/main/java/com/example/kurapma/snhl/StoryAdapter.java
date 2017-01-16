package com.example.kurapma.snhl;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by kurapma on 1/11/17.
 */

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.MyViewHolder>{

    private Activity context;

    ArrayList<StoryInformation> data;


    public StoryAdapter(Activity context , ArrayList<StoryInformation> data){
        this.context = context;
        this.data=data;
    }


    @Override
    public StoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = context.getLayoutInflater() ;
        View view=inflater.inflate(R.layout.story_image_item,parent,false);
        MyViewHolder myViewHolder= new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(StoryAdapter.MyViewHolder holder, int position) {
        /*holder.textview.setText(data.get(position).title);*/
        Picasso.with(context).load(data.get(position).imageId).resize(1080,720 ).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textview;
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textview = (TextView) itemView.findViewById(R.id.text_below_image);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);
        }
    }
}

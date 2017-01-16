package com.example.kurapma.snhl;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kurapma on 1/11/17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
    private Activity context;
    private List<NewsData> items;
    static final String TAG = "TAG_MyActivity";

    public NewsAdapter(Activity context,String[] titles,String[] names, String[] urls, Bitmap[] images){
        super();
        this.context = context;
        items = new ArrayList<>();
        for(int i =0; i<names.length; i++){
            NewsData item = new NewsData();
            item.setName(names[i]);
            item.setUrl(urls[i]);
            item.setImage(images[i]);
            item.setTitle(titles[i]);
            items.add(item);
        }
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_card_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsData list =  items.get(position);
        holder.mTitleView.setText(list.getTitle().toUpperCase());
        holder.mTextView.setText(list.getName());
        Picasso.with(context).load(list.getUrl()).resize(640,360 ).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return (position);
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        public CardView mCardView;
        public TextView mTextView;
        public TextView mTitleView;
        public ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),SelectedNewsActivity.class);
                    intent.putExtra("ImageURL",items.get(getAdapterPosition()).getUrl().toString());
                    intent.putExtra("Title",items.get(getAdapterPosition()).getTitle().toString());
                    intent.putExtra("Text",items.get(getAdapterPosition()).getName().toString());
                    v.getContext().startActivity(intent);
                }
            });

            mCardView = (CardView) itemView.findViewById(R.id.card_view);
            mTextView = (TextView) itemView.findViewById(R.id.tv_text);
            mTitleView = (TextView) itemView.findViewById(R.id.tv_title);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_image);
        }
    }

}

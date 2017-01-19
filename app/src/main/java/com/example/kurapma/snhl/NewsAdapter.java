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

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private Activity context;
    private List<NewsData> items;
    static final String TAG = "TAG_MyActivity";

    public NewsAdapter(List<NewsData> items,  Activity context) {
        this.items = items;
        this.context = context;
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
        NewsData list = items.get(position);
        if (list != null) {
            holder.mTitleView.setText(list.getTitle());
            holder.mTextView.setText(list.getName());
            Picasso.with(context).load(list.getUrl()).resize(760, 480).into(holder.mImageView);
        } else {

        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return (position);
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView mTextView;
        public TextView mTitleView;
        public ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), SelectedNewsActivity.class);
                    intent.putExtra("ImageURL", items.get(getAdapterPosition()).getUrl().toString());
                    intent.putExtra("Title", items.get(getAdapterPosition()).getTitle().toString());
                    intent.putExtra("Text", items.get(getAdapterPosition()).getName().toString());
                    v.getContext().startActivity(intent);
                }
            });

            mCardView = (CardView) itemView.findViewById(R.id.news_card_view);
            mTextView = (TextView) itemView.findViewById(R.id.news_tv_text);
            mTitleView = (TextView) itemView.findViewById(R.id.news_tv_title);
            mImageView = (ImageView) itemView.findViewById(R.id.news_iv_image);
        }
    }

}

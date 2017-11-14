package com.example.kurapma.snhl.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.kurapma.snhl.R;
import com.example.kurapma.snhl.activity.DonateActivity;
import com.example.kurapma.snhl.activity.SelectedStoryActivity;
import com.example.kurapma.snhl.model.StoryData;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by kurapma on 1/18/17.
 */

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {
    private Activity context;
    private List<StoryData> items;
    static final String TAG = "TAG_MyActivity";

    public StoryAdapter(List<StoryData> items, Activity context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public StoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.story_card_item, parent, false);
        int height = parent.getHeight() / 2;
        v.setMinimumHeight(height);
        StoryAdapter.ViewHolder vh = new StoryAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(StoryAdapter.ViewHolder holder, int position) {
        StoryData list = items.get(position);
        if (list != null) {
            int amountNeeded = list.getAmountNeeded();
            int amountCollected = list.getAmountCollected();
            float floatProgressBar;
            floatProgressBar = (amountNeeded-amountCollected)*100/amountNeeded;
            int intProgressBar = Math.round(100-floatProgressBar);
            Picasso.with(context).load(list.getUrl()).resize(760, 480).into(holder.mImageView);
            holder.amount_progress_bar.setProgress(intProgressBar);
            holder.fundInfo.setText("$"+amountCollected +" of "+"$"+amountNeeded+" goal reached");
        }else {
            System.out.println("Gone wrong");;
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
        public ImageView mImageView;
        public Button donateButton;
        public TextView fundInfo;
        public ProgressBar amount_progress_bar;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), SelectedStoryActivity.class);
                    intent.putExtra("ImageURL", items.get(getAdapterPosition()).getUrl().toString());
                    intent.putExtra("Title", items.get(getAdapterPosition()).getTitle().toString());
                    intent.putExtra("Text", items.get(getAdapterPosition()).getName().toString());
                    v.getContext().startActivity(intent);
                }
            });

            mCardView = (CardView) itemView.findViewById(R.id.story_card_view);
            mImageView = (ImageView) itemView.findViewById(R.id.story_iv_image);
            donateButton = (Button) itemView.findViewById(R.id.donate_button);
            fundInfo = (TextView)itemView.findViewById(R.id.fund_info);
            amount_progress_bar = (ProgressBar) itemView.findViewById(R.id.amount_progress_bar);

            donateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.getContext().startActivity(new Intent(view.getContext(), DonateActivity.class));
                }
            });
        }
    }
}

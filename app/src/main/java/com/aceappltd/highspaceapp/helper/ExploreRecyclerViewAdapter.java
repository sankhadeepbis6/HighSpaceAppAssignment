package com.aceappltd.highspaceapp.helper;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aceappltd.highspaceapp.R;
import com.aceappltd.highspaceapp.data.Events;

import java.util.ArrayList;

public class ExploreRecyclerViewAdapter extends RecyclerView.Adapter<ExploreRecyclerViewAdapter.ExploreRecyclerViewHolder> {

    private ArrayList<Events> mEventsArrayList;

    public static class ExploreRecyclerViewHolder extends RecyclerView.ViewHolder{

        ImageView mImageView;
        TextView mTextView;
        public ExploreRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            // bind view item in here
            mImageView = itemView.findViewById(R.id.recyclerview_explore_image);
            mTextView = itemView.findViewById(R.id.recyclerview_explore_text);

        }
    }

    public ExploreRecyclerViewAdapter(ArrayList<Events> eventsArrayList) {
        mEventsArrayList = eventsArrayList;
    }

    @NonNull
    @Override
    public ExploreRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // attaching view holder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_explore, parent, false);
        return new ExploreRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreRecyclerViewHolder holder, int position) {

         Events events = mEventsArrayList.get(position);

        Bitmap photo = events.getIconBitmap();
        holder.mImageView.setImageBitmap(photo);

        holder.mTextView.setText(events.getName());
    }

    @Override
    public int getItemCount() {
        return mEventsArrayList.size();
    }
}
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

public class UpcomingRecyclerViewAdapter extends RecyclerView.Adapter<UpcomingRecyclerViewAdapter.UpcomingRecyclerViewHolder> {

    private ArrayList<Events> mEventsArrayList;

    public static class UpcomingRecyclerViewHolder extends RecyclerView.ViewHolder{

        private ImageView mImageView;
        private TextView mTextTime;
        private TextView mTextName;
        private TextView mTextView;

        public UpcomingRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            // bind view item in here
            mImageView = itemView.findViewById(R.id.recyclerview_upcoming_image);
            mTextTime = itemView.findViewById(R.id.recyclerview_upcoming_time);
            mTextName = itemView.findViewById(R.id.recyclerview_upcoming_name);
            mTextView = itemView.findViewById(R.id.recyclerview_upcoming_text);

        }
    }

    public UpcomingRecyclerViewAdapter(ArrayList<Events> eventsArrayList) {
        mEventsArrayList = eventsArrayList;
    }

    @NonNull
    @Override
    public UpcomingRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // attaching view holder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_upcoming, parent, false);
        return new UpcomingRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingRecyclerViewHolder holder, int position) {

        Events events = mEventsArrayList.get(position);

        Bitmap photo = events.getIconBitmap();
        holder.mImageView.setImageBitmap(photo);

        holder.mTextName.setText(events.getName());
        holder.mTextTime.setText(events.getDateTime());

        String text = events.getType() + " - " + events.getLocation();
        holder.mTextView.setText(text);

    }

    @Override
    public int getItemCount() {
        return mEventsArrayList.size();
    }
}
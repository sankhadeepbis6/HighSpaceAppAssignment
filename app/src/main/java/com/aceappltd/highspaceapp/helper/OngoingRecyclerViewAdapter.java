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

public class OngoingRecyclerViewAdapter extends RecyclerView.Adapter<OngoingRecyclerViewAdapter.OngoingRecyclerViewHolder> {

    private ArrayList<Events> mEventsArrayList;

    public static class OngoingRecyclerViewHolder extends RecyclerView.ViewHolder{

        private ImageView mImageView;
        private TextView mTextTime;
        private TextView mTextName;
        private TextView mTextView;

        public OngoingRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            // bind view item in here
            mImageView = itemView.findViewById(R.id.recyclerview_ongoing_image);
            mTextTime = itemView.findViewById(R.id.recyclerview_ongoing_time);
            mTextName = itemView.findViewById(R.id.recyclerview_ongoing_name);
            mTextView = itemView.findViewById(R.id.recyclerview_ongoing_text);

        }
    }

    public OngoingRecyclerViewAdapter(ArrayList<Events> eventsArrayList) {
        mEventsArrayList = eventsArrayList;
    }

    @NonNull
    @Override
    public OngoingRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // attaching view holder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_ongoing, parent, false);
        return new OngoingRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OngoingRecyclerViewHolder holder, int position) {

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
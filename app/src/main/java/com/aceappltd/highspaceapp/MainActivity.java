package com.aceappltd.highspaceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.aceappltd.highspaceapp.data.Events;
import com.aceappltd.highspaceapp.helper.DownloadBitmap;
import com.aceappltd.highspaceapp.helper.ExploreRecyclerViewAdapter;
import com.aceappltd.highspaceapp.helper.OngoingRecyclerViewAdapter;
import com.aceappltd.highspaceapp.helper.UpcomingRecyclerViewAdapter;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    RequestQueue mRequestQueue;
    ArrayList<Events> mArrayEventsExplore;
    ArrayList<Events> mArrayEventsOngoing;
    ArrayList<Events> mArrayEventsUpcoming;

    private RecyclerView mExploreRecyclerView;
    private ExploreRecyclerViewAdapter mExploreAdapter;
    private RecyclerView.LayoutManager mExploreLayoutManager;

    private RecyclerView mOngoingRecyclerView;
    private OngoingRecyclerViewAdapter mOngoingAdapter;
    private RecyclerView.LayoutManager mOngoingLayoutManager;

    private RecyclerView mUpcomingRecyclerView;
    private UpcomingRecyclerViewAdapter mUpcomingAdapter;
    private RecyclerView.LayoutManager mUpcomingLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRequestQueue = Volley.newRequestQueue(this);

        mArrayEventsExplore = new ArrayList<>();
        mArrayEventsOngoing = new ArrayList<>();
        mArrayEventsUpcoming = new ArrayList<>();

        getJson();

        //setting up explore recycler view
        mExploreRecyclerView = findViewById(R.id.explore_recyclerview);
        mExploreRecyclerView.setHasFixedSize(true);
        mExploreLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        mExploreAdapter = new ExploreRecyclerViewAdapter(mArrayEventsExplore);

        mExploreRecyclerView.setLayoutManager(mExploreLayoutManager);
        mExploreRecyclerView.setAdapter(mExploreAdapter);


        //setting up ongoing recycler view
        mOngoingRecyclerView = findViewById(R.id.ongoing_recyclerview);
        mOngoingRecyclerView.setHasFixedSize(true);
        mOngoingLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        mOngoingAdapter = new OngoingRecyclerViewAdapter(mArrayEventsOngoing);

        mOngoingRecyclerView.setLayoutManager(mOngoingLayoutManager);
        mOngoingRecyclerView.setAdapter(mOngoingAdapter);

        //setting up upcoming recycler view
        mUpcomingRecyclerView = findViewById(R.id.upcoming_recyclerview);
        mUpcomingRecyclerView.setHasFixedSize(true);
        mUpcomingLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        mUpcomingAdapter = new UpcomingRecyclerViewAdapter(mArrayEventsUpcoming);

        mUpcomingRecyclerView.setLayoutManager(mUpcomingLayoutManager);
        mUpcomingRecyclerView.setAdapter(mUpcomingAdapter);

    }

    private void getJson() {

        String url = "https://extendsclass.com/api/json-storage/bin/deddffc";

        //JSONObject jsonObject;

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("high_space", response.getString("statusMessage"));

                            JSONArray jsonArrayExplore = response.getJSONArray("Explore Events Now");
                            JSONArray jsonArrayOngoing = response.getJSONArray("Ongoing Events");
                            JSONArray jsonArrayUpcoming = response.getJSONArray("Upcoming Events");

                            for (int i = 0; i < jsonArrayExplore.length(); i++) {
                                String name = jsonArrayExplore.getJSONObject(i).getString("name");
                                String iconURL = jsonArrayExplore.getJSONObject(i).getString("iconURL");

                                DownloadBitmap downloadBitmap = new DownloadBitmap();
                                downloadBitmap.execute(iconURL);
                                Bitmap bitmap = downloadBitmap.get();

                                Events events = new Events(name, "", "", "", iconURL);
                                events.iconBitmap = bitmap;

                                mArrayEventsExplore.add(events);
                            }
                            Log.d("high_space", "size - " + mArrayEventsExplore.size());

                            for (int i = 0; i < jsonArrayOngoing.length(); i++) {
                                String name = jsonArrayOngoing.getJSONObject(i).getString("name");
                                String type = jsonArrayOngoing.getJSONObject(i).getString("type");
                                String location = jsonArrayOngoing.getJSONObject(i).getString("location");
                                String dateTime = jsonArrayOngoing.getJSONObject(i).getString("dateTime");
                                String iconURL = jsonArrayOngoing.getJSONObject(i).getString("iconURL");

                                DownloadBitmap downloadBitmap = new DownloadBitmap();
                                downloadBitmap.execute(iconURL);
                                Bitmap bitmap = downloadBitmap.get();

                                Events events = new Events(name, type, location, dateTime, iconURL);
                                events.iconBitmap = bitmap;

                                mArrayEventsOngoing.add(events);
                            }
                            Log.d("high_space", "size - " + mArrayEventsOngoing.size());

                            for (int i = 0; i < jsonArrayUpcoming.length(); i++) {
                                String name = jsonArrayUpcoming.getJSONObject(i).getString("name");
                                String type = jsonArrayUpcoming.getJSONObject(i).getString("type");
                                String location = jsonArrayUpcoming.getJSONObject(i).getString("location");
                                String dateTime = jsonArrayUpcoming.getJSONObject(i).getString("dateTime");
                                String iconURL = jsonArrayUpcoming.getJSONObject(i).getString("iconURL");

                                DownloadBitmap downloadBitmap = new DownloadBitmap();
                                downloadBitmap.execute(iconURL);
                                Bitmap bitmap = downloadBitmap.get();

                                Events events = new Events(name, type, location, dateTime, iconURL);
                                events.iconBitmap = bitmap;

                                mArrayEventsUpcoming.add(events);
                            }
                            Log.d("high_space", "size - " + mArrayEventsUpcoming.size());

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("high_space", "json error - " + e.toString());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Log.d("high_space", "interrupted error - " + e.toString());
                        } catch (ExecutionException e) {
                            Log.d("high_space", "error - " + e.toString());
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("high_space", "error -" + error);
                        Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });

        mRequestQueue.add(request);
    }


}
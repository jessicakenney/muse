package com.epicodus.muse.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.epicodus.muse.R;
import com.epicodus.muse.adapters.ArtifactListAdapter;
import com.epicodus.muse.models.Artifact;
import com.epicodus.muse.services.CooperHewittService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ArtifactListActivity extends AppCompatActivity {
    public static final String TAG = ArtifactListActivity.class.getSimpleName();
    @Bind(R.id.recyclerArtifactsView) RecyclerView mRecyclerView;
    private ArtifactListAdapter mAdapter;
    public ArrayList<Artifact> artifacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artifact_list);

        ButterKnife.bind(this);
        Intent intent = getIntent();
        String color = intent.getStringExtra("color");

        getArtifacts(color);
    }

    private void getArtifacts(String color) {
        final CooperHewittService cooperHewittService = new CooperHewittService();

        cooperHewittService.callArtifacts(color, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                    artifacts = cooperHewittService.processResults(response);
                    ArtifactListActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter = new ArtifactListAdapter(getApplicationContext(), artifacts);
                            mRecyclerView.setAdapter(mAdapter);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ArtifactListActivity.this);
                            mRecyclerView.setLayoutManager(layoutManager);
                            mRecyclerView.setHasFixedSize(true);

                          for (Artifact artifact : artifacts) {
                            Log.d(TAG, "Title: " + artifact.getTitle());
                            Log.d(TAG, "Type: " + artifact.getType());
                            Log.d(TAG, "Url: " + artifact.getUrl());
                            Log.d(TAG, "Medium: " + artifact.getMedium());
                            Log.d(TAG, "Date: " + artifact.getDate());
                            Log.d(TAG, "Description: " + artifact.getDescription());
                            Log.d(TAG, "Justification: " + artifact.getJustification());
                            Log.d(TAG, "ObjectId: " + artifact.getObjectId());
                            Log.d(TAG, "Image url: " + artifact.getImageUrl());
                          }
                        }
                    });
            }

        });
    }
}

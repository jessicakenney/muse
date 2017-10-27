package com.epicodus.muse.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

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
    @Bind(R.id.nullSearchTextView) TextView mNullSearchTextView;
    @Bind(R.id.recyclerArtifactsView) RecyclerView mRecyclerView;
    private ArtifactListAdapter mAdapter;
    public ArrayList<Artifact> artifacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artifact_list);

        ButterKnife.bind(this);
        Intent intent = getIntent();
        String value = intent.getStringExtra("value");
        String option = intent.getStringExtra("option");

        String getRandom = intent.getStringExtra("random");

        getArtifacts(option, value);
    }

    private void getArtifacts(String option, String value) {
        final CooperHewittService cooperHewittService = new CooperHewittService();

        cooperHewittService.callArtifacts(option, value, new Callback() {

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
                            // if artifacts return 0, report  nullSearchTextView
                            if ( artifacts.size() == 0 ) {
                                mNullSearchTextView.setText("No results found for your query.");
                            } else {
                                mAdapter = new ArtifactListAdapter(getApplicationContext(), artifacts);
                                mRecyclerView.setAdapter(mAdapter);
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ArtifactListActivity.this);
                                mRecyclerView.setLayoutManager(layoutManager);
                                mRecyclerView.setHasFixedSize(true);
                            }

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

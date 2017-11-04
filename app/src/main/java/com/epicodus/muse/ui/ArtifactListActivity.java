package com.epicodus.muse.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.epicodus.muse.R;
import com.epicodus.muse.adapters.ArtifactListAdapter;
import com.epicodus.muse.models.Artifact;

import java.util.ArrayList;

import butterknife.Bind;

public class ArtifactListActivity extends AppCompatActivity {
    public static final String TAG = ArtifactListActivity.class.getSimpleName();
    @Bind(R.id.nullSearchTextView) TextView mNullSearchTextView;
    @Bind(R.id.recyclerArtifactsView) RecyclerView mRecyclerView;
    private ArtifactListAdapter mAdapter;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentWord;
    public ArrayList<Artifact> artifacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artifact_list);
//        ButterKnife.bind(this);
//
//        Intent intent = getIntent();
//        String value = intent.getStringExtra("value");
//        String option = intent.getStringExtra("option");
        //Need to pass Fragment these in a bundle

        if (savedInstanceState == null) {
            // During initial setup, plug in the details fragment.
            ArtifactListFragment fragment = new ArtifactListFragment();
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(
                    android.R.id.content, fragment).commit();
        }
//        Bundle bundle = new Bundle();
//        bundle.putString("value", value);
//        bundle.putString("option", option);
//        this.fragment.setArguments(bundle);

//        //if word query save in sharedPreferences
//        if (option.equals("word")) {
//            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//            mRecentWord = mSharedPreferences.getString(Constants.PREFERENCES_WORD_KEY, null);
//            if (mRecentWord != null) {
//                getArtifacts(option, mRecentWord);
//                Log.d(TAG, "Using word saved in sharedPreferences : "+mRecentWord);
//            }
//        } else {
//            getArtifacts(option, value);
//        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_search, menu);
//        ButterKnife.bind(this);
//
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();
//
//        MenuItem menuItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                addToSharedPreferences(query);
//                getArtifacts("word", query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }



//    private void addToSharedPreferences(String value ) {
//        mEditor.putString(Constants.PREFERENCES_WORD_KEY, value).apply();
//    }

//    private void getArtifacts(String option, String value) {
//        final CooperHewittService cooperHewittService = new CooperHewittService();
//
//        cooperHewittService.callArtifacts(option, value, new Callback() {
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) {
//                    artifacts = cooperHewittService.processResults(response);
//                    ArtifactListActivity.this.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            // if artifacts return 0, report  nullSearchTextView
//                            if ( artifacts.size() == 0 ) {
//                                mNullSearchTextView.setText("No results found for your query.");
//                            } else {
//                                mAdapter = new ArtifactListAdapter(getApplicationContext(), artifacts);
//                                mRecyclerView.setAdapter(mAdapter);
//                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ArtifactListActivity.this);
//                                mRecyclerView.setLayoutManager(layoutManager);
//                                mRecyclerView.setHasFixedSize(true);
//                            }
//
//                          for (Artifact artifact : artifacts) {
//                            Log.d(TAG, "Title: " + artifact.getTitle());
//                            Log.d(TAG, "Type: " + artifact.getType());
//                            Log.d(TAG, "Url: " + artifact.getUrl());
//                            Log.d(TAG, "Medium: " + artifact.getMedium());
//                            Log.d(TAG, "Date: " + artifact.getDate());
//                            Log.d(TAG, "Description: " + artifact.getDescription());
//                            Log.d(TAG, "Justification: " + artifact.getJustification());
//                            Log.d(TAG, "ObjectId: " + artifact.getObjectId());
//                            Log.d(TAG, "Image url: " + artifact.getImageUrl());
//                          }
//                        }
//                    });
//            }
//
//        });
//    }
}

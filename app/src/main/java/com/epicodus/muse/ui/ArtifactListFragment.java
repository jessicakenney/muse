package com.epicodus.muse.ui;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.muse.Constants;
import com.epicodus.muse.R;
import com.epicodus.muse.adapters.ArtifactListAdapter;
import com.epicodus.muse.models.Artifact;
import com.epicodus.muse.services.CooperHewittService;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.content.Intent.getIntent;
import static android.content.Intent.getIntentOld;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtifactListFragment extends Fragment {
    public static final String TAG = ArtifactListActivity.class.getSimpleName();
    //@Bind(R.id.nullSearchTextView) TextView mNullSearchTextView;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private ArtifactListAdapter mAdapter;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentWord;
    public ArrayList<Artifact> artifacts = new ArrayList<>();
    private String mValue ;
    private String mOption ;


    public ArtifactListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mEditor = mSharedPreferences.edit();
        ButterKnife.bind(this.getActivity());
        mValue = getActivity().getIntent().getStringExtra("value");
        mOption = getActivity().getIntent().getStringExtra("option");
        Log.v("HERE!", mValue +  mOption + "");
        setHasOptionsMenu(true);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_artifact_list,container,false);
        ButterKnife.bind(this,view);

        mRecentWord = mSharedPreferences.getString(Constants.PREFERENCES_WORD_KEY, null);
        //getArtifacts("word", mRecentWord);

        if ((mRecentWord != null) && (mOption.equals("word"))) {
            getArtifacts("word", mRecentWord);
            Log.d(TAG, "Using word saved in sharedPreferences : "+mRecentWord);
        } else {
            Log.d(TAG, "Calling getArtifacts for : "+mOption + mValue);
            getArtifacts(mOption, mValue);
        }

        return view;
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
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // if artifacts return 0, report  nullSearchTextView
                    if ( artifacts.size() == 0 ) {
                        //mNullSearchTextView.setText("No results found for your query.");
                    } else {
                        mAdapter = new ArtifactListAdapter(getActivity(), artifacts);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
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

    private void addToSharedPreferences(String value ) {
        mEditor.putString(Constants.PREFERENCES_WORD_KEY, value).apply();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);

        inflater.inflate(R.menu.menu_search, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                getArtifacts("word", query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}

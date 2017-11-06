package com.epicodus.muse.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.epicodus.muse.R;
import com.epicodus.muse.adapters.ArtifactListAdapter;
import com.epicodus.muse.models.Artifact;

import java.util.ArrayList;

public class ArtifactListActivity extends AppCompatActivity {
    public static final String TAG = ArtifactListActivity.class.getSimpleName();
    //@Bind(R.id.nullSearchTextView) TextView mNullSearchTextView;
    //@Bind(R.id.recyclerArtifactsView) RecyclerView mRecyclerView;
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

//        if (savedInstanceState == null) {
//            // During initial setup, plug in the details fragment.
//            ArtifactListFragment fragment = new ArtifactListFragment();
//            fragment.setArguments(getIntent().getExtras());
//            getSupportFragmentManager().beginTransaction().add(
//                    android.R.id.content, fragment).commit();
//        }
//        Bundle bundle = new Bundle();
//        bundle.putString("value", value);
//        bundle.putString("option", option);
//        this.fragment.setArguments(bundle);


    }
}

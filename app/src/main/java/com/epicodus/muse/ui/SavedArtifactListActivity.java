package com.epicodus.muse.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.muse.Constants;
import com.epicodus.muse.R;
import com.epicodus.muse.adapters.FirebaseArtifactViewHolder;
import com.epicodus.muse.models.Artifact;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedArtifactListActivity extends AppCompatActivity {
    private DatabaseReference mArtifactReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerArtifactsView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_artifact_list);
        ButterKnife.bind(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mArtifactReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_ARTIFACTS)
                .child(uid);

        setUpFirebaseAdapter();

    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Artifact, FirebaseArtifactViewHolder>
                (Artifact.class, R.layout.artifact_list_item, FirebaseArtifactViewHolder.class,
                        mArtifactReference) {

            @Override
            protected void populateViewHolder(FirebaseArtifactViewHolder viewHolder,
                                              Artifact model, int position) {
                viewHolder.bindArtifact(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}

package com.epicodus.muse.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.epicodus.muse.Constants;
import com.epicodus.muse.R;
import com.epicodus.muse.adapters.FirebaseArtifactListAdapter;
import com.epicodus.muse.adapters.FirebaseArtifactViewHolder;
import com.epicodus.muse.models.Artifact;
import com.epicodus.muse.util.OnStartDragListener;
import com.epicodus.muse.util.SimpleItemTouchHelperCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedArtifactListActivity extends AppCompatActivity implements OnStartDragListener {
    private DatabaseReference mArtifactReference;
    private FirebaseArtifactListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

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

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

//        mArtifactReference = FirebaseDatabase
//                .getInstance()
//                .getReference(Constants.FIREBASE_CHILD_ARTIFACTS)
//                .child(uid);

        Query query = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_ARTIFACTS)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);

        mFirebaseAdapter = new FirebaseArtifactListAdapter(Artifact.class,
                R.layout.artifact_list_item_drag, FirebaseArtifactViewHolder.class,
                query, this, this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

}

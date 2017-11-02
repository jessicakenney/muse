package com.epicodus.muse.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.muse.Constants;
import com.epicodus.muse.R;
import com.epicodus.muse.models.Artifact;
import com.epicodus.muse.ui.ArtifactDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import static com.epicodus.muse.ui.ArtifactListActivity.TAG;

/**
 * Created by momma on 10/27/17.
 */

public class FirebaseArtifactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_WIDTH = 300;
    private static final int MAX_HEIGHT = 300;

    View mView;
    Context mContext;

    public FirebaseArtifactViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindArtifact(Artifact artifact) {
        ImageView artifactImageView = (ImageView) mView.findViewById(R.id.artifactImageView);
        TextView titleTextView = (TextView) mView.findViewById(R.id.artifactTitleTextView);

        titleTextView.setText(artifact.getTitle());
        Picasso.with(mContext)
                .load(artifact.getImageUrl())
                .into(artifactImageView);
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Artifact> artifacts = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_ARTIFACTS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    artifacts.add(snapshot.getValue(Artifact.class));
                }

                Log.v(TAG,"look for artifact info from FIrebase for detail view"+artifacts);
                int itemPosition = getLayoutPosition();
                Log.v(TAG,"look for artifact info from FIrebase for detail view"+artifacts.get(itemPosition).getTitle() + " Position " + itemPosition);
                Intent intent = new Intent(mContext, ArtifactDetailActivity.class);
                intent.putExtra("position", itemPosition);
                intent.putExtra("artifacts", Parcels.wrap(artifacts));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}

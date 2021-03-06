package com.epicodus.muse.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.muse.R;
import com.epicodus.muse.models.Artifact;
import com.epicodus.muse.ui.ArtifactDetailActivity;
import com.epicodus.muse.ui.ArtifactDetailFragment;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by momma on 10/19/17.
 */

public class ArtifactListAdapter extends RecyclerView.Adapter<ArtifactListAdapter.ArtifactViewHolder> {
    public static final String TAG = ArtifactListAdapter.class.getSimpleName();
    private static final int MAX_WIDTH = 300;
    private static final int MAX_HEIGHT = 300;
    private ArrayList<Artifact> mArtifacts = new ArrayList<>();
    private Context mContext;

    public ArtifactListAdapter(Context context, ArrayList<Artifact> artifacts) {
        mContext = context;
        mArtifacts = artifacts;
    }

    @Override
    public ArtifactListAdapter.ArtifactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artifact_list_item, parent, false);
        ArtifactViewHolder viewHolder = new ArtifactViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ArtifactListAdapter.ArtifactViewHolder holder, int position) {
        holder.bindArtifact(mArtifacts.get(position));
    }

    @Override
    public int getItemCount() {
        return mArtifacts.size();
    }

    public class ArtifactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.artifactImageView) ImageView mArtifactImageView;
        @Bind(R.id.artifactTitleTextView) TextView mTitleTextView;
        private int mOrientation;
        private Context mContext;




        public ArtifactViewHolder(View itemView) {
            super(itemView);
                ButterKnife.bind(this, itemView);
                mContext = itemView.getContext();
                itemView.setOnClickListener(this);
                // Determines the current orientation of the device:
                mOrientation = itemView.getResources().getConfiguration().orientation;

                // Checks if the recorded orientation matches Android's landscape configuration.
                // if so, we create a new DetailFragment to display in our special landscape layout:
                if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                    createDetailFragment(0);
                }
        }

        // Takes position of restaurant in list as parameter:
        private void createDetailFragment(int position) {
            ArtifactDetailFragment detailFragment = ArtifactDetailFragment.newInstance(mArtifacts, position);
            FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.artifactDetailContainer, detailFragment);
            ft.commit();
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(itemPosition);
            } else {
                Intent intent = new Intent(mContext, ArtifactDetailActivity.class);
                intent.putExtra("position", itemPosition);
                intent.putExtra("artifacts", Parcels.wrap(mArtifacts));
                mContext.startActivity(intent);
            }
        }

        public void bindArtifact(Artifact artifact) {
            mTitleTextView.setText(artifact.getTitle());
            //mMediumTextView.setText(artifact.getMedium());
            Picasso.with(mContext)
                    .load(artifact.getImageUrl())
                    //.resize(MAX_WIDTH, MAX_HEIGHT)
                    //.centerCrop()
                    //.placeholder(R.drawable.waffles)
                    .into(mArtifactImageView);
        }
    }
}

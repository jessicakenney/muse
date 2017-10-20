package com.epicodus.muse.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.muse.R;
import com.epicodus.muse.models.Artifact;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by momma on 10/19/17.
 */

public class ArtifactListAdapter extends RecyclerView.Adapter<ArtifactListAdapter.ArtifactViewHolder> {
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

    public class ArtifactViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.artifactImageView) ImageView mArtifactImageView;
        @Bind(R.id.artifactTitleTextView) TextView mTitleTextView;
        //@Bind(R.id.artifactTypeTextView) TextView mTypeTextView;
        @Bind(R.id.artifactMediumTextView) TextView mMediumTextView;

        private Context mContext;

        public ArtifactViewHolder(View itemView) {
            super(itemView);
            try {
                ButterKnife.bind(this, itemView);
                mContext = itemView.getContext();
            } catch (Exception e) {
                e.printStackTrace();
            }
            }

        public void bindArtifact(Artifact artifact) {
            mTitleTextView.setText(artifact.getTitle());
            //mTypeTextView.setText(artifact.getType());
            mMediumTextView.setText(artifact.getMedium());
            Picasso.with(mContext)
                    .load(artifact.getImageUrl())
                    //.resize(MAX_WIDTH, MAX_HEIGHT)
                    //.centerCrop()
                    .placeholder(R.drawable.waffles)
                    .into(mArtifactImageView);
        }
    }
}

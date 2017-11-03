package com.epicodus.muse.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.muse.R;
import com.epicodus.muse.models.Artifact;
import com.epicodus.muse.util.ItemTouchHelperViewHolder;
import com.squareup.picasso.Picasso;

import static com.epicodus.muse.R.id.artifactImageView;

;


/**
 * Created by momma on 10/27/17.
 */

public class FirebaseArtifactViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    private static final int MAX_WIDTH = 300;
    private static final int MAX_HEIGHT = 300;
    public static final String TAG = FirebaseArtifactViewHolder.class.getSimpleName();
    public ImageView mArtifactImageView;

    View mView;
    Context mContext;

    public FirebaseArtifactViewHolder(View itemView)  {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindArtifact(Artifact artifact) {
        TextView titleTextView = (TextView) mView.findViewById(R.id.artifactTitleTextView);
        mArtifactImageView = (ImageView) mView.findViewById(artifactImageView);

        titleTextView.setText(artifact.getTitle());
        Picasso.with(mContext)
                .load(artifact.getImageUrl())
                .into(mArtifactImageView);


    }

    @Override
    public void onItemSelected() {
        itemView.animate()
                .alpha(0.7f)
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setDuration(500);
    }

    @Override
    public void onItemClear() {
        itemView.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f);
    }


}

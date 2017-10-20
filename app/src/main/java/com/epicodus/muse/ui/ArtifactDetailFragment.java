package com.epicodus.muse.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.muse.R;
import com.epicodus.muse.models.Artifact;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ArtifactDetailFragment extends Fragment {
    @Bind(R.id.artifactImageView) ImageView mImageLabel;
    @Bind(R.id.artifactTitleTextView) TextView mTitleLabel;
    @Bind(R.id.artifactMediumTextView) TextView mMediumLabel;
    @Bind(R.id.artifactJustificationTextView) TextView mJustificationLabel;
    @Bind(R.id.saveArtifactButton) TextView mSaveArtifactButton;

    private Artifact mArtifact;

    public static ArtifactDetailFragment newInstance(Artifact artifact) {
        ArtifactDetailFragment artifactDetailFragment = new ArtifactDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("artifact", Parcels.wrap(artifact));
        artifactDetailFragment.setArguments(args);
        return artifactDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArtifact = Parcels.unwrap(getArguments().getParcelable("artifact"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artifact_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mArtifact.getImageUrl()).into(mImageLabel);

        mTitleLabel.setText(mArtifact.getTitle());
        mMediumLabel.setText(mArtifact.getMedium());
        mJustificationLabel.setText(mArtifact.getJustification());

        return view;
    }
}

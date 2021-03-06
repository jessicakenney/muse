package com.epicodus.muse.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.muse.Constants;
import com.epicodus.muse.R;
import com.epicodus.muse.models.Artifact;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ArtifactDetailFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = ArtifactDetailFragment.class.getSimpleName();
    @Bind(R.id.artifactImageView)
    ImageView mImageLabel;
    @Bind(R.id.artifactTitleTextView)
    TextView mTitleLabel;
    @Bind(R.id.artifactMediumTextView)
    TextView mMediumLabel;
    @Bind(R.id.artifactDescriptionTextView)
    TextView mDescriptionLabel;
    @Bind(R.id.artifactJustificationTextView)
    TextView mJustificationLabel;
    @Bind(R.id.websiteTextView)
    TextView mWebsiteLabel;
    @Bind(R.id.galleryTextView)
    TextView mGalleryLabel;
    TextView scrollable;

    private Artifact mArtifact;
    private ArrayList<Artifact> mArtifacts;
    private int mPosition;

    public static ArtifactDetailFragment newInstance(ArrayList<Artifact> artifacts,Integer position ) {
        ArtifactDetailFragment artifactDetailFragment = new ArtifactDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.EXTRA_KEY_ARTIFACTS, Parcels.wrap(artifacts));
        args.putInt(Constants.EXTRA_KEY_POSITION, position);
        artifactDetailFragment.setArguments(args);
        return artifactDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArtifacts = Parcels.unwrap(getArguments().getParcelable(Constants.EXTRA_KEY_ARTIFACTS));
        mPosition = getArguments().getInt(Constants.EXTRA_KEY_POSITION);
        mArtifact = mArtifacts.get(mPosition);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artifact_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mArtifact.getImageUrl()).into(mImageLabel);

        mTitleLabel.setText(mArtifact.getTitle());
        mMediumLabel.setText(mArtifact.getMedium());
        mDescriptionLabel.setText(mArtifact.getDescription());
        mJustificationLabel.setText(mArtifact.getJustification());

        //scrollable = mJustificationLabel;
        //scrollable.setMovementMethod(new ScrollingMovementMethod());

        mWebsiteLabel.setOnClickListener(this);
        mGalleryLabel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mArtifact.getUrl()));
            startActivity(webIntent);
        }

        if (v == mGalleryLabel) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user == null) {
                Log.d(TAG,"Trying to save , but not logged in"+ user);
                Toast.makeText(getContext(), "You need to Login before you can save", Toast.LENGTH_LONG).show();
            } else {
                String uid = user.getUid();

                DatabaseReference restaurantRef = FirebaseDatabase
                        .getInstance()
                        .getReference(Constants.FIREBASE_CHILD_ARTIFACTS)
                        .child(uid);

                DatabaseReference pushRef = restaurantRef.push();
                String pushId = pushRef.getKey();
                mArtifact.setPushId(pushId);
                pushRef.setValue(mArtifact);

                Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

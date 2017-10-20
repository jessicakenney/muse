package com.epicodus.muse.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.epicodus.muse.R;
import com.epicodus.muse.adapters.ArtifactPagerAdapter;
import com.epicodus.muse.models.Artifact;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ArtifactDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    private ArtifactPagerAdapter adapterViewPager;
    ArrayList<Artifact> mArtifacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artifact_detail);
        ButterKnife.bind(this);

        mArtifacts = Parcels.unwrap(getIntent().getParcelableExtra("artifacts"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new ArtifactPagerAdapter(getSupportFragmentManager(), mArtifacts);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}

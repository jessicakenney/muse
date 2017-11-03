package com.epicodus.muse.adapters;

/**
 * Created by momma on 10/20/17.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.muse.models.Artifact;
import com.epicodus.muse.ui.ArtifactDetailFragment;

import java.util.ArrayList;

public class ArtifactPagerAdapter extends FragmentPagerAdapter {
    public static final String TAG = ArtifactPagerAdapter.class.getSimpleName();
    private ArrayList<Artifact> mArtifacts;

    public ArtifactPagerAdapter(FragmentManager fm, ArrayList<Artifact> artifacts) {
        super(fm);
        mArtifacts = artifacts;
    }

    @Override
    public Fragment getItem(int position) {
        return ArtifactDetailFragment.newInstance(mArtifacts.get(position));
    }

    @Override
    public int getCount() {
        return mArtifacts.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mArtifacts.get(position).getTitle();
    }
}

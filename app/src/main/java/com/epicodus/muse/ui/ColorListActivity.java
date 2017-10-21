package com.epicodus.muse.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.muse.R;
import com.epicodus.muse.adapters.ColorListAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.epicodus.muse.Constants.CSS3_PALETTE_HEX;

public class ColorListActivity extends AppCompatActivity {
    public static final String TAG = ColorListActivity.class.getSimpleName();
    ColorListAdapter mColorAdapter;
    @Bind(R.id.colorsRecyclerView) RecyclerView mRecyclerView;
    String[] colors = new String[]{"#f0f8ff", "#faebd7", "#00ffff", "#7fffd4", "#f0ffff", "#f5f5dc", "#ffe4c4", "#000000", "#ffebcd", "#0000ff", "#8a2be2", "#a52a2a" };
    //use constant CSS4_PALETTE_HEX

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);
        ButterKnife.bind(this);

        mColorAdapter = new ColorListAdapter(getApplicationContext(), CSS3_PALETTE_HEX);
        mRecyclerView.setAdapter(mColorAdapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ColorListActivity.this, 5);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

    }

}

package com.epicodus.muse.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.muse.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.cooperTextView) TextView mCooperTextView;
    @Bind(R.id.hewittTextView) TextView mHewittTextView;
    @Bind(R.id.colorsButton) Button mColorsButton;
    @Bind(R.id.randomButton) Button mRandomButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams.ttf");
        mAppNameTextView.setTypeface(typeface);

        mColorsButton.setOnClickListener(this);
        mRandomButton.setOnClickListener(this);
        mCooperTextView.setOnClickListener(this);
        mHewittTextView.setOnClickListener(this);

    }//onCreate

    @Override
    public void onClick(View v) {
        if (v == mColorsButton){
            Intent intent = new Intent (MainActivity.this, ColorListActivity.class);
            startActivity(intent);
        }
        if (v == mRandomButton){
            Log.v(TAG, ".>>>>>...RANDOM selected: ");
            Intent intent = new Intent (MainActivity.this,ArtifactListActivity.class );
            intent.putExtra("color", "random");
            startActivity(intent);
        }
        if (v == mCooperTextView | v == mHewittTextView){
            Intent intent = new Intent (MainActivity.this, AboutActivity.class);
            startActivity(intent);

        };

    }//onClick



}//Main

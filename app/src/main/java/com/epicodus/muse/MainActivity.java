package com.epicodus.muse;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.cooperTextView) TextView mCooperTextView;
    @Bind(R.id.hewittTextView) TextView mHewittTextView;
    @Bind(R.id.colorsButton) Button mColorsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams.ttf");
        mAppNameTextView.setTypeface(typeface);

        mColorsButton.setOnClickListener(this);
        mCooperTextView.setOnClickListener(this);
        mHewittTextView.setOnClickListener(this);

    }//onCreate

    @Override
    public void onClick(View v) {
        if (v == mColorsButton){
            Intent intent = new Intent (MainActivity.this, ColorsActivity.class);
            startActivity(intent);
        }
        if (v == mCooperTextView | v == mHewittTextView){
            Intent intent = new Intent (MainActivity.this, AboutActivity.class);
            startActivity(intent);

        };

    }//onClick



}//Main

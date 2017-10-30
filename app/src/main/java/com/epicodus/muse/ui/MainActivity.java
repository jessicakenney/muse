package com.epicodus.muse.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.muse.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.titleTextView) TextView mAppNameTextView;
    @Bind(R.id.exploreTextView) TextView mExploreTextView;
    @Bind(R.id.cooperTextView) TextView mCooperTextView;
    @Bind(R.id.hewittTextView) TextView mHewittTextView;
    @Bind(R.id.colorsButton) Button mColorsButton;
    @Bind(R.id.randomButton) Button mRandomButton;
    @Bind(R.id.wordButton) Button mWordButton;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams.ttf");
        mAppNameTextView.setTypeface(typeface);
        mExploreTextView.setTypeface(typeface);

        mColorsButton.setOnClickListener(this);
        mRandomButton.setOnClickListener(this);
        mWordButton.setOnClickListener(this);
        mCooperTextView.setOnClickListener(this);
        mHewittTextView.setOnClickListener(this);

        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //when there is a user logged in
                    //getSupportActionBar().setTitle("Welcome, " + user.getDisplayName());
                } else {
                }
            }
        };


        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_home:
                                Log.v(TAG, ".>>>>>...bottomnav HOME clicked");
                                break;
                            case R.id.action_about:
                                Log.v(TAG, ".>>>>>...bottomnav ABOUT clicked");
                                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.action_account:
                                FirebaseUser user = mAuth.getCurrentUser();
                                Log.v(TAG, ".>>>>>...bottomnav ACCOUNT clicked user:  "+user);
                                if (user != null) {
                                    Intent intent0 = new Intent(MainActivity.this, UserAccountActivity.class);
                                    startActivity(intent0);
                                } else {
                                    login();
                                }
                                break;
                        }
                        return true;
                    }
                });
    }//onCreate


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    @Override
    public void onClick(View v) {
        String inputWord = "";

        if (v == mColorsButton){
            Intent intent = new Intent (MainActivity.this, ColorListActivity.class);
            startActivity(intent);
        }
        if (v == mRandomButton){
//            Log.v(TAG, ".>>>>>...RANDOM selected: ");
//            Intent intent = new Intent (MainActivity.this,ArtifactListActivity.class );
//            intent.putExtra("option", "random");
//            startActivity(intent);
        }
        if (v == mWordButton){
            Log.v(TAG, ".>>>>>...Word selected: ");
            Intent intent = new Intent (MainActivity.this,ArtifactListActivity.class );
            intent.putExtra("option", "word");
            startActivity(intent);
        }

        if (v == mCooperTextView | v == mHewittTextView){
            Intent intent = new Intent (MainActivity.this, AboutActivity.class);
            startActivity(intent);

        };

    }//onClick

    private void login() {
        //go to loginActivity
        Log.v(TAG, ".>>>>>...going to Login Activity");
        Intent intent = new Intent (MainActivity.this,LoginActivity.class );
        startActivity(intent);
    }


}//Main

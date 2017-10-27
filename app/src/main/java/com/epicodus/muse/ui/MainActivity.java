package com.epicodus.muse.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
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
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.cooperTextView) TextView mCooperTextView;
    @Bind(R.id.hewittTextView) TextView mHewittTextView;
    @Bind(R.id.colorsButton) Button mColorsButton;
    @Bind(R.id.randomButton) Button mRandomButton;
    @Bind(R.id.wordButton) Button mWordButton;
    @Bind(R.id.queryTextView) TextView mQueryTextView;
    @Bind(R.id.enterWordButton) Button mEnterWordButton;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams.ttf");
        mAppNameTextView.setTypeface(typeface);

        mColorsButton.setOnClickListener(this);
        mRandomButton.setOnClickListener(this);
        mWordButton.setOnClickListener(this);
        mEnterWordButton.setOnClickListener(this);
        mCooperTextView.setOnClickListener(this);
        mHewittTextView.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //display welcome message
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName());
                } else {

                }
            }
        };

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_login, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem menuLogin = menu.findItem(R.id.action_login);
        MenuItem menuLogout = menu.findItem(R.id.action_logout);
        MenuItem menuGalleries = menu.findItem(R.id.action_galleries);

        FirebaseUser user = mAuth.getCurrentUser();
        Log.d(TAG, ">>>: mCurrentUser >>>> "+user);

        //if (mAuthListener != null) {
        if(user != null ) {
            menuLogin.setVisible(false);
            menuLogout.setVisible(true);
            menuGalleries.setVisible(true);
        } else {
            menuLogin.setVisible(true);
            menuLogout.setVisible(false);
            menuGalleries.setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_login) {
            login();
            return true;
        }
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        String inputWord = "";

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
        if (v == mWordButton){
            Log.v(TAG, ".>>>>>...Word selected: ");
            //make visible a textbox to enter query word
            if ( mQueryTextView.getVisibility() == View.VISIBLE) {
                mQueryTextView.setVisibility(View.INVISIBLE);
                mEnterWordButton.setVisibility(View.INVISIBLE);

            } else {
                mQueryTextView.setVisibility(View.VISIBLE);
                mEnterWordButton.setVisibility(View.VISIBLE);
            }
        }
        if (v == mEnterWordButton){
            Log.v(TAG, ".>>>>>...Enter Word Button Pressed: ");
            inputWord = mQueryTextView.getText().toString().trim();
            if (inputWord.equals("")) {
                mQueryTextView.setError("Please enter word to search");
                return;
            }
            Log.v(TAG, ".>>>>>...WordEntered: "+inputWord);
            Intent intent = new Intent (MainActivity.this,ArtifactListActivity.class );
            intent.putExtra("option", "word");
            intent.putExtra("value", inputWord);
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

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }



}//Main

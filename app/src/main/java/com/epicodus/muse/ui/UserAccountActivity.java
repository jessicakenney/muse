package com.epicodus.muse.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.muse.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UserAccountActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = UserAccountActivity.class.getSimpleName();
    @Bind(R.id.userTextView) TextView mUserTextView;
    @Bind(R.id.galleryButton) Button mGalleryButton;
    @Bind(R.id.logoutButton) Button mLogoutButton;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
        ButterKnife.bind(this);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams.ttf");
        mUserTextView.setTypeface(typeface);

        mGalleryButton.setOnClickListener(this);
        mLogoutButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        Log.v(TAG, ">>need to set user name here "+user.getDisplayName());
        mUserTextView.setText("Hi "+user.getDisplayName());

//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    //when there is a user logged in
//                } else {
//                }
//            }
//        };

    }

    @Override
    public void onClick(View v) {
        if (v == mGalleryButton){
            Intent intent = new Intent(UserAccountActivity.this, SavedArtifactListActivity.class);
            startActivity(intent);
        }
        if (v == mLogoutButton){
            logout();
        }
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(UserAccountActivity.this, "Bye Bye",
                Toast.LENGTH_LONG).show();
        Intent intent = new Intent(UserAccountActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}

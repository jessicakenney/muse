package com.epicodus.muse.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.muse.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UserAccountActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = UserAccountActivity.class.getSimpleName();
    @Bind(R.id.userTextView) TextView mUserTextView;
    @Bind(R.id.galleryButton) Button mGalleryButton;
    @Bind(R.id.logoutButton) Button mLogoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
        ButterKnife.bind(this);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams.ttf");
        mUserTextView.setTypeface(typeface);

        mGalleryButton.setOnClickListener(this);
        mLogoutButton.setOnClickListener(this);

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

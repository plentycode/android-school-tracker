package com.plentycode.school_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    private String loggedName;

    private static final String TAG = "LoginActivity";

    //ui elements
    Button btnLogOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase auth

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        } else {
            loggedName = firebaseUser.getDisplayName();
            Log.d(TAG, "User Logged" + loggedName);

            TextView txtCentered = (TextView) findViewById(R.id.centered_text);
            txtCentered.setText("Welcome, " + loggedName);
        }

        btnLogOut = (Button) findViewById(R.id.log_out);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

    }
}

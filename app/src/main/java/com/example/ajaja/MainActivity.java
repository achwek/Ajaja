package com.example.ajaja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private  Timer timer = new Timer();
    private TimerTask timerTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_main);

        timerTask = new TimerTask() {
            @Override
            public void run() {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    // User is signed in
                    //use intent to move
                    Intent intent= new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // No user is signed in
                    //use intent to move
                    Intent intent= new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }};
        timer.schedule(timerTask,3000);
    }
}
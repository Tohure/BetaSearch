package com.orbismobile.betasearch.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.orbismobile.betasearch.R;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView launch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        launch = (TextView) findViewById(R.id.launch);
        launch.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(SplashActivity.this, TabActivity.class));
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        finish();
    }
}

package com.example.iksoks;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Play extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Iks Oks");
    }

    public void singleOnClick(View view) {
        Intent intent = new Intent(this, Single.class);
        startActivity(intent);
    }

    public void multiOnClick(View view) {
        Intent intent = new Intent(this, Multi.class);
        startActivity(intent);
    }
}
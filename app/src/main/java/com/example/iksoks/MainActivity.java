package com.example.iksoks;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            // enabling action bar app icon and behaving it as toggle button
            actionBar.setIcon(R.drawable.ass3);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        setContentView(R.layout.activity_main);
    }

    public void playOnClick(View view) {
        Intent intent = new Intent(this, Play.class);
        startActivity(intent);
    }

    public void exitOnClick(View view) {
        Intent intent = new Intent(this, Quit.class);
        startActivity(intent);
    }

    public void aboutOnClick(View view) {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }
}
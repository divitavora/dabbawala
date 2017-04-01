package com.example.dishankaashvi.dabba;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView image;
        image = (ImageView) findViewById(R.id.ImageView01);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, catering.class);
                startActivity(i);
                finish();
            }
        }, 4000);
    }

    public void onItemClick(int mPosition) {
    }
}


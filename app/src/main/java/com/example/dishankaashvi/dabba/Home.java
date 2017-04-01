package com.example.dishankaashvi.dabba;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;

public class Home extends Activity {
    Button c;
    Button d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        get();
        set();
    }

    private void set() {
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Home.this,catering.class);
                startActivity(i);
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Home.this,dabba.class);
                startActivity(i);

            }
        });
    }

    private void get() {
         c=(Button)findViewById(R.id.catering);
         d=(Button)findViewById(R.id.dabba);
    }

}

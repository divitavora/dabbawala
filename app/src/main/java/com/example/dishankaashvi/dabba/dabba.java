package com.example.dishankaashvi.dabba;

import android.content.Intent;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class dabba extends AppCompatActivity {
    EditText source;
    EditText destination;
    Button location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dabba);
        get();
        set();
    }
    private void get(){
        source=(EditText)findViewById(R.id.source);
        destination=(EditText)findViewById(R.id.destination);
        location=(Button)findViewById(R.id.place);
    }
    private void set() {
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(dabba.this,datepick.class);
                String src=source.getText().toString();
                String des=destination.getText().toString();

            }
        });
    }
}

package com.example.dishankaashvi.dabba;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Calendar;

public class bill extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        final Calendar c =Calendar.getInstance();


        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        String currentDate = day+"/"+month+"/"+year;
        Log.d("Date",currentDate);

        TextView datetext = (TextView) findViewById(R.id.dateRHS);
        datetext.setText(currentDate);



        String thali_detail = getIntent().getExtras().getString("key");

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://dabba-a52df.firebaseio.com/").child("Thali").child(thali_detail);
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final TextView thaliName = (TextView) findViewById(R.id.nameRHS);
                thaliName.setText(dataSnapshot.child("name").getValue().toString());
                final TextView thaliPrice = (TextView) findViewById(R.id.costRHS);
                thaliPrice.setText(dataSnapshot.child("price").getValue().toString());
                final TextView thaliTotal = (TextView) findViewById(R.id.totalRHS);
                thaliTotal.setText(dataSnapshot.child("price").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}

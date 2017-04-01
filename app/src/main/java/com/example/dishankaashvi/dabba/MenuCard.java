package com.example.dishankaashvi.dabba;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dishankaashvi.dabba.models.ordermodel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class MenuCard extends AppCompatActivity{

       DatabaseReference uRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://dabba-a52df.firebaseio.com//");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_card);

        Button btn = (Button) findViewById(R.id.button);


        final ProgressDialog pd = new ProgressDialog(MenuCard.this);
        pd.setMessage("Loading...");
        pd.show();

        final EditText qty = (EditText) findViewById(R.id.qtyvalue);

        final StorageReference mStorage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://dabba-a52df.appspot.com").child("menu");

        final ImageView thaliPic = (ImageView) findViewById(R.id.thali_pic);

        final String thali_details = getIntent().getExtras().getString("key");
        final DatabaseReference mRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://dabba-a52df.firebaseio.com/").child("Thali").child(thali_details);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final TextView thaliName = (TextView) findViewById(R.id.thali_name);
                thaliName.setText(dataSnapshot.child("name").getValue().toString());
                final TextView thaliContent = (TextView) findViewById(R.id.thali_content);
                thaliContent.setText(dataSnapshot.child("content").getValue().toString());
                final TextView thaliPrice = (TextView) findViewById(R.id.thali_price);
                thaliPrice.setText(dataSnapshot.child("price").getValue().toString());
                mStorage.child(dataSnapshot.child("pic").getValue().toString()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri downloadURI) {
                        Picasso.with(MenuCard.this).load(downloadURI).fit().centerCrop().into(thaliPic);
                    }
                }).addOnFailureListener( new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                        Toast.makeText(MenuCard.this,"Image cannot be loaded",Toast.LENGTH_LONG).show();
                    }
                });
                pd.dismiss();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String dp = (String)dataSnapshot.child("price").getValue();
                        float price = Float.parseFloat(dp);
                        final String quant = qty.getText().toString();
                       float quantity = Float.parseFloat(quant);
                        float total_price = price * Float.parseFloat(quant);
                        final ordermodel orders = new ordermodel(dataSnapshot.child("name").getValue().toString(),quant,total_price);
                        final String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        uRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if(dataSnapshot.hasChild(user_id))
                                {
                                    String billkey = dataSnapshot.child(user_id).child("currentbill").getValue(String.class);
                                    uRef.child(user_id).child(billkey).push().setValue(orders);
                                }
                                else{
                                    DatabaseReference newRef=uRef.child(user_id).push().push();
                                    newRef.setValue(orders);
                                    String key = newRef.getParent().getKey();
                                    FirebaseDatabase.getInstance().getReferenceFromUrl("https://dabba-a52df.firebaseio.com//").child(user_id).child("currentbill").setValue((key.toString()));
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        Intent intent=new Intent(MenuCard.this,catering.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
    }



}
package com.example.dishankaashvi.dabba;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class catering extends AppCompatActivity {
    DatabaseReference uRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://dabba-a52df.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catering);

        final ArrayList<String> nameThali = new ArrayList<>();
        Button cart = (Button) findViewById(R.id.button1);

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://dabba-a52df.firebaseio.com/").child("ThaliList");


            ListView mlistThali = (ListView) findViewById(R.id.listThali);

            final ProgressBar pd1 = (ProgressBar) findViewById(R.id.prog);
            pd1.setVisibility(View.VISIBLE);


            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(catering.this, android.R.layout.simple_list_item_1, nameThali);

            mRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    nameThali.add(value);
                    arrayAdapter.notifyDataSetChanged();
                    pd1.setVisibility(View.INVISIBLE);

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            mlistThali.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String choice = nameThali.get(i);
                    String str = FirebaseDatabase.getInstance().getReferenceFromUrl("https://dabba-a52df.firebaseio.com/\n").child("Thali").child(choice).getKey();
                    Intent intent = new Intent(catering.this, MenuCard.class);
                    intent.putExtra("key",str);
                    startActivity(intent);
                }
            });
            mlistThali.setAdapter(arrayAdapter);
            cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(catering.this, vieworders.class);
                    startActivity(intent);
                }});}

//                    LayoutInflater li = LayoutInflater.from(catering.this);
//                    View promptsView = li.inflate(R.layout.activity_orderdisplay, null);
//                    ListView orderlist = (ListView) findViewById(R.id.listorder);
//                    final ArrayList<ordermodel> nameorders = new ArrayList<>();
//
//                    final ProgressBar pd1 = (ProgressBar) findViewById(R.id.prog);
//                    pd1.setVisibility(View.VISIBLE);
//
//                    final String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
//
//
//                    ArrayAdapter<ordermodel> orderAdapter = new ArrayAdapter<ordermodel>(catering.this,android.R.layout.activity_list_item,nameorders);
//                    uRef.child(user_id).child("currentbill").addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            final String billkey = dataSnapshot.getValue().toString();
//
//                            uRef.child(user_id).child(billkey).addValueEventListener(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(DataSnapshot dataSnapshot) {
//                                    for(DataSnapshot snapshot:dataSnapshot.getChildren()) {
//                                        ordermodel o = snapshot.getValue(ordermodel.class);
//                                        nameorders.add(o);
//                                        arrayAdapter.notifyDataSetChanged();
//                                        pd1.setVisibility(View.INVISIBLE);
//                                    }
//                                }
//
//                                @Override
//                                public void onCancelled(DatabaseError databaseError) {
//
//                                }
//                            });
//
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//
//                        }
//                    });
//
//                    orderlist.setAdapter(orderAdapter);
//
//
//
//
//
//                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//                            catering.this);
//
//                    // set prompts.xml to alertdialog builder
//                    alertDialogBuilder.setView(promptsView);
//
//                    final EditText userInput = (EditText) promptsView
//                            .findViewById(R.id.editTextDialogUserInput);
//
//                    // set dialog message
//                    alertDialogBuilder
//                            .setCancelable(false)
//                            .setPositiveButton("OK",
//                                    new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface dialog, int id) {
//                                            // get user input and set it to result
//                                            // edit text
//                                            //  result.setText(userInput.getText());
//                                            String destination = userInput.getText().toString();
//                                            Log.d("Choice", destination);
//                                            locate(destination,2);
//                                            return;
//                                        }
//                                    })
//                            .setNegativeButton("Cancel",
//                                    new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface dialog, int id) {
//                                            dialog.cancel();
//                                        }
//                                    });
//
//                    // create alert dialog
//                    AlertDialog alertDialog = alertDialogBuilder.create();
//
//                    // show it
//                    alertDialog.show();
//
//
//                }
//            });
//                }
//            });
//        }


}

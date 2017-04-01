package com.example.dishankaashvi.dabba;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.dishankaashvi.dabba.models.listviewmodel;
import com.example.dishankaashvi.dabba.models.ordermodel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class vieworders extends AppCompatActivity {
    DatabaseReference uRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://dabba-a52df.firebaseio.com/");

    ListView list;
    CustomAdapter adapter;
    public  vieworders CustomListView = null;
    public ArrayList<listviewmodel> CustomListViewValuesArr = new ArrayList<listviewmodel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vieworders);
        CustomListView = this;
        setListData();


        Resources res = getResources();
        list = (ListView) findViewById(R.id.list);  // List defined in XML ( See Below )
        adapter = new CustomAdapter(CustomListView, CustomListViewValuesArr, res);
        list.setAdapter(adapter);

    }
        public void setListData()
        {

//            for (int i = 0; i < 11; i++) {
//
//                final ListModel sched = new ListModel();
//
//                /******* Firstly take data in model object ******/
//                sched.setDishName("Company "+i);
//                sched.setImage("image"+i);
//                sched.setUrl("http:\\www."+i+".com");
//
//                /******** Take Model Object in ArrayList **********/
//                CustomListViewValuesArr.add( sched );
//            }

            final ProgressBar pd1 = (ProgressBar) findViewById(R.id.prog);
            pd1.setVisibility(View.VISIBLE);

            final String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();

            uRef.child(user_id).child("currentbill").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    final String billkey = dataSnapshot.getValue().toString();

                    uRef.child(user_id).child(billkey).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot snapshot:dataSnapshot.getChildren()) {
                                ordermodel o = snapshot.getValue(ordermodel.class);
                                final listviewmodel obj =new listviewmodel();
                                obj.setDishName(o.order_item);
                                obj.setQuantity(o.quantity);
                                obj.setTotalprice(o.total_price);
                                CustomListViewValuesArr.add(obj);
                                adapter.notifyDataSetChanged();
                                pd1.setVisibility(View.INVISIBLE);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }




    /****** Function to set data in ArrayList *************/



    /*****************  This function used by adapter ****************/

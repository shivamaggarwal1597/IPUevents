package com.example.shivam.eventslistapp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.shivam.eventslistapp.adapter.EventAdapter;
import com.example.shivam.eventslistapp.models.EventModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class ChooserActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    EventAdapter eventAdapter;
    List<EventModel> eventModels;
    FirebaseDatabase firebaseDatabase;
    Handler handler;
    ProgressBar progressBar;
    int progressBarTimer = 0;
    FirebaseStorage firebaseStorage;
    DatabaseReference databaseReference;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);
        progressBar = (ProgressBar)findViewById(R.id.pro);
        handler=new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progressBarTimer<100){
                    progressBarTimer+=5;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressBarTimer);
                            if(progressBarTimer==100){
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                    try{
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }

        }).start();

        eventModels = new ArrayList<EventModel>();
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.child("events").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventModels.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    EventModel emc = ds.getValue(EventModel.class);
                    if(emc.getFlag().equals("active")) {
                        eventModels.add(emc);
                        eventAdapter = new EventAdapter(ChooserActivity.this, eventModels);
                        recyclerView.setAdapter(eventAdapter);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}


package com.example.shivam.eventslistapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateEvent extends AppCompatActivity {
    EditText mobile_no_text;
    Button proceed_button;
    String text;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        mobile_no_text = (EditText)findViewById(R.id.create_event_mobile_edit_text);
        proceed_button = (Button) findViewById(R.id.create_event_proceed_btn);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        proceed_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text =  mobile_no_text.getText().toString();
                if (text.trim().length()==0 || text.trim().length()<10||text.trim().length()>10){
                    Toast.makeText(CreateEvent.this,"The mobile number should contain 10 charecters",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(CreateEvent.this,UploadImageCreateEvent.class);
                    intent.putExtra("mobile_number",text);
                    databaseReference.child("events").child(text).child("flag").setValue("inactive");
                    startActivity(intent);
                }
            }
        });

    }
}

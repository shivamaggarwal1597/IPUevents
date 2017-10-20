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

public class ShowSuccessActivity extends AppCompatActivity {
String mob,name_of_event,name_of_organiser,category,price,duration;
EditText name_of_event_edit_text,name_of_organiser_edit_text,category_text_view,price_text,duration_edit_text;
Button submit_button;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_success);
        Intent intent = getIntent();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
       mob= intent.getStringExtra("mob_id");
        name_of_event_edit_text = (EditText)findViewById(R.id.name_of_event_edit_text);
        name_of_organiser_edit_text =(EditText)findViewById(R.id.name_of_organiser_edit_text);
        category_text_view = (EditText)findViewById(R.id.category_of_event_edit_text);
        price_text = (EditText)findViewById(R.id.price_of_event_edit_text);
        duration_edit_text = (EditText)findViewById(R.id.duration_of_event_edit_text);
        submit_button = (Button)findViewById(R.id.submit_event_button);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                name_of_event = name_of_event_edit_text.getText().toString();
                name_of_organiser = name_of_organiser_edit_text.getText().toString();
                category = category_text_view.getText().toString();
                price = price_text.getText().toString();
                duration = duration_edit_text.getText().toString();

if (name_of_event.trim().length()!=0&&name_of_organiser.trim().length()!=0&&category.trim().length()!=0&&price.trim().length()!=0
        &&duration.trim().length()!=0) {
    databaseReference.child("events").child(mob).child("duration").setValue(duration);
    databaseReference.child("events").child(mob).child("name_of_event").setValue(name_of_event);
    databaseReference.child("events").child(mob).child("price_of_event").setValue(price);
    databaseReference.child("events").child(mob).child("category_of_event").setValue(category);
    databaseReference.child("events").child(mob).child("name_of_organiser").setValue(name_of_organiser);
    databaseReference.child("events").child(mob).child("flag").setValue("active");
    Intent intent1 = new Intent(ShowSuccessActivity.this,MainActivity.class);
    startActivity(intent1);
    finish();

}
else
{
    Toast.makeText(ShowSuccessActivity.this,"All fields are compulsory",Toast.LENGTH_SHORT).show();
}
            }
        });

    }
}

package com.example.shivam.eventslistapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SetLocationDateTime extends AppCompatActivity {
    //In this activity, date -time and location would be made input
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String latitude,longitude;
    String mob_no_id;
    Button fetch_location_button,proceed_app_button;
    FusedLocationProviderClient mFusedLocationClient;
    EditText start_month,start_date,description_text,set_loaction_edit_text;
    TextView location_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_location_date_time);
        firebaseDatabase = FirebaseDatabase.getInstance();
        checkPermission();
        set_loaction_edit_text = (EditText)findViewById(R.id.setlocation_loaction_edit_text);
        databaseReference = firebaseDatabase.getReference();
        fetch_location_button = (Button)findViewById(R.id.setlocation_fetch_location_button);
        proceed_app_button = (Button)findViewById(R.id.setlocation_proceed_button);
        location_text = (TextView)findViewById(R.id.setlocation_fetch_location_text_view);
        start_date = (EditText)findViewById(R.id.set_loaction_start_date_edit_text);
        start_month = (EditText)findViewById(R.id.set_loaction_start_month_edit_text);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        description_text = (EditText)findViewById(R.id.setlocation_description_edit_text);
        Intent intent = getIntent();
        mob_no_id = intent.getStringExtra("mobile_no");
        // int perm = ContextCompat.checkSelfPermission(SetLocationDateTime.this, Manifest.permission.ACCESS_COARSE_LOCATION);
        //ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},perm);
        fetch_location_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLastLocation();
            }
        });

        proceed_app_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(set_loaction_edit_text.getText().toString().trim().length()!=0
                        &&
                        start_date.getText().toString().trim().length()!=0
                        &&
                        start_month.getText().toString().trim().length()!=0
                        && description_text.getText().toString().trim().length()!=0){
                    String description = description_text.getText().toString();
                    String location = set_loaction_edit_text.getText().toString();
                    String start_date_text = start_date.getText().toString();
                    String start_month_text = start_month.getText().toString();

                    //database calls
                    Log.e("My TAG ksjb",start_date_text);
                    databaseReference.child("events").child(mob_no_id).child("start_date_of_event").setValue(start_date_text);
                    databaseReference.child("events").child(mob_no_id).child("start_month_of_event").setValue(start_month_text);
                    databaseReference.child("events").child(mob_no_id).child("address_of_event").setValue(location);
                    databaseReference.child("events").child(mob_no_id).child("start_date_of_event").setValue(start_date_text);
                    databaseReference.child("events").child(mob_no_id).child("description_of_event").setValue(description);
                    databaseReference.child("events").child(mob_no_id).child("contact_number_of_organiser").setValue(mob_no_id);

                    Intent intent = new Intent(SetLocationDateTime.this,ShowSuccessActivity.class);
                    intent.putExtra("mob_id",mob_no_id);
                    startActivity(intent);
                    finish();

                }
            }
        });
    }
    public void checkPermission(){
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                ){//Can add more as per requirement

            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    123);
        }
    }

    @SuppressWarnings("MissingPermission")
    private void getLastLocation(){
        mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location!=null) {
                    Log.e("Location IS:::::::", location.toString());
                    longitude=String.valueOf(location.getLongitude());
                    latitude=String.valueOf(location.getLatitude());

                    Log.e("MyTag",latitude);
                    Log.e("MyTag",longitude);
                    location_text.append(latitude+" , "+ longitude);
                    databaseReference.child("events").child(mob_no_id).child("latitude").setValue(latitude);
                    databaseReference.child("events").child(mob_no_id).child("longitude").setValue(longitude);

                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                    try {
                        List<Address> listAddresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        if(null!=listAddresses&&listAddresses.size()>0){
                            String _Location = listAddresses.get(0).getAddressLine(0);
                            set_loaction_edit_text.setText(_Location);

                            Log.e("My tag",_Location);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                }else
                    Log.e("location is : ","null");
            }
        });

    }

}

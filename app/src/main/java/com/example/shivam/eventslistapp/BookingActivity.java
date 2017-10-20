package com.example.shivam.eventslistapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

public class BookingActivity extends AppCompatActivity {
    ImageView imageView;
    Button textButton,callButton,book_button;
    FirebaseStorage firebaseStorage;
    TextView name_text,location_text,date_text_view,details_text_view;
    StorageReference storageReference;
    String location,name,category,latitude,longitude,price,description,image_url,start_date,start_month,mobile;
    File localFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        Intent intent = getIntent();
        imageView = (ImageView)findViewById(R.id.book_image);
        firebaseStorage = FirebaseStorage.getInstance();
        image_url = intent.getStringExtra("image");
        latitude = intent.getStringExtra("latitude");
        longitude = intent.getStringExtra("longitude");
        start_month = intent.getStringExtra("start_month");
        book_button = (Button)findViewById(R.id.book_ticket);
        book_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BookingActivity.this,"you will be directed to the payment page",Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(BookingActivity.this,PaymentActivity.class);
                startActivity(intent3);
                finish();
            }
        });
        storageReference = firebaseStorage.getReferenceFromUrl("gs://whereismybholastaff.appspot.com/");

        Glide.with(BookingActivity.this).using(new FirebaseImageLoader()).load(storageReference.child(image_url)).into(imageView);

        name =intent.getStringExtra("name");
        name_text = (TextView)findViewById(R.id.event_name_book_activity);
        //name_text.setText(name);
        location_text = (TextView)findViewById(R.id.location_text_view);
        location = intent.getStringExtra("address");
        location_text.setText(location);
        start_date = intent.getStringExtra("start_date");
        date_text_view=(TextView)findViewById(R.id.date_booking_text);
        date_text_view.setText(start_date+" "+start_month);
        description = intent.getStringExtra("description");
        details_text_view = (TextView)findViewById(R.id.description);
        details_text_view.setText(description);
        callButton = (Button)findViewById(R.id.call);
        textButton = (Button)findViewById(R.id.text_org);
        mobile = intent.getStringExtra("organiser_no");
        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("smsto:"));
                intent.setType("vnd.android-dir/mms-sms");
                intent.putExtra("sms_body","write your message here");
                intent.putExtra("address",mobile);
                startActivity(intent);
            }
        });

        location_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //here location values of user and the location of the event would come
                double lat = Double.parseDouble(latitude);
                double lon = Double.parseDouble(longitude);
                showDirections(28.6981, 77.1085, lat, lon);
            }
        });
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri="tel:" +mobile;
                Intent intent1=new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent1);
            }
        });

    }
    public void showDirections(double lat, double lng, double lat1, double lng1) {

        final Intent intent2 = new
                Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?" +
                "saddr=" + lat + "," + lng + "&daddr=" + lat1 + "," +
                lng1));
        intent2.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        startActivity(intent2);

    }


}


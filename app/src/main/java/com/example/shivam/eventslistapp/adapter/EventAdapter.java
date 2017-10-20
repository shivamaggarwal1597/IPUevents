package com.example.shivam.eventslistapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.shivam.eventslistapp.BookingActivity;
import com.example.shivam.eventslistapp.R;
import com.example.shivam.eventslistapp.models.EventModel;
import com.example.shivam.eventslistapp.view_holder.EventViewHolder;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

/**
 * Created by shivam on 28/9/17.
 */

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {
    public List<EventModel> eventModels;
    public Context context;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    public EventAdapter(Context context, List<EventModel> models){

        this.eventModels = models;
        this.context = context;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        EventViewHolder eventViewHolder = null;
        LayoutInflater li= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutView = li.inflate(R.layout.custom_layout,parent,false);
        eventViewHolder = new EventViewHolder(layoutView, eventModels);
        firebaseStorage = FirebaseStorage.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        storageReference = firebaseStorage.getReferenceFromUrl("gs://whereismybholastaff.appspot.com/");
        return eventViewHolder;
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, final int position) {
        String path = eventModels.get(position).getImage_of_event_URL().toString();
        holder.name_of_event_text_view.setText(eventModels.get(position).getName_of_event());
        holder.start_date_event_text_view.setText(eventModels.get(position).getStart_date_of_event());
        holder.start_month_event_text_view.setText(eventModels.get(position).getStart_month_of_event());
        holder.price_of_event_text_view.setText("Rs. "+eventModels.get(position).getPrice_of_event());
        holder.category_of_event_text_view.setText("Category: "+eventModels.get(position).getCategory_of_event());
        holder.location_of_event.setText(eventModels.get(position).getAddress_of_event());
        Glide.with(context).using(new FirebaseImageLoader()).load(storageReference.child(path)).into(holder.event_image_view);;
        holder.book_event_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Toast.makeText(context,"image is cicked",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,BookingActivity.class);
                intent.putExtra("name",eventModels.get(position).getName_of_event());
                intent.putExtra("category",eventModels.get(position).getCategory_of_event());
                intent.putExtra("address",eventModels.get(position).getAddress_of_event());
                intent.putExtra("latitude",eventModels.get(position).getLatitude());
                intent.putExtra("longitude",eventModels.get(position).getLongitude());
                intent.putExtra("start_date",eventModels.get(position).getStart_date_of_event());
                intent.putExtra("start_month",eventModels.get(position).getStart_month_of_event());
                intent.putExtra("organiser_no",eventModels.get(position).getContact_number_of_organiser());
                intent.putExtra("description",eventModels.get(position).getDescription_of_event());
                intent.putExtra("price",eventModels.get(position).getPrice_of_event());
                intent.putExtra("image",eventModels.get(position).getImage_of_event_URL());




                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventModels.size();
    }
}

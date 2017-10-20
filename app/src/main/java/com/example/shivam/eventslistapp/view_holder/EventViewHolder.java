package com.example.shivam.eventslistapp.view_holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shivam.eventslistapp.R;
import com.example.shivam.eventslistapp.models.EventModel;

import java.util.List;

/**
 * Created by shivam on 28/9/17.
 */

public class EventViewHolder extends RecyclerView.ViewHolder {
    public ImageView event_image_view;
    public Button book_event_button;
    public TextView category_of_event_text_view,start_date_event_text_view,start_month_event_text_view,name_of_event_text_view,price_of_event_text_view,
            location_of_event;
    public List<EventModel> taskObj;
    public EventViewHolder(final View itemView, List<EventModel> eventModels) {
        super(itemView);
        this.taskObj = eventModels;
        event_image_view = (ImageView) itemView.findViewById(R.id.image_of_event);
        book_event_button = (Button)itemView.findViewById(R.id.book_tickets_button);
        category_of_event_text_view = (TextView)itemView.findViewById(R.id.event_category_text_view);
        start_date_event_text_view = (TextView)itemView.findViewById(R.id.start_date_text_view);
        start_month_event_text_view = (TextView)itemView.findViewById(R.id.start_month_text_view);
        name_of_event_text_view = (TextView)itemView.findViewById(R.id.event_name_text_view);
        price_of_event_text_view = (TextView)itemView.findViewById(R.id.event_price_text_view);
        location_of_event = (TextView)itemView.findViewById(R.id.event_address_text_view);
    }
}

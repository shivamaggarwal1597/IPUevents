package com.example.shivam.eventslistapp.models;

/**
 * Created by shivam on 28/9/17.
 */

public class EventModel {
    public String latitude;
    public String longitude;
    public String duration;
    public String name_of_event;
    public String name_of_organiser;
    public String contact_number_of_organiser;
    public String description_of_event;
    public String image_of_event_URL;
    public  String category_of_event;
    public String address_of_event;
    public String flag;
    public String price_of_event;
    public String start_date_of_event;
    public  String start_month_of_event;
    public EventModel(){

    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAddress_of_event() {
        return address_of_event;
    }

    public void setAddress_of_event(String address_of_event) {
        this.address_of_event = address_of_event;
    }


    public EventModel(String latitude, String longitude, String duration,
                      String name_of_event, String name_of_organiser,
                      String contact_number_of_organiser, String description_of_event,
                      String image_of_event_URL, String category_of_event,
                      String address_of_event, String flag, String price_of_event,
                      String start_date_of_event, String start_month_of_event) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.duration = duration;
        this.name_of_event = name_of_event;
        this.name_of_organiser = name_of_organiser;
        this.contact_number_of_organiser = contact_number_of_organiser;
        this.description_of_event = description_of_event;
        this.image_of_event_URL = image_of_event_URL;
        this.category_of_event = category_of_event;
        this.address_of_event = address_of_event;
        this.flag = flag;
        this.price_of_event = price_of_event;
        this.start_date_of_event = start_date_of_event;
        this.start_month_of_event = start_month_of_event;
    }

    public String getStart_date_of_event() {
        return start_date_of_event;
    }

    public void setStart_date_of_event(String start_date_of_event) {
        this.start_date_of_event = start_date_of_event;
    }

    public String getStart_month_of_event() {
        return start_month_of_event;
    }

    public void setStart_month_of_event(String start_month_of_event) {
        this.start_month_of_event = start_month_of_event;
    }

    public String getPrice_of_event() {
        return price_of_event;
    }

    public void setPrice_of_event(String price_of_event) {
        this.price_of_event = price_of_event;
    }

    public String getCategory_of_event() {
        return category_of_event;
    }

    public void setCategory_of_event(String category_of_event) {
        this.category_of_event = category_of_event;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getName_of_event() {
        return name_of_event;
    }

    public void setName_of_event(String name_of_event) {
        this.name_of_event = name_of_event;
    }

    public String getName_of_organiser() {
        return name_of_organiser;
    }

    public void setName_of_organiser(String name_of_organiser) {
        this.name_of_organiser = name_of_organiser;
    }

    public String getContact_number_of_organiser() {
        return contact_number_of_organiser;
    }

    public void setContact_number_of_organiser(String contact_number_of_organiser) {
        this.contact_number_of_organiser = contact_number_of_organiser;
    }

    public String getDescription_of_event() {
        return description_of_event;
    }

    public void setDescription_of_event(String description_of_event) {
        this.description_of_event = description_of_event;
    }

    public String getImage_of_event_URL() {
        return image_of_event_URL;
    }

    public void setImage_of_event_URL(String image_of_event_URL) {
        this.image_of_event_URL = image_of_event_URL;
    }
}

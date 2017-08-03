package com.davicaetano.gbapp.view;

import android.view.View;

import com.davicaetano.gbapp.gbModel.GbEvent;


/**
 * Created by davi on 8/2/17.
 */

//This model is used by the view.
public class GbEventMain {
    private GbEvent gbEvent;

    public GbEventMain(GbEvent gbEvent) {
        this.gbEvent = gbEvent;
    }

    public String getName() {
        if (gbEvent == null || gbEvent.getName() == null) {
            return "";
        } else {
            return gbEvent.getName();
        }
    }

    public String getLocation() {
        if (gbEvent == null || gbEvent.getGbVenue() == null) {
            return "";
        }
        String location = "";
        if (gbEvent.getGbVenue().getCity() != null) {
            location += gbEvent.getGbVenue().getCity();
        }

        if (gbEvent.getGbVenue().getState() != null) {
            if (!location.equals("")){
                location += " - ";
            }
            location += gbEvent.getGbVenue().getState();
        }
        return location;
    }

    public int getLocationVisibility() {
        if (getLocation().equals("")) {
            return View.GONE;
        } else {
            return View.VISIBLE;
        }
    }

    public String getEndDate() {
        if (gbEvent == null || gbEvent.getName() == null) {
            return "";
        } else {
            return gbEvent.getEndDate();
        }
    }

    public int getEndDateVisibility() {
        if (getEndDate().equals("")) {
            return View.GONE;
        } else {
            return View.VISIBLE;
        }
    }

    public boolean hasImage() {
        if (gbEvent == null || gbEvent.getIcon() == null || gbEvent.getIcon().equals("")){
            return false;
        } else {
            return true;
        }
    }

    public String getImageUrl() {
        if (hasImage()) {
            return gbEvent.getIcon();
        } else {
            return "";
        }
    }
}

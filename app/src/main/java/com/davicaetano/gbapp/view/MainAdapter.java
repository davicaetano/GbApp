package com.davicaetano.gbapp.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.davicaetano.gbapp.R;
import com.davicaetano.gbapp.gbModel.GbEvent;

import java.util.List;

/**
 * Created by davi on 8/2/17.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.EventViewHolder> {

    private List<GbEvent> gbEventList;

    public MainAdapter(List<GbEvent> gbEventList) {
        this.gbEventList = gbEventList;
    }

    public void setGbEventList(List<GbEvent> gbEventList) {
        this.gbEventList = gbEventList;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.main_event_list_item, parent, false);

        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventViewHolder eventViewHolder, int position) {
        if (gbEventList == null) return;
        GbEventMain gbEventMain = new GbEventMain(gbEventList.get(position));

        eventViewHolder.eventName.setText(gbEventMain.getName());

        eventViewHolder.eventLocation.setVisibility(gbEventMain.getLocationVisibility());
        eventViewHolder.eventLocation.setText(gbEventMain.getLocation());

        eventViewHolder.eventEndDate.setVisibility(gbEventMain.getEndDateVisibility());
        eventViewHolder.eventEndDate.setText(gbEventMain.getEndDate());

        Glide.with(eventViewHolder.eventLogo.getContext())
                .clear(eventViewHolder.eventLogo);
        if (gbEventMain.hasImage()) {
            Glide.with(eventViewHolder.eventLogo.getContext())
                    .load(gbEventMain.getImageUrl())
                    .into(eventViewHolder.eventLogo);
        }

    }

    @Override
    public int getItemCount() {
        if (gbEventList != null) {
            return gbEventList.size();
        } else {
            return 0;
        }
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        TextView eventName;
        TextView eventLocation;
        TextView eventEndDate;
        ImageView eventLogo;

        public EventViewHolder(View itemView) {
            super(itemView);

            eventName = itemView.findViewById(R.id.main_list_event_name);
            eventLocation = itemView.findViewById(R.id.main_list_event_location);
            eventEndDate = itemView.findViewById(R.id.main_list_event_end_date);
            eventLogo = itemView.findViewById(R.id.main_list_event_logo);
        }
    }
}

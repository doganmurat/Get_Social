package com.dogannn.getsocial;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsHolder> {

    private List<Events.Event> eventsList = new ArrayList<>();
    private LayoutInflater layoutInflater;
    public EventsAdapter(Activity activity, List<Events.Event> eventsList){
        this.layoutInflater= (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.eventsList = eventsList;
    }

    @NonNull
    @Override
    public EventsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = layoutInflater.inflate(R.layout.row_layout,null);
        EventsHolder eventsHolder = new EventsHolder(rowView);

        return eventsHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventsHolder holder, int position) {
        Events.Event eventsTmp = eventsList.get(position);
        holder.eventNameTV.setText(eventsTmp.getName());
        holder.venueNameTv.setText(eventsTmp.getVenue().getName() + "/");
        holder.venueCityTv.setText(eventsTmp.getVenue().getCity());
        holder.eventTimeTv.setText(eventsTmp.getEventDateLocal());
        holder.id = eventsTmp.getId();
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    class EventsHolder extends RecyclerView.ViewHolder {
        int id;
        TextView eventNameTV;
        TextView venueNameTv;
        TextView venueCityTv;
        TextView eventTimeTv;

        public EventsHolder(@NonNull View itemView) {
            super(itemView);
            eventNameTV = itemView.findViewById(R.id.eventName);
            venueNameTv = itemView.findViewById(R.id.venueName);
            venueCityTv = itemView.findViewById(R.id.venueCity);
            eventTimeTv = itemView.findViewById(R.id.eventTime);
        }
    }
}

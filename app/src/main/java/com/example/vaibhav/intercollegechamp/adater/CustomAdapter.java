package com.example.vaibhav.intercollegechamp.adater;

/**
 * Created by vaibhav on 19/4/17.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.vaibhav.intercollegechamp.AppController;
import com.example.vaibhav.intercollegechamp.model.Event;

import java.util.List;

//import info.androidhive.customlistviewvolley.R;
import com.example.vaibhav.intercollegechamp.R;



//import com.example.vaibhav.listviewdemojsonvv.R;

public class CustomAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Event> eventItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomAdapter(Activity activity, List<Event> eventItems) {
        this.activity = activity;
        this.eventItems = eventItems;
    }

    @Override
    public int getCount() {
        return eventItems.size();
    }

    @Override
    public Object getItem(int location) {
        return eventItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        TextView EventName = (TextView) convertView.findViewById(R.id.tvEventName);
        TextView EventDate = (TextView) convertView.findViewById(R.id.tvEventDate);
        TextView EventVenue = (TextView) convertView.findViewById(R.id.EventVenue);
//        TextView EventId,
//        TextView EventType,
//        TextView EventLastDate,
//        TextView EventRules,
//        TextView EventTime
        //TextView EventId = (TextView) convertView.findViewById(R.id.releaseYear);

        // getting movie data for the row
        Event m = eventItems.get(position);

        // thumbnail image
        //thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

        // title
        EventName.setText(m.getEventName());

        // rating
        EventDate.setText("Date: " + m.getEventDate());
        // release year
        EventVenue.setText("Venue: "+ String.valueOf(m.getEventVenue()));

        return convertView;
    }

}
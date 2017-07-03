package com.example.vaibhav.intercollegechamp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.vaibhav.intercollegechamp.adater.CustomAdapter;

import com.example.vaibhav.intercollegechamp.model.Event;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import static com.example.vaibhav.intercollegechamp.UserModel.serverurl;


public class EventListActivity extends Activity {

    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();
    public Context context;
    // Events json url
    private static final String url = serverurl;

    private ProgressDialog pDialog;
    private List<Event> eventList = new ArrayList<Event>();
    private ListView listView;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);



        listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView.getItemAtPosition(0);

                //Toast.makeText(EventListActivity.this, String.valueOf(position).toString(), Toast.LENGTH_SHORT).show();

                Intent i=new Intent(EventListActivity.this,EventDetailsActivity.class);
                i.putExtra("EventID1",eventList.get(position).getEventId().toString());
                i.putExtra("EventName1",eventList.get(position).getEventName().toString());
                i.putExtra("EventTime1",eventList.get(position).getEventTime().toString());
                i.putExtra("EventDate1",eventList.get(position).getEventDate().toString());
                i.putExtra("EventLastDate1",eventList.get(position).getEventLastDate().toString());
                i.putExtra("EventVenue1",eventList.get(position).getEventVenue().toString());
                i.putExtra("EventCoordinator1",eventList.get(position).getEventCoordinator().toString());
                i.putExtra("EventCoordinatorNumber1",eventList.get(position).getEventCoordinatorNumber().toString());
                i.putExtra("EventRules1",eventList.get(position).getEventRules().toString());




                EventListActivity.this.startActivity(i);

            }

        });



        adapter = new CustomAdapter(this, eventList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        // changing action bar color
//        getActionBar().setBackgroundDrawable(
        //              new ColorDrawable(Color.parseColor("#1b1b1b")));

        // Creating volley request obj
        JsonArrayRequest eventReq = new JsonArrayRequest(url,new Response.Listener<JSONArray>()
        {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Event event = new Event();

                                event.setEventId(obj.getString("EventId"));
                                event.setEventName(obj.getString("EventName"));
                                event.setEventType(obj.getString("EventType"));
                                event.setEventRules(obj.getString("EventRules"));
                                event.setEventDate(obj.getString("EventDate"));
                                event.setEventTime(obj.getString("EventTime"));
                                event.setEventLastDate(obj.getString("EventLastDate"));
                                event.setEventVenue(obj.getString("EventVenue"));
                                event.setEventCoordinator(obj.getString("EventCoordinator"));
                                event.setEventCoordinatorNumber(obj.getString("EventCoordinatorNumber"));


                                eventList.add(event);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(eventReq);


    }  //End onCreate


    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}

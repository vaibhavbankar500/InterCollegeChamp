package com.example.vaibhav.intercollegechamp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.vaibhav.intercollegechamp.adater.CustomAdapter;
import com.example.vaibhav.intercollegechamp.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.vaibhav.intercollegechamp.UserModel.Class1;
import static com.example.vaibhav.intercollegechamp.UserModel.CollegeName1;
import static com.example.vaibhav.intercollegechamp.UserModel.Dept1;
import static com.example.vaibhav.intercollegechamp.UserModel.Email1;
import static com.example.vaibhav.intercollegechamp.UserModel.FirstName1;
import static com.example.vaibhav.intercollegechamp.UserModel.LastName1;
import static com.example.vaibhav.intercollegechamp.UserModel.Mobile1;
import static com.example.vaibhav.intercollegechamp.UserModel.eventapplyurl;
import static com.example.vaibhav.intercollegechamp.UserModel.registerurl;
import static com.example.vaibhav.intercollegechamp.UserModel.server_user_url;
import static com.example.vaibhav.intercollegechamp.UserModel.serverurl;

public class EventDetailsActivity extends AppCompatActivity {


    // Events json url
    private static final String url = server_user_url;
    private static final String eventapplyrurl= eventapplyurl;

    private TextView EventID;
    private TextView EventName;
    private TextView EventDate;
    private TextView EventTime;
    private TextView EventLastDate;
    private TextView EventVenue;
    private TextView EventCoordinator;
    private TextView EventCoordinatorNumber;
    private TextView EventRules;

    private Button ApplyButton;

    int i=0;


    private CustomAdapter adapter;



    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();
    public Context context;

    private ProgressDialog pDialog;
    private List<User> userList = new ArrayList<User>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        EventID = (TextView) findViewById(R.id.et_eventID);
        EventName = (TextView) findViewById(R.id.et_eventName);
        EventDate = (TextView) findViewById(R.id.et_eventDate);
        EventTime = (TextView) findViewById(R.id.et_eventTime);
        EventLastDate = (TextView) findViewById(R.id.et_eventLastDate);
        EventVenue = (TextView) findViewById(R.id.et_eventVenue);
        EventCoordinator = (TextView) findViewById(R.id.et_eventCoordinator);
        EventCoordinatorNumber = (TextView) findViewById(R.id.et_eventCoordinatorNumber);
        EventRules = (TextView) findViewById(R.id.et_eventRules);

       // adapter = new CustomAdapter(this, eventList);
        Intent intent = getIntent();
        final String EventID1 = intent.getStringExtra("EventID1");
        String EventName1 = intent.getStringExtra("EventName1");
        String EventDate1 = intent.getStringExtra("EventDate1");
        String EventTime1 = intent.getStringExtra("EventTime1");
        String EventLastDate1 = intent.getStringExtra("EventLastDate1");
        String EventVenue1 = intent.getStringExtra("EventVenue1");
        String EventCoordinator1 = intent.getStringExtra("EventCoordinator1");
        String EventCoordinatorNumber1 = intent.getStringExtra("EventCoordinatorNumber1");
        String EventRules1 = intent.getStringExtra("EventRules1");


        EventID.setText(EventID1);
        EventName.setText(EventName1);
        EventDate.setText(EventDate1);
        EventTime.setText(EventTime1);
        EventLastDate.setText(EventLastDate1);
        EventVenue.setText(EventVenue1);
        EventCoordinator.setText(EventCoordinator1);
        EventCoordinatorNumber.setText(EventCoordinatorNumber1);
        EventRules.setText(EventRules1);



        ApplyButton = (Button) findViewById(R.id.bt_eventApply);

        ApplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*FirstName1=userList.get(0).getFirstname().toString();
                LastName1=userList.get(0).getLastname().toString();
                CollegeName1=userList.get(0).getCollegename().toString();
                Dept1=userList.get(0).getDept().toString();
                Class1=userList.get(0).getSclass().toString();
                Email1=userList.get(0).getEmail().toString();
                Mobile1=userList.get(0).getMobile().toString();
*/

                eventapply(Email1,EventID1);

            }
        });



    } //End onCreate()

    public void eventapply(String email, String eventID)
    {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(EventDetailsActivity.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();


            }

            @Override
            protected String doInBackground(String... params) {



                HashMap<String, String> data = new HashMap<String,String>();
                data.put("email", params[0]);
                data.put("eventid", params[1]);

                String result = ruc.sendPostRequest(eventapplyrurl,data);

                return  result;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(email,eventID);
        //String str =ru.execute(email1,username,password,fullName,mobileno, address,dob, bloodgroup).toString();
        //  Toast.makeText(this, str, Toast.LENGTH_LONG).show();

    }



}  //End EventDetailsActivity

package com.example.vaibhav.intercollegechamp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.baasbox.android.BaasDocument;
import com.baasbox.android.BaasHandler;
import com.baasbox.android.BaasResult;
import com.baasbox.android.BaasUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.example.vaibhav.intercollegechamp.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.vaibhav.intercollegechamp.UserModel.Class1;
import static com.example.vaibhav.intercollegechamp.UserModel.CollegeName1;
import static com.example.vaibhav.intercollegechamp.UserModel.Dept1;
import static com.example.vaibhav.intercollegechamp.UserModel.Email1;
import static com.example.vaibhav.intercollegechamp.UserModel.FirstName1;
import static com.example.vaibhav.intercollegechamp.UserModel.LastName1;
import static com.example.vaibhav.intercollegechamp.UserModel.Mobile1;
import static com.example.vaibhav.intercollegechamp.UserModel.currentuser1;
import static com.example.vaibhav.intercollegechamp.UserModel.serverurl;

public class MainActivity extends AppCompatActivity {


    private String urlJsonArry = serverurl;

    private String jsonResponse;
    private FirebaseAuth firebaseAuth;

    TextView logged_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        makeJsonArrayRequest();

        final Button bt_profile_main_act_01= (Button) findViewById(R.id.bt_profile_main_act_01);
        final Button bt_event_main_act_01= (Button) findViewById(R.id.bt_event_main_act_01);
        final Button bt_logout_main = (Button) findViewById(R.id.bt_logout_main);
        final Button bt_aboutus = (Button) findViewById(R.id.bt_aboutus);

        logged_user=(TextView) findViewById(R.id.tv_current_user);

        logged_user.setText(currentuser1);

        Toast.makeText(this,currentuser1, Toast.LENGTH_SHORT).show();

        //Toast.makeText(this, FirstName1+LastName1+CollegeName1+Dept1+Class1+Email1+Mobile1, Toast.LENGTH_SHORT).show();


        bt_profile_main_act_01.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent student_profile= new Intent(MainActivity.this,ProfileActivity.class);
                MainActivity.this.startActivity(student_profile);
            }
        }
        );


        bt_aboutus.setOnClickListener(new Button.OnClickListener(){
          @Override
            public void onClick(View v){
              Intent aboutus=new Intent(MainActivity.this,AboutUs.class);
              MainActivity.this.startActivity(aboutus);
          }
        });

/*
        bt_event_main_act_01.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent event_swipe=new Intent(MainActivity.this,EventSwipeActivity.class);
                MainActivity.this.startActivity(event_swipe);
            }
        }
        );
        */


        bt_event_main_act_01.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent event_list=new Intent(MainActivity.this,EventListActivity.class);
                MainActivity.this.startActivity(event_list);
            }
        }
        );




        bt_logout_main.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){

                firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.signOut();

// this listener will be called when there is change in firebase user session
                firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    //startActivity(new Intent(MainActivity.this, RegisterActivity.class));

                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                    Toast.makeText(getApplicationContext(), "Logout Successful.", Toast.LENGTH_LONG).show();

                }






                /*
                BaasUser.current().logout(new BaasHandler<Void>() {
                    @Override
                    public void handle(BaasResult<Void> result) {
                        if(result.isSuccess()) {
                            Log.d("LOG", "Logged out: "+(BaasUser.current() == null));
                            Intent intent = new Intent(MainActivity.this,
                                    LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                            Toast.makeText(getApplicationContext(), "Logout Successful.", Toast.LENGTH_LONG).show();
                        } else{
                            Log.e("LOG","Show error",result.error());
                        }
                    };
                });


                */



                /*
                SharedPreferences myPrefs = getSharedPreferences("Activity",
                        MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.clear();
                editor.commit();
                finish();
                //AppState.getSingleInstance().setLoggingOut(true);
               // setLoginState(true);
               // Log.d(TAG, "Now log out and start the activity login");
                Intent intent = new Intent(MainActivity.this,
                        LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Logout Successful.", Toast.LENGTH_LONG).show();
                */
                /*
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null){
                    // User is signed in
                    //For Signout
                    //firebaseAuth.signOut();
                    //user.unauth(); //End user session

                    Toast.makeText(MainActivity.this,"Signout Successfully",Toast.LENGTH_SHORT).show();

                    Intent student_signout= new Intent(MainActivity.this,RegisterActivity.class);
                    MainActivity.this.startActivity(student_signout);


                }*/

            }


        }
        );

        /*
        private void signOut(){
            firebaseAuth.signOut();
            //updateUI(null);
        }
        */


    }


/*
    *//**
     * Method to make json array request where response starts with [
     * *//*
    private void makeJsonArrayRequest() {



        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,new Response.Listener<JSONArray>()
        {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("JSON", response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            jsonResponse = "";
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject person = (JSONObject) response
                                        .get(i);

                                String EventId = person.getString("EventId");
                                String EventName = person.getString("EventName");
                                String EventType = person.getString("EventType");
                                String EventRules = person.getString("EventRules");
                                String EventDate = person.getString("EventDate");
                                String EventTime = person.getString("EventTime");
                                String EventLastDate = person.getString("EventLastDate");
                                String EventVenue = person.getString("EventVenue");
                                String EventCoordinator = person.getString("EventCoordinator");
                                String EventCoordinatorNumber = person.getString("EventCoordinatorNumber");




                                jsonResponse += "EventId: " + EventId + "\n\n";
                                jsonResponse += "EventName: " + EventName + "\n\n";
                                jsonResponse += "EventType: " + EventType + "\n\n";
                                jsonResponse += "EventRules: " + EventRules + "\n\n\n";
                                jsonResponse += "EventDate: " + EventDate + "\n\n\n";
                                jsonResponse += "EventTime: " + EventTime + "\n\n";
                                jsonResponse += "EventLastDate: " + EventLastDate + "\n\n";
                                jsonResponse += "EventVenue: " + EventVenue + "\n\n";
                                jsonResponse += "EventCoordinator: " + EventDate + "\n\n\n";
                                jsonResponse += "EventCoordinatorNumber: " + EventDate + "\n\n\n";

                                //Toast.makeText(getApplicationContext(),jsonResponse, Toast.LENGTH_SHORT).show();

                            }

                           // Toast.makeText(getApplicationContext(),jsonResponse, Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("VOLLEY", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }
*/


}

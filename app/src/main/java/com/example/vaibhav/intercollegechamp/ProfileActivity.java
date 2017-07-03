package com.example.vaibhav.intercollegechamp;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.baasbox.android.BaasHandler;
import com.baasbox.android.BaasResult;
import com.baasbox.android.BaasUser;
import com.baasbox.android.json.JsonObject;
import com.example.vaibhav.intercollegechamp.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.vaibhav.intercollegechamp.UserModel.Class1;
import static com.example.vaibhav.intercollegechamp.UserModel.CollegeName1;
import static com.example.vaibhav.intercollegechamp.UserModel.Dept1;
import static com.example.vaibhav.intercollegechamp.UserModel.Email1;
import static com.example.vaibhav.intercollegechamp.UserModel.FirstName1;
import static com.example.vaibhav.intercollegechamp.UserModel.LastName1;
import static com.example.vaibhav.intercollegechamp.UserModel.Mobile1;
import static com.example.vaibhav.intercollegechamp.UserModel.server_user_url;

public class ProfileActivity extends AppCompatActivity {

    private static final String url = server_user_url;

    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();
    public Context context;

    private ProgressDialog pDialog;
    private List<User> userList = new ArrayList<User>();


    EditText FirstName;
    EditText LastName;
    EditText CollegeName;
    EditText Dept;
    EditText Class;
    EditText Email;
    EditText Mobile;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        FirstName = (EditText) findViewById(R.id.et_firstname);
        LastName = (EditText) findViewById(R.id.et_lastname);
        CollegeName = (EditText) findViewById(R.id.et_collegename);
        Dept = (EditText) findViewById(R.id.et_dept);
        Class = (EditText) findViewById(R.id.et_class);
        Email = (EditText) findViewById(R.id.et_email);
        Mobile = (EditText) findViewById(R.id.et_mobile);


/*

        FirstName.setText(userList.get(0).getEmail());
        LastName.setText(userList.get(0).getEmail());
        CollegeName.setText(userList.get(0).getEmail());
        Dept.setText(userList.get(0).getEmail());
        Class.setText(userList.get(0).getEmail());
        Email.setText(Email1);
        Mobile.setText(userList.get(0).getEmail());

*/



        JsonArrayRequest eventReq = new JsonArrayRequest(url,new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());


                // Parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject obj = response.getJSONObject(i);
                        User user = new User();


                        user.setFirstname(obj.getString("first_name"));
                        user.setLastname(obj.getString("last_name"));
                        user.setCollegename(obj.getString("college_name"));
                        user.setDept(obj.getString("department"));
                        user.setSclass(obj.getString("class"));
                        user.setEmail(obj.getString("email_id"));
                        user.setPassword(obj.getString("password"));
                        user.setMobile(obj.getString("mobile"));

                         //Toast.makeText(ProfileActivity.this, obj.getString("email_id"), Toast.LENGTH_SHORT).show();
                        //userList.add(user);

                        //Toast.makeText(ProfileActivity.this, userList.get(0).getEmail(), Toast.LENGTH_SHORT).show();


                        if(obj.getString("email_id").toString().equals(Email1)){
                            userList.add(user);
                            //Toast.makeText(ProfileActivity.this, userList.get(0).getEmail(), Toast.LENGTH_SHORT).show();

                            FirstName.setText(userList.get(0).getFirstname());
                            LastName.setText(userList.get(0).getLastname());
                            CollegeName.setText(userList.get(0).getCollegename());
                            Dept.setText(userList.get(0).getDept());
                            Class.setText(userList.get(0).getSclass());
                            Email.setText(Email1);
                            Mobile.setText(userList.get(0).getMobile());

                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                // notifying list adapter about data changes
                // so that it renders the list view with updated data
                // adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());


            }
        });


        AppController.getInstance().addToRequestQueue(eventReq);




    }//End onCreate
}

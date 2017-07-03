package com.example.vaibhav.intercollegechamp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baasbox.android.BaasClientException;
import com.baasbox.android.BaasDocument;
import com.baasbox.android.BaasException;
import com.baasbox.android.BaasHandler;
import com.baasbox.android.BaasResult;
import com.baasbox.android.BaasServerException;
import com.baasbox.android.BaasUser;
import com.example.vaibhav.intercollegechamp.util.AlertUtils;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.R.attr.name;
import static android.R.attr.password;
import static android.R.attr.theme;
import static android.R.attr.thickness;
import static com.example.vaibhav.intercollegechamp.UserModel.Class1;
import static com.example.vaibhav.intercollegechamp.UserModel.CollegeName1;
import static com.example.vaibhav.intercollegechamp.UserModel.Dept1;
import static com.example.vaibhav.intercollegechamp.UserModel.Email1;
import static com.example.vaibhav.intercollegechamp.UserModel.FirstName1;
import static com.example.vaibhav.intercollegechamp.UserModel.LastName1;
import static com.example.vaibhav.intercollegechamp.UserModel.Mobile1;
import static com.example.vaibhav.intercollegechamp.UserModel.currentuser1;
import static com.example.vaibhav.intercollegechamp.UserModel.registerurl;

public class StudentSignUpActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText FirstName;
    private EditText LastName;
    private EditText CollegeName;
    private EditText Dept;
    private EditText Class;
    private EditText Email;
    private EditText Password;
    private EditText Mobile;
    private Button signupButton;


    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    //private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);


        final Drawable errorIcon = (Drawable)getResources().getDrawable(R.drawable.tickmark);
        errorIcon.setBounds(new Rect(0, 0, errorIcon.getIntrinsicWidth(), errorIcon.getIntrinsicHeight()));


        firebaseAuth = FirebaseAuth.getInstance();
        //FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        progressDialog = new ProgressDialog(this);

        FirstName = (EditText) findViewById(R.id.etFirstName);
        LastName = (EditText) findViewById(R.id.etLastName);
        CollegeName = (EditText) findViewById(R.id.etCollegeName);
        Dept = (EditText) findViewById(R.id.etDept);
        Class = (EditText) findViewById(R.id.etClass);
        Email = (EditText) findViewById(R.id.etEmail);
        Password = (EditText) findViewById(R.id.etPassword);
        Mobile = (EditText) findViewById(R.id.etMobile);
        signupButton = (Button) findViewById(R.id.signupButton);

        signupButton.setOnClickListener(this);


        Mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                if (Mobile.getText().toString().length()==10)
                {
                    Mobile.setError("Correct",errorIcon);
                }
                else
                {
                    Mobile.setError("Invalid Mobile Number");
                    //  etMobilenumber.setBackgroundColor(Color.RED);
                }
            }
        });


        Email.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void afterTextChanged(Editable s) {
                String email = Email.getText().toString();
                //Toast.makeText(StudentSignUpActivity.this, email, Toast.LENGTH_SHORT).show();
                if (isValidEmail(email))
                {
                    Email.setError("Correct",errorIcon);


                }
                else
                {
                    Email.setError("Invalid Email Address");

            }

            }
        });




    }


    private void registerUser(){
        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();
        String firstname = FirstName.getText().toString().trim();
        String lastname = LastName.getText().toString().trim();
        String collegename = CollegeName.getText().toString().trim();
        String dept = Dept.getText().toString().trim();
        String sclass = Class.getText().toString().trim();
        String mobile = Mobile.getText().toString().trim();




        if(TextUtils.isEmpty(email)){
            //Email is empty
            Toast.makeText(this,"Please Enter Email.",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            //Password is empty
            Toast.makeText(this,"Please Enter Password.",Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidEmail(email)) {
            Toast.makeText(getApplicationContext(), "Invalid Email Address", Toast.LENGTH_LONG).show();

            Email.setError("Invalid Email");
            finish();
            startActivity(getIntent());        // To Reload Same Activity
        }


        if (!isValidPassword(password)) {
            Toast.makeText(getApplicationContext(), "Invalid password", Toast.LENGTH_LONG).show();

            Password.setError("Invalid Password");
            finish();
            startActivity(getIntent());        // To Reload Same Activity
        }



        //if validations are ok
        //we will first show progress dialog        startActivity(intent);


        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //User is Successfully Registered and logged in
                            Toast.makeText(StudentSignUpActivity.this,"Register Successfully",Toast.LENGTH_SHORT).show();

        //Toast.makeText(this, FirstName1+LastName1+CollegeName1+Dept1+Class1+Email1+Mobile1, Toast.LENGTH_SHORT).show();
                            //we will start main activity here.
                            Intent student_signup= new Intent(StudentSignUpActivity.this,MainActivity.class);
                            StudentSignUpActivity.this.startActivity(student_signup);

                        }
                        else {
                            Toast.makeText(StudentSignUpActivity.this,"Registration Failed.",Toast.LENGTH_SHORT).show();
                            finish();

                        }

                    }
                });




    }

    private void userinfo(){


        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();
        String firstname = FirstName.getText().toString().trim();
        String lastname = LastName.getText().toString().trim();
        String collegename = CollegeName.getText().toString().trim();
        String dept = Dept.getText().toString().trim();
        String sclass = Class.getText().toString().trim();
        String mobile = Mobile.getText().toString().trim();


        FirstName1=firstname;
        LastName1=lastname;
        CollegeName1=collegename;
        Dept1=dept;
        Class1=sclass;
        Email1=email;
        Mobile1=mobile;


        register(firstname,lastname,collegename,dept,sclass, email,password,mobile);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Student_Profile");

        myRef.child(firstname).child("email").setValue(email);
        myRef.child(firstname).child("firstname").setValue(firstname);
        myRef.child(firstname).child("lastname").setValue(lastname);
        myRef.child(firstname).child("collegename").setValue(collegename);
        myRef.child(firstname).child("dept").setValue(dept);
        myRef.child(firstname).child("sclass").setValue(sclass);
        myRef.child(firstname).child("mobile").setValue(mobile);


        //Toast.makeText(this, FirstName1+LastName1+CollegeName1+Dept1+Class1+Email1+Mobile1, Toast.LENGTH_SHORT).show();

    }





    @Override
    public void onClick(View view) {
        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();
        String firstname = FirstName.getText().toString().trim();
        String lastname = LastName.getText().toString().trim();
        String collegename = CollegeName.getText().toString().trim();
        String dept = Dept.getText().toString().trim();
        String sclass = Class.getText().toString().trim();
        String mobile = Mobile.getText().toString().trim();
        if(view == signupButton){
            if(isValidEmail(email) && isValidPassword(password) && !firstname.equals("") &&
                    !lastname.equals("") &&
                    !collegename.equals("") &&
                    !dept.equals("") &&
                    !sclass.equals("")
                    && !mobile.equals("")) {
                registerUser();
                userinfo();
            }
            else {
                Toast.makeText(this, "enter valid detailS", Toast.LENGTH_SHORT).show();
            }
        }

    }
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }



    public void register(String firstname,String lastname,String collegename,String dept,String sclass,String email,String password,String mobile)
    {
       class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(StudentSignUpActivity.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                if(s.equals("successfully registered"))
                {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }

            }

            @Override
            protected String doInBackground(String... params) {



                HashMap<String, String> data = new HashMap<String,String>();
                data.put("firstname", params[0]);
                data.put("lastname", params[1]);
                data.put("collegename",params[2]);
                data.put("dept", params[3]);
                data.put("sclass", params[4]);
                data.put("email", params[5]);
                data.put("password", params[6]);
                data.put("mobile", params[7]);

                String result = ruc.sendPostRequest(registerurl,data);

                return  result;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(firstname,lastname,collegename,dept,sclass, email,password,mobile);
        //String str =ru.execute(email1,username,password,fullName,mobileno, address,dob, bloodgroup).toString();
        //  Toast.makeText(this, str, Toast.LENGTH_LONG).show();

    }



}



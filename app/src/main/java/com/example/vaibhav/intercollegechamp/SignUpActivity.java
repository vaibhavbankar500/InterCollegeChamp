package com.example.vaibhav.intercollegechamp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        setContentView(R.layout.activity_sign_up);
        final Button bt_sign_up_01= (Button) findViewById(R.id.bt_sign_up_01);

        bt_sign_up_01.setOnClickListener(new Button.OnClickListener(){
                                                   @Override
                                                   public void onClick(View v){
                                                       Intent student_signup= new Intent(SignUpActivity.this,StudentSignUpActivity.class);
                                                       SignUpActivity.this.startActivity(student_signup);
                                                   }
                                               }
        );


        final Button bt_sign_up_02= (Button) findViewById(R.id.bt_sign_up_02);

        bt_sign_up_02.setOnClickListener(new Button.OnClickListener(){
                                             @Override
                                             public void onClick(View v){
                                                 Intent college_signup= new Intent(SignUpActivity.this,CollegeSignUpActivity.class);
                                                 SignUpActivity.this.startActivity(college_signup);
                                             }
                                         }
        );

    }


}

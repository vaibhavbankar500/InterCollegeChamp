package com.example.vaibhav.intercollegechamp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_1);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            // user auth state is changed - user is null
            // launch login activity
            //startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();


        }

        setContentView(R.layout.activity_register_1);
        final Button bt_login_reg_act_01= (Button) findViewById(R.id.bt_login_reg_act_01);

        bt_login_reg_act_01.setOnClickListener(new Button.OnClickListener(){
                                                      @Override
                                                      public void onClick(View v){
                                                          Intent reg_login= new Intent(RegisterActivity.this,LoginActivity.class);
                                                          RegisterActivity.this.startActivity(reg_login);
                                                      }
                                                  }
        );

        final Button bt_signup_reg_act_01= (Button) findViewById(R.id.bt_signup_reg_act_01);

        bt_signup_reg_act_01.setOnClickListener(new Button.OnClickListener(){
                                                   @Override
                                                   public void onClick(View v){
                                                       Intent reg_signup= new Intent(RegisterActivity.this,SignUpActivity.class);
                                                       RegisterActivity.this.startActivity(reg_signup);
                                                   }
                                               }
        );


    }
}

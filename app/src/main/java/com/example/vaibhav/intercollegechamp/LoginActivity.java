package com.example.vaibhav.intercollegechamp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baasbox.android.BaasClientException;
import com.baasbox.android.BaasException;
import com.baasbox.android.BaasResult;
import com.baasbox.android.BaasServerException;
import com.baasbox.android.BaasUser;
import com.example.vaibhav.intercollegechamp.util.AlertUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.vaibhav.intercollegechamp.UserModel.Email1;
import static com.example.vaibhav.intercollegechamp.UserModel.currentuser1;


public class LoginActivity extends AppCompatActivity {


//    private LoginTask loginTask;

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView signuplink;
    private TextView password_reset;

    private FirebaseAuth firebaseAuth;


    @SuppressWarnings("static-access")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /*
        if ( BaasUser.current().isAuthentcated()) {
            onUserLogged();
            return;
        }
        */
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        setTitle("Login");

        setContentView(R.layout.activity_login);

        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.loginButton);
        signuplink=(TextView)findViewById(R.id.tv_signupLink);
        password_reset=(TextView) findViewById(R.id.tv_password_reset);

        firebaseAuth = FirebaseAuth.getInstance();

        password_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Password_Reset=new Intent(LoginActivity.this,ResetPasswordActivity.class);
                LoginActivity.this.startActivity(Password_Reset);

            }
        });



        signuplink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupLink=new Intent(LoginActivity.this,SignUpActivity.class);
                LoginActivity.this.startActivity(signupLink);
            }
        });


        //firebaseAuth = FirebaseAuth.getInstance();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if(email.equals("") || password.equals("")){
                    Toast.makeText(LoginActivity.this, "Enter valid details", Toast.LENGTH_SHORT).show();
                } else {
                firebaseAuth = FirebaseAuth.getInstance();
                currentuser1=email;
                onClickLogin(email,password); }
            }
        });

//        findViewById(R.id.signupLink).setOnClickListener(new View.OnClickListener() {
//  /*//        usernameEditText = (EditText) findViewById(R.id.username);
//        passwordEditText = (EditText) findViewById(R.id.password);
//        loginButton = (Button) findViewById(R.id.loginButton);
//
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String username = usernameEditText.getText().toString();
//                String password = passwordEditText.getText().toString();
//
//                onClickLogin(username, password);
//            }
//        });
//*/          @Override
//            public void onClick(View v) {
//                onClickSignup();
//            }
//        });
    }//end_oncreate

    public void onUserLogged() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Login Successful.", Toast.LENGTH_LONG).show();
        Email1=usernameEditText.getText().toString();
        finish();
    }

    protected void onClickSignup() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);

    }

    public void onClickLogin(String email, String password) {
//        loginTask = new LoginTask();
//        loginTask.execute(username, password);
       // firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Login Successful", "signInWithEmail:onComplete:" + task.isSuccessful());
                        //Toast.makeText(getApplicationContext(), "Login Successful.", Toast.LENGTH_LONG).show();


                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("Login Unsuccessful", "signInWithEmail", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        if (task.isSuccessful()) {
                            onUserLogged();
                        }

                        // ...
                    }
                });


    }

//    protected void onLogin(BaasResult<BaasUser> result) {
//        try {
//            result.get();
//            onUserLogged();
//        } catch (BaasClientException e) {
//            if (e.httpStatus == 401) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setCancelable(true);
//                builder.setTitle("Login failed");
//                builder.setMessage("Invalid username or password");
//                builder.setNegativeButton("Cancel", null);
//                builder.create().show();
//            } else {
//                AlertUtils.showErrorAlert(this, e);
//            }
//        } catch (BaasServerException e) {
//            AlertUtils.showErrorAlert(this, e);
//        } catch (BaasException e) {
//            AlertUtils.showErrorAlert(this, e);
//        }
//    }

//    public class LoginTask extends AsyncTask<String, Void, BaasResult<BaasUser>> {
//
//        @Override
//        protected void onPreExecute() {
//            loginButton.setEnabled(false);
//        }
//
//        @Override
//        protected BaasResult<BaasUser> doInBackground(String... params) {
//            BaasUser user = BaasUser.withUserName(params[0]);
//            user.setPassword(params[1]);
//            return user.loginSync();
//        }
//
//        @Override
//        protected void onPostExecute(BaasResult<BaasUser> result) {
//            loginButton.setEnabled(true);
//            onLogin(result);
//        }
//    }


}

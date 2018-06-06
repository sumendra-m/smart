package com.example.android.smart;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    TextView signupTxt;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signupTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = email.getText().toString();
                String pass = password.getText().toString();
                SharedPreferences sp = getSharedPreferences("myfile", Context.MODE_PRIVATE);
                String myuser = sp.getString("user", null);
                String mypass = sp.getString("pass", null);
                if(user.equals(myuser) && pass.equals(mypass)){
                    if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)){
                        Toast.makeText(LoginActivity.this, "Username and password fields cannot be empty.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Login successful.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                        startActivity(intent);
                        SharedPreferences sp1 = getSharedPreferences("yourfile", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp1.edit();
                        editor.putBoolean("state", true);
                        editor.apply();
                    }
                }
                else{
                    Toast.makeText(LoginActivity.this, "Login failure. Username or password is wrong.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        SharedPreferences sp = getSharedPreferences("yourfile", MODE_PRIVATE);
        boolean state = sp.getBoolean("state", false);
        if(state){
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);
        }
        super.onStart();
    }
}

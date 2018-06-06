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

public class SignupActivity extends AppCompatActivity {
    EditText email,password;
    TextView loginTxt;
    Button signupBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email=findViewById(R.id.email_address);
        password=findViewById(R.id.password);
        loginTxt=findViewById(R.id.txt_signin);
        signupBtn=findViewById(R.id.btn_signup);

        loginTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("myfile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                String user = email.getText().toString();
                String pass = password.getText().toString();
                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)){
                    Toast.makeText(SignupActivity.this, "Username and password fields cannot be empty.", Toast.LENGTH_SHORT).show();
                }
                else {
                    editor.putString("user", user);
                    editor.putString("pass", pass);
                    editor.apply();
                    Intent intent = new Intent(SignupActivity.this, DashboardActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}

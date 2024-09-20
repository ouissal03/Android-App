package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    EditText username, login, password;
    String strUsername, strLogin, strPass;
    Button signin, signup;
    DBHelper myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        myDatabase = new DBHelper(this);

        username = findViewById(R.id.edtTxtName);
        login = findViewById(R.id.edtTxtLoginS);
        password = findViewById(R.id.edtTxtPasswordS);


        signup = findViewById(R.id.btnSignUpS);
        signin = findViewById(R.id.btnSignInS);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strUsername = username.getText().toString();
                strLogin = login.getText().toString();
                strPass = password.getText().toString();

                if (strUsername.isEmpty() || strLogin.isEmpty() || strPass.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Invalid data :(", Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User(strUsername, strLogin, strPass);
                    myDatabase.addUser(user);
                    Toast.makeText(SignupActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignInActivity();
            }
        });
    }

    private void openSignInActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
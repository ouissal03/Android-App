package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText login, password;
    String strLogin, strPass;
    Button signin, signup;
    DBHelper myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDatabase = new DBHelper(this);

        login = findViewById(R.id.edtTxtLogin);
        password = findViewById(R.id.edtTxtPassword);

        signin = findViewById(R.id.btnSignIn);
        signup = findViewById(R.id.btnSignUp);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strLogin = login.getText().toString();
                strPass = password.getText().toString();

                if (myDatabase.verifyUser(strLogin, strPass)) {
                    openFirstActivity();
                } else
                    Toast.makeText(MainActivity.this, "Invalid Login or Password", Toast.LENGTH_SHORT).show();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignUpActivity();
            }
        });
    }

    private void openFirstActivity() {
        Intent intent = new Intent(this, FirstActivity.class);
        startActivity(intent);
        finish();
    }

    private void openSignUpActivity() {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
        finish();
    }
}
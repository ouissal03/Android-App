package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class FinalActivity extends AppCompatActivity {
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        Bundle bundle = getIntent().getExtras();
        String result = bundle.getString("result");

        txtResult = findViewById(R.id.txtResult);
        txtResult.setText(result);
    }
}
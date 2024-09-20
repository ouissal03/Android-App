package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    Button next;
    static int helper = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        radioGroup = findViewById(R.id.radioGrp);
        next = findViewById(R.id.btnNext);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.first:
                        helper = 1;
                        break;
                    case R.id.second:
                        helper = 2;
                        break;
                    default:
                        break;
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFormActivity();
            }
        });

    }

    private void openFormActivity() {
        Intent intent = new Intent(this, FormActivity.class);
        Bundle bundle = new Bundle();

        bundle.putInt("helper", helper);
        intent.putExtras(bundle);

        startActivity(intent);
        finish();
    }

}
package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.csv.CSVRecord;

import java.util.List;
import java.util.stream.Collectors;

public class FormActivity extends AppCompatActivity {
    EditText edtTxtAge, edtTxtGender, edtTxtBP, edtTxtCh, edtTxtNa, edtTxtK;
    Button btnEvaluate;
    String result;
    List<CSVRecord> records;
    List<PersonRecord> personRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        edtTxtAge = findViewById(R.id.edtTxtAge);
        edtTxtGender = findViewById(R.id.edtTxtGender);
        edtTxtBP = findViewById(R.id.edtTxtBP);
        edtTxtCh = findViewById(R.id.edtTxtCh);
        edtTxtNa = findViewById(R.id.edtTxtNa);
        edtTxtK = findViewById(R.id.edtTxtK);

        records = CSVLoader.parseCSV(getResources().openRawResource(R.raw.data));
        personRecords = records.stream()
                .map((record) -> PersonRecordUtil.parsePersonRecord(record))
                .collect(Collectors.toList());

        Bundle bundle = getIntent().getExtras();
        int helper = bundle.getInt("helper");

        btnEvaluate = findViewById(R.id.btnEvaluate);
        btnEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (empty() == 1) {
                    switch (helper) {
                        case 1:
                            result = useKnnAlgo();
                            openResultActivity();
                            break;
                        case 2:
                            result = useBayesAlgo();
                            openResultActivity();
                            break;
                        default:
                            break;
                    }
                } else
                    Toast.makeText(FormActivity.this, "There is an empty field.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void openResultActivity() {
        Intent intent = new Intent(this, FinalActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString("result", result);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    private int empty() {
        String age, gender, bp, ch, na, k;

        age = edtTxtAge.getText().toString();
        gender = edtTxtGender.getText().toString();
        bp = edtTxtBP.getText().toString();
        ch = edtTxtCh.getText().toString();
        na = edtTxtNa.getText().toString();
        k = edtTxtK.getText().toString();

        if (age.isEmpty() || gender.isEmpty() || bp.isEmpty() || ch.isEmpty() || na.isEmpty() || k.isEmpty()) {
            return 0;
        } else return 1;
    }

    private PersonRecord getPersonRecord() {
        Integer age, gender, bp, ch, na, k;

        age = encodeAge(edtTxtAge.getText().toString());
        gender = encodeGender(edtTxtGender.getText().toString());
        bp = encodeBP(edtTxtBP.getText().toString());
        ch = encodeCh(edtTxtCh.getText().toString());
        na = encodeNa(edtTxtNa.getText().toString());
        k = encodeK(edtTxtK.getText().toString());

        return new PersonRecord(age, gender, bp, ch, na, k, "?");
    }

    public String useKnnAlgo() {
        KNNClassifier classifier = new KNNClassifier();
        personRecords.forEach((person) -> classifier.addDataItem(person));
        PersonRecord per = getPersonRecord();

        return classifier.predict(per, 10).getKey();
    }

    public String useBayesAlgo() {
        NaiveBayesClassifier classifier = new NaiveBayesClassifier();
        personRecords.forEach((person) -> classifier.addDataItem(person));
        PersonRecord per = getPersonRecord();

        return classifier.predict(per).getKey();
    }

    private Integer encodeAge(String strAge) {
        Integer age;
        if (Integer.parseInt(strAge) <= 43) age = 0;
        else age = 1;
        return age;
    }

    private Integer encodeGender(String strGender) {
        Integer gender;
        if (strGender.toLowerCase().equals("female")) gender = 0;
        else if (strGender.toLowerCase().equals("male")) gender = 1;
        else return null;
        return gender;
    }

    private Integer encodeBP(String strBP) {
        Integer bp;
        if (strBP.toLowerCase().equals("low")) bp = 0;
        else if (strBP.toLowerCase().equals("normal")) bp = 1;
        else if (strBP.toLowerCase().equals("high")) bp = 2;
        else return null;
        return bp;
    }

    private Integer encodeCh(String strCh) {
        Integer ch;
        if (strCh.toLowerCase().equals("normal")) ch = 1;
        else if (strCh.toLowerCase().equals("high")) ch = 2;
        else return null;
        return ch;
    }

    private Integer encodeNa(String strNa) {
        Integer na;
        if (Double.valueOf(strNa) <= 0.718289) na = 0;
        else na = 1;
        return na;
    }

    private Integer encodeK(String strK) {
        Integer k;
        if (Double.valueOf(strK) <= 0.052486) k = 0;
        else k = 1;
        return k;
    }
}
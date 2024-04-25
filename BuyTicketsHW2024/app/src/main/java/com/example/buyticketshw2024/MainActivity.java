package com.example.buyticketshw2024;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private String outputStr = "";
    private String gender = "";
    private String type = "";
    private int numberOfTickets = 0;
    private int price = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup rgGender = (RadioGroup) findViewById(R.id.rgGender);
        RadioGroup rgType = (RadioGroup) findViewById(R.id.rgType);
        EditText edtNumberOfTickets = (EditText) findViewById(R.id.edtNumberOfTickets);
        Button btnConfirm = (Button) findViewById(R.id.btnConfirm);

        gender = getResources().getString(R.string.male);
        type = getResources().getString(R.string.regularticket);

       rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {
               if (checkedId == R.id.rdbBoy) {
                   gender = getResources().getString(R.string.male);
               }
               else {
                   gender = getResources().getString(R.string.female);
               }
               showResult();
           }
       });

       rgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.rdbAdult) {
                 type = getResources().getString(R.string.regularticket);
                 price = 500;
            }
            else if (checkedId == R.id.rdbChild) {
                 type = getResources().getString(R.string.childticket);
                 price = 250;
            }
            else {
                 type = getResources().getString(R.string.studentticket);
                 price = 400;
            }
            showResult();
          }
       });

       edtNumberOfTickets.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {
           }
          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                 numberOfTickets = Integer.parseInt(s.toString());
            } catch (NumberFormatException e) {
                 numberOfTickets = 0;
            }
            showResult();
          }
          @Override
          public void afterTextChanged(Editable s) {
          }
       });

       btnConfirm.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                intent.putExtra("outputStr", outputStr);
                startActivity(intent);
           }
       });
    }

    public void showResult() {
        outputStr = String.format("%s\n%s\n%d張\n共 %d元", gender, type, numberOfTickets,
                price * numberOfTickets);
        ((TextView) findViewById(R.id.lblOutput)).setText(outputStr);
    }
}
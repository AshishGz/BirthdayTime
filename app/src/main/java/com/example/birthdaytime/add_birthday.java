package com.example.birthdaytime;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.birthdaytime.getterSetter.birthdayInfo;

public class add_birthday extends AppCompatActivity {
    EditText nameEditText, birthYearEditText, birthMonthEditText, birthDayEeditText, alramHourEditText, alramMinuteEditText, phoneNumberEditText, massageEditText;
    ImageView addBirthday, back, btnAdd, update;
    TextView heading;
    DatabaseHelper databaseHelper;
    Integer id;
    String contactName, contactNumber, flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_birthday);
        id = getIntent().getIntExtra("id", 0);


        databaseHelper = new DatabaseHelper(this);
        nameEditText = (EditText) findViewById(R.id.birthdayNameEditText);
        birthYearEditText = (EditText) findViewById(R.id.birthYearEditText);
        birthMonthEditText = (EditText) findViewById(R.id.birthMonthEditText);
        birthDayEeditText = (EditText) findViewById(R.id.birthDayEditTextx);
        alramHourEditText = (EditText) findViewById(R.id.alramHourEditText);
        alramMinuteEditText = (EditText) findViewById(R.id.alramMinuteEditText);
        phoneNumberEditText = (EditText) findViewById(R.id.phoneNumberEditText);
        massageEditText = (EditText) findViewById(R.id.massageEditText);
        addBirthday = (ImageView) findViewById(R.id.btnAdd);
        btnAdd = (ImageView) findViewById(R.id.btnAdd);
        update = (ImageView) findViewById(R.id.profile);
        heading = (TextView) findViewById(R.id.heading);
        back = (ImageView) findViewById(R.id.btnBack);

        if (id == 0) {

            addBrthdayToDatabase();

        } else {

            updateDataPopulate();


        }
        populateContact();


        backButton();


    }

    public void addBrthdayToDatabase() {
        addBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv = new ContentValues();
                cv.put("Name", nameEditText.getText().toString());
                cv.put("birthYear", birthYearEditText.getText().toString());
                cv.put("birthMonth", birthMonthEditText.getText().toString());
                cv.put("birthDay", birthDayEeditText.getText().toString());
                cv.put("reminderHour", alramHourEditText.getText().toString());
                cv.put("reminderMin", alramMinuteEditText.getText().toString());
                cv.put("phoneNumber", phoneNumberEditText.getText().toString());
                cv.put("massage", massageEditText.getText().toString());
                cv.put("flag", "1");
                databaseHelper.insertBirthday(cv);
                Toast.makeText(add_birthday.this, "data inserted", Toast.LENGTH_LONG).show();
                Log.i("size:", "size inside: add birthday click" + id);
                finish();


            }
        });

    }

    public void backButton() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void updateDataPopulate() {
        birthdayInfo info = databaseHelper.getBirthdayInfo(id + "");
        heading.setText("Edit Birthday");
        btnAdd.setImageResource(R.drawable.ic_add);
        nameEditText.setText(info.getName());
        birthYearEditText.setText(info.getBirthYear());
        birthMonthEditText.setText(info.getBirthMonth());
        birthDayEeditText.setText(info.getBirthDay());
        alramHourEditText.setText(info.getAlramHour());
        alramMinuteEditText.setText(info.getAlramMinute());
        phoneNumberEditText.setText(info.getPhoneNumber());
        massageEditText.setText(info.getMassage());
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateBirthdayValude();
                Log.i("size:", "size inside: update birthday click " + id);
                finish();
            }
        });


    }

    public void updateBirthdayValude() {
        ContentValues cv = new ContentValues();
        cv.put("Name", nameEditText.getText().toString());
        cv.put("birthYear", birthYearEditText.getText().toString());
        cv.put("birthMonth", birthMonthEditText.getText().toString());
        cv.put("birthDay", birthDayEeditText.getText().toString());
        cv.put("reminderHour", alramHourEditText.getText().toString());
        cv.put("reminderMin", alramMinuteEditText.getText().toString());
        cv.put("phoneNumber", phoneNumberEditText.getText().toString());
        cv.put("massage", massageEditText.getText().toString());
        databaseHelper.updateBirthday(cv, id + "");
    }

    public void populateContact() {
        contactName = getIntent().getStringExtra("contactName");
        contactNumber = getIntent().getStringExtra("contactPhone");
        flag = getIntent().getStringExtra("flag");

        nameEditText.setText(contactName);
        phoneNumberEditText.setText(contactNumber);


    }
}
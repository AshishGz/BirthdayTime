package com.example.birthdaytime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.example.birthdaytime.fragment.ageCalculatorFragment;
import com.example.birthdaytime.fragment.birthdayWishesFragment;

public class TaskActivity extends AppCompatActivity {
    ImageView back_task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        String value = getIntent().getStringExtra("value");
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        back_task = (ImageView) findViewById(R.id.back_task);
        if (value.equals("age")) {
            Log.i("value::", "::::::::: " + value);
            transaction.replace(R.id.main_fragment_task, new ageCalculatorFragment());
        } else {
            Log.i("value::", "?????????????? " + value);
            transaction.replace(R.id.main_fragment_task, new birthdayWishesFragment());
        }
        transaction.commit();
    }

    public void back() {

    }

}

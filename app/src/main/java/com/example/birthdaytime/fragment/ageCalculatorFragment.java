package com.example.birthdaytime.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.birthdaytime.R;
import com.example.birthdaytime.javaClass.findAgeClass;


/**
 * Created by admin on 2/10/2017.
 */

public class ageCalculatorFragment extends Fragment {
    DatePicker datePicker;
    TextView showAge;
    Button calAge;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_age_calculator, null);
        datePicker = (DatePicker) view.findViewById(R.id.datepiker);
        showAge = (TextView) view.findViewById(R.id.showAge);
        calAge = (Button) view.findViewById(R.id.calAge);
        calAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userDay = datePicker.getDayOfMonth();
                int userMonth = datePicker.getMonth() + 1;
                int userYear = datePicker.getYear();

                findAgeClass calAgeOf = new findAgeClass();
                String getAge = calAgeOf.findAge(userYear + "", userMonth + "", userDay + "");
                showAge.setText("Your Age is : " + getAge);


            }
        });


        return view;

    }
}

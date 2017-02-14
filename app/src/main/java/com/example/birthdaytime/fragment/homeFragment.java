package com.example.birthdaytime.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.birthdaytime.R;
import com.example.birthdaytime.TaskActivity;

/**
 * Created by admin on 2/9/2017.
 */

public class homeFragment extends Fragment {

    LinearLayout ageCalculator, birthdayWishes, gift, resturent;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_layout, null);
        ageCalculator = (LinearLayout) view.findViewById(R.id.ageCalculator);

        birthdayWishes = (LinearLayout) view.findViewById(R.id.BirthdayWishes);
        resturent = (LinearLayout) view.findViewById(R.id.ageCalculator);
        gift = (LinearLayout) view.findViewById(R.id.ageCalculator);
        homeClicable();


        return view;

    }

    public void homeClicable() {
        ageCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAgeCalculator = new Intent(getActivity(), TaskActivity.class);
                intentAgeCalculator.putExtra("value", "age");
                startActivity(intentAgeCalculator);
                Log.i("ageCalculator", "onClick: ");

            }
        });
        birthdayWishes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBirthdayWishes = new Intent(getActivity(), TaskActivity.class);
                intentBirthdayWishes.putExtra("value", "wish");
                startActivity(intentBirthdayWishes);
                Log.i("birthdaywishes", "?????????: ");
            }
        });

    }


}



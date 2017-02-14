package com.example.birthdaytime.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.birthdaytime.R;

/**
 * Created by admin on 2/10/2017.
 */

public class birthdayWishesFragment extends android.support.v4.app.Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_birthday_wishes, null);
        Log.i("what", "?????????????? ");
        return view;

    }
}

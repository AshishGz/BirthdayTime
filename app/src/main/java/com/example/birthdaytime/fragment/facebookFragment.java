package com.example.birthdaytime.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.birthdaytime.R;

/**
 * Created by admin on 2/6/2017.
 */

public class facebookFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.display_birthday_layout, null);
        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText("Facebook Friend");
        return view;

    }
}

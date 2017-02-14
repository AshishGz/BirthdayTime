package com.example.birthdaytime.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.birthdaytime.DatabaseHelper;
import com.example.birthdaytime.R;
import com.example.birthdaytime.add_birthday;
import com.example.birthdaytime.birthdayInfo;

import java.util.ArrayList;
import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by admin on 2/2/2017.
 */

public class AddBirthdayFragment extends Fragment {


    LinearLayout displayBirtday;
    DatabaseHelper databaseHelper;
    ImageView addFromComtact;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.display, null);

        databaseHelper = new DatabaseHelper(getActivity());
        displayBirtday = (LinearLayout) view.findViewById(R.id.displayBirthday);

        displayBirthdayList();
        return view;

    }


    public void displayBirthdayList() {

        displayBirtday.removeAllViews();
        ArrayList<birthdayInfo> list = databaseHelper.getBirthdayList();
        // Log.i("size:", "size:" + list.size());
        for (int i = 0; i < list.size(); i++) {
            // TextView textView = new TextView(getActivity());
            final birthdayInfo info = list.get(i);
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.display_birthday_layout, null);
            TextView name = (TextView) view.findViewById(R.id.name);
            TextView dob = (TextView) view.findViewById(R.id.dob);
            // Log.i("size:", "size inside:" + info.getName());
            name.setText(info.getName());
            dob.setText(info.getBirthDay() + "/ " + info.getBirthMonth() + "/ " + info.getBirthYear() + " ,"
                    + findAge(info.getBirthYear(), info.getBirthMonth(), info.getBirthDay()));
            ImageView edit = (ImageView) view.findViewById(R.id.edit);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), add_birthday.class);
                    intent.putExtra("id", Integer.parseInt(info.getId()));
                    startActivity(intent);
                }
            });
            ImageView delect = (ImageView) view.findViewById(R.id.delect);
            delect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialouge(info.getId());
                }
            });
            addFromComtact = (ImageView) view.findViewById(R.id.addFromContact);
            addFromComtact.setVisibility(View.GONE);
            displayBirtday.addView(view);
        }

    }

    public void alertDialouge(final String id) {
        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("Won't be able to recover this file!")
                .setCancelText("No,cancel plz!")
                .setConfirmText("Yes,delete it!")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        // reuse previous dialog instance, keep widget user state, reset them if you need
                        sDialog.setTitleText("Cancelled!")
                                .setContentText("Your Birthday is safe :)")
                                .setConfirmText("OK")
                                .showCancelButton(false)
                                .setCancelClickListener(null)
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.ERROR_TYPE);

                        // or you can new a SweetAlertDialog to show
                               /* sDialog.dismiss();
                                new SweetAlertDialog(SampleActivity.this, SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Cancelled!")
                                        .setContentText("Your imaginary file is safe :)")
                                        .setConfirmText("OK")
                                        .show();*/
                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.setTitleText("Deleted!")
                                .setContentText("Your BIrthday has been deleted!")
                                .setConfirmText("OK")
                                .showCancelButton(false)
                                .setCancelClickListener(null)
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        databaseHelper.delectBirthday(id);
                        displayBirthdayList();
                    }
                })
                .show();
    }

    public String findAge(String giveYear, String givenMonth, String givenDay) {
        Calendar calDOB = Calendar.getInstance();
        calDOB.set(Integer.parseInt(giveYear), Integer.parseInt(givenMonth), Integer.parseInt(givenDay));
//setup calNow as today.
        Calendar calNow = Calendar.getInstance();
        calNow.setTime(new java.util.Date());
//calculate age in years.
        int ageYr = (calNow.get(Calendar.YEAR) - calDOB.get(Calendar.YEAR));
// calculate additional age in months, possibly adjust years.
        int ageMo = (calNow.get(Calendar.MONTH) - calDOB.get(Calendar.MONTH));
        if (ageMo < 0) {
//adjust years by subtracting one
            ageYr--;
        }
        return ageYr + "";
    }


    @Override
    public void onResume() {
        displayBirthdayList();
        super.onResume();
    }
}




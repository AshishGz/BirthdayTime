package com.example.birthdaytime.fragment;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.birthdaytime.DatabaseHelper;
import com.example.birthdaytime.R;
import com.example.birthdaytime.add_birthday;
import com.example.birthdaytime.getterSetter.contactInfo;

import java.util.ArrayList;

/**
 * Created by admin on 2/3/2017.
 */

public class contactFragment extends Fragment {


    ArrayList<contactInfo> list = new ArrayList<contactInfo>();
    LinearLayout mainLayout;
    ImageView edit, delect;
    ToggleButton toggleButton;
    DatabaseHelper databaseHelper;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setContact();
        View viewMain = inflater.inflate(R.layout.display_contact_layouto, null);
        LinearLayout containerView = (LinearLayout) viewMain.findViewById(R.id.contaner);
        databaseHelper = new DatabaseHelper(getActivity());


        for (int i = 0; i < list.size(); i++) {
            View view = inflater.inflate(R.layout.display_birthday_layout, null);
            final contactInfo info = list.get(i);

            TextView name = (TextView) view.findViewById(R.id.name);
            TextView number = (TextView) view.findViewById(R.id.dob);
            toggleButton = (ToggleButton) view.findViewById(R.id.toggle);

            edit = (ImageView) view.findViewById(R.id.edit);
            delect = (ImageView) view.findViewById(R.id.delect);
            edit.setVisibility(View.GONE);
            delect.setVisibility(View.GONE);
            toggleButton.setVisibility(View.GONE);
            name.setText(info.getContactName());
            number.setText(info.getContactPhone());
            view.findViewById(R.id.addFromContact).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), add_birthday.class);

                    intent.putExtra("contactName", info.getContactName());
                    intent.putExtra("contactPhone", info.getContactPhone());
                    intent.putExtra("flag", "true");
                    startActivity(intent);


                }
            });
            containerView.addView(view);

        }
        return viewMain;
    }


    public ArrayList<contactInfo> setContact() {
        ContentValues cv = new ContentValues();
        ContentResolver cr = getActivity().getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
                null, null, null);
        while (cursor.moveToNext()) {
            contactInfo cinfo = new contactInfo();

            String id = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.Contacts._ID));
            String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {

                Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
                while (pCur.moveToNext()) {
                    String phone = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    cinfo.setContactPhone(phone);


                }
                pCur.close();


                cinfo.setContactName(contactName);
                list.add(cinfo);
            }


        }
        return list;


    }
}
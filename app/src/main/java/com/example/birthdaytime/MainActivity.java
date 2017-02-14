package com.example.birthdaytime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.birthdaytime.fragment.AddBirthdayFragment;
import com.example.birthdaytime.fragment.contactFragment;
import com.example.birthdaytime.fragment.facebookFragment;
import com.example.birthdaytime.fragment.homeFragment;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    Button add;
    TextView titlebarHeading;
    private ResideMenu resideMenu;
    private MainActivity mContext;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemBirthday;
    private ResideMenuItem itemContact;
    private ResideMenuItem itemFacebook;
    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
            // Toast.makeText(mContext, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
            // Toast.makeText(mContext, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titlebarHeading = (TextView) findViewById(R.id.titlebarHeading);
        add = (Button) findViewById(R.id.add);
        mContext = this;
        setUpMenu();
        setAdd_birthday();
        if (savedInstanceState == null)
            changeFragment(new AddBirthdayFragment());
    }

    public void setAdd_birthday() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, com.example.birthdaytime.add_birthday.class));
            }
        });

    }

    private void setUpMenu() {

        // attach to current activity;
        resideMenu = new ResideMenu(this);
        // resideMenu.setUse3D(true);
        resideMenu.setBackground(R.color.themeColor);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.
        resideMenu.setScaleValue(0.6f);

        // create menu items;
        itemHome = new ResideMenuItem(this, R.drawable.ic_home, "Home");
        itemBirthday = new ResideMenuItem(this, R.drawable.ic_display, "Birthday");
        itemContact = new ResideMenuItem(this, R.drawable.ic_contact, "Contact");
        itemFacebook = new ResideMenuItem(this, R.drawable.ic_facebook, "Facebook Friends");

        itemHome.setOnClickListener(this);
        itemBirthday.setOnClickListener(this);
        itemContact.setOnClickListener(this);
        itemFacebook.setOnClickListener(this);

        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemBirthday, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemContact, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemFacebook, ResideMenu.DIRECTION_LEFT);

        // You can disable a direction by setting ->
        // resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        // findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener() {
        //   @Override
        // public void onClick(View view) {
        //   resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
        //}
        //});
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {

        if (view == itemHome) {
            changeFragment(new homeFragment());
            titlebarHeading.setText("Birthday Time");

        } else if (view == itemBirthday) {
            changeFragment(new AddBirthdayFragment());
            titlebarHeading.setText("Birthday");
        } else if (view == itemContact) {
            changeFragment(new contactFragment());
            titlebarHeading.setText("Contact");
        } else if (view == itemFacebook) {
            changeFragment(new facebookFragment());
            titlebarHeading.setText("Facebook Friends");
        }

        resideMenu.closeMenu();
    }

    private void changeFragment(Fragment targetFragment) {
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    // What good method is to access resideMenu？
    public ResideMenu getResideMenu() {
        return resideMenu;
    }
}


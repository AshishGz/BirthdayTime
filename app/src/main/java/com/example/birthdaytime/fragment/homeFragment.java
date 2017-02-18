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

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.birthdaytime.R;
import com.example.birthdaytime.TaskActivity;

import java.util.HashMap;

/**
 * Created by admin on 2/9/2017.
 */

public class homeFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    LinearLayout ageCalculator, birthdayWishes, gift, resturent;
    private SliderLayout mDemoSlider;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_layout, null);
        mDemoSlider = (SliderLayout) view.findViewById(R.id.slider);
        ageCalculator = (LinearLayout) view.findViewById(R.id.ageCalculator);

        birthdayWishes = (LinearLayout) view.findViewById(R.id.BirthdayWishes);
        resturent = (LinearLayout) view.findViewById(R.id.ageCalculator);
        gift = (LinearLayout) view.findViewById(R.id.ageCalculator);
        homeClicable();
        slideImage();


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

    public void slideImage() {
        HashMap<String, String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");


        // HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        //file_maps.put("Birth Time Gallery", R.drawable.ic_launcher);
        //file_maps.put("Birth  ", R.drawable.ic_birthday);
        //file_maps.put("Time", R.drawable.ic_contact_home);
        //file_maps.put("Gallery", R.drawable.profle);

        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);

}


    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}




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
        url_maps.put("Samjhana", "https://scontent-sit4-1.xx.fbcdn.net/v/t1.0-9/16708678_728754423954905_239252669295343550_n.jpg?oh=c25561ca55e86b04694f5e9862c15c84&oe=593181AD");
        url_maps.put("life", "https://fb-s-d-a.akamaihd.net/h-ak-xpt1/v/t1.0-0/p206x206/14470482_652384044925277_7478711624906405562_n.jpg?oh=cd57b14fa3195a6fdecd4fc6fa171789&oe=594B5876&__gda__=1496177514_879d77d51d8efb2adc3761f2f80a28cf");
        url_maps.put("Ashish", "https://scontent-sit4-1.xx.fbcdn.net/v/t1.0-9/1484115_768292829873413_6323326440013707268_n.jpg?oh=03ba91ed5bfda6a63f37dae7ebbd341f&oe=5946C9FA");
        url_maps.put("Cute", "https://scontent-sit4-1.xx.fbcdn.net/v/t31.0-8/s960x960/16601616_726932780803736_7216687716651756536_o.jpg?oh=f91df369cfd32e0a22da233f498c5e61&oe=592FAD50");


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




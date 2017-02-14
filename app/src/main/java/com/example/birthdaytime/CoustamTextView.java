package com.example.birthdaytime;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by admin on 2/2/2017.
 */

public class CoustamTextView extends TextView {
    Context context;

    public CoustamTextView(Context context) {
        super(context);
        this.context = context;
        setCoustamTypeFace();
    }

    public CoustamTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setCoustamTypeFace();
    }

    public void setCoustamTypeFace() {
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "fontawesome-webfont.ttf");
        this.setTypeface(tf);
    }
}

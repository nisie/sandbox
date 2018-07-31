package com.nisie.sandbox;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView testImage;
    TextView info;
    TextView infoImg;

    View tag;
    FrameLayout imageContainer;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testImage = findViewById(R.id.test_image);
        infoImg = findViewById(R.id.info_image);
        info = findViewById(R.id.info);
        imageContainer = findViewById(R.id.image_container);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        infoImg.setText("Width : " + width + " Height : " + height);

        ConstraintLayout.LayoutParams parms = new ConstraintLayout.LayoutParams(width, width);
        imageContainer.setLayoutParams(parms);
        imageContainer.requestLayout();

        testImage.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        info.setText("Touch coordinates DOWN: " +
                                String.valueOf(Math.round(event.getX()) + " x " + String.valueOf
                                        (Math.round(event.getY()))));
                        break;
                    case MotionEvent.ACTION_UP:
                        v.performClick();
                        info.setText("Touch coordinates UP: " +
                                String.valueOf(Math.round(event.getX()) + " x " + String.valueOf
                                        (Math.round(event.getY()))));

                        tag = new View(MainActivity.this);
                        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                                100,
                                50
                        );
                        params.leftMargin = Math.round(event.getX());
                        params.topMargin = Math.round(event.getY());

                        tag.setBackgroundColor(getResources().getColor(R.color.black));
                        imageContainer.addView(tag, params);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });


    }
}

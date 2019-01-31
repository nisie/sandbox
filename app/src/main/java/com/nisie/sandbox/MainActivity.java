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
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int ARROW_WIDTH = 30;
    private static final int ARROW_HEIGHT = 30;

    private static final int TEXT_WIDTH = 120;
    private static final int TEXT_HEIGHT = 50;
    ImageView testImage;
    TextView info;
    TextView infoImg;

    View tag;
    TextView tagText;

    FrameLayout imageContainer;

    private int screenWidth;

    float dX, dY;
    int index = 0;
    int indexText = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testImage = findViewById(R.id.test_image);
        infoImg = findViewById(R.id.info_image);
        info = findViewById(R.id.info);
        imageContainer = findViewById(R.id.image_container);

//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        int screenHeight = displayMetrics.heightPixels;
//        screenWidth = displayMetrics.widthPixels;
//        infoImg.setText("Width : " + screenWidth + " Height : " + screenHeight);
//
//        ConstraintLayout.LayoutParams parms = new ConstraintLayout.LayoutParams(screenWidth, screenWidth);
//        imageContainer.setLayoutParams(parms);
//        imageContainer.requestLayout();
//
//        testImage.setOnTouchListener(new View.OnTouchListener() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        info.setText("Touch coordinates DOWN: " +
//                                String.valueOf(Math.round(event.getX()) + " x " + String.valueOf
//                                        (Math.round(event.getY()))));
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        v.performClick();
//                        info.setText("Touch coordinates UP: " +
//                                String.valueOf(Math.round(event.getX()) + " x " + String.valueOf
//                                        (Math.round(event.getY()))));
//
//                        tag = new View(MainActivity.this);
//                        tagText = new TextView(MainActivity.this);
//                        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
//                                ARROW_WIDTH,
//                                ARROW_HEIGHT
//                        );
//
//                        int xTagPos = getXPosition(event.getX());
//                        int yTagPos = getYPosition(event.getY());
//                        params.leftMargin = xTagPos;
//                        params.topMargin = yTagPos;
//                        setArrow(yTagPos, tag);
//                        tag.setOnTouchListener(onTagTouch());
//
//                        ConstraintLayout.LayoutParams textParams = new ConstraintLayout.LayoutParams(
//                                TEXT_WIDTH,
//                                TEXT_HEIGHT
//                        );
//                        textParams.leftMargin = getXTextPosition(xTagPos);
//                        textParams.topMargin = getYTextPosition(yTagPos);
//                        tagText.setBackgroundColor(getResources().getColor(R.color.black));
//                        tagText.setTextColor(getResources().getColor(R.color.white));
//                        tagText.setText("Test Tag");
//
//                        tagText.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                int indexDelete = v.getId();
//                                int indexDeleteTag = indexDelete - 1;
//                                imageContainer.removeView(findViewById(indexDelete));
//                                imageContainer.removeView(findViewById(indexDeleteTag));
//                            }
//                        });
//                        tag.setId(index);
//                        tagText.setId(index + 1);
//                        imageContainer.addView(tag, params);
//                        imageContainer.addView(tagText, textParams);
//                        index += 2;
//
//                        break;
//                    default:
//                        break;
//                }
//                return true;
//            }
//        });


    }

    private void setArrow(int yTagPos, View tag) {
        tag.setBackground(getResources().getDrawable(R.drawable.ic_play_arrow_black_24dp));
        if (yTagPos < (screenWidth / 2))
            tag.setRotation(270);
        else {
            tag.setRotation(90);
        }
    }

    private int getYTextPosition(int yTagPos) {
//        if (yTagPos > screenWidth - TEXT_HEIGHT)
//            return Math.round(screenWidth - TEXT_HEIGHT);
//        else return yTagPos;

        if (yTagPos < screenWidth / 2) {
            return yTagPos + ARROW_HEIGHT;
        } else {
            return yTagPos - TEXT_HEIGHT;
        }
    }

    private int getXTextPosition(int xTagPos) {
//        if (xTagPos < (screenWidth / 2))
//            return xTagPos + ARROW_WIDTH;
//        else
//            return xTagPos - TEXT_WIDTH;

        if (xTagPos > screenWidth - TEXT_WIDTH) {
            return screenWidth - TEXT_WIDTH;
        } else {
            return xTagPos;
        }
    }

    private View.OnTouchListener onTagTouch() {
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:

                        dX = v.getX() - event.getRawX();
                        dY = v.getY() - event.getRawY();
                        break;

                    case MotionEvent.ACTION_MOVE:

                        v.animate()
                                .x(event.getRawX() + dX)
                                .y(event.getRawY() + dY)
                                .setDuration(0)
                                .start();
                        break;
                    default:
                        return false;
                }
                return true;
            }
        };
    }


    private int getYPosition(float y) {
        if (y > screenWidth - ARROW_HEIGHT)
            return Math.round(screenWidth - ARROW_HEIGHT);
        else if (y > screenWidth / 2)
            return Math.round(y - ARROW_HEIGHT);
        else
            return Math.round(y);

    }

    private int getXPosition(float x) {
        if (x > screenWidth - ARROW_WIDTH)
            return Math.round(screenWidth - ARROW_WIDTH);
        else if (x < ARROW_WIDTH) {
            return Math.round(x);
        } else
            return Math.round(x - (ARROW_WIDTH / 2));
    }
}

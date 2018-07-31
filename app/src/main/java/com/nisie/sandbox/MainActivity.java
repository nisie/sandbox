package com.nisie.sandbox;

import android.annotation.SuppressLint;
import android.graphics.Point;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView testImage;
    TextView info;
    View tag;
    FrameLayout imageContainer;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testImage = findViewById(R.id.test_image);
        info = findViewById(R.id.info);
        imageContainer = findViewById(R.id.image_container);

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

//        testImage.setOnDragListener(new View.OnDragListener() {
//            @Override
//            public boolean onDrag(View v, DragEvent event) {
//                switch (event.getAction()) {
//                    case DragEvent.ACTION_DRAG_STARTED:
//                        info.setText("Touch coordinates DOWN: " +
//                                String.valueOf(event.getX()) + "x" + String.valueOf(event.getY()));
//                        break;
//                    case DragEvent.ACTION_DRAG_LOCATION:
//                        Point touchPosition = getTouchPositionFromDragEvent(v, event);
//                        //do something with the position (a scroll i.e);
//                        break;
//                    default:
//                }
//                return true;
//            }
//        });
    }
}

package com.example.shadesofred;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splash_screen extends AppCompatActivity {
    ImageView ivTop , ivBottom , ivMiddle;
    TextView textView;
    CharSequence charSequence;
    int index;
    long delay = 150;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //to remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //to hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_screen);

        ivTop = findViewById(R.id.top);
        ivBottom = findViewById(R.id.bottom);
        ivMiddle = findViewById(R.id.centerImage);
        textView = findViewById(R.id.text_view);

        //Initialise Top animation
        Animation animation1 = AnimationUtils.loadAnimation(this,R.anim.top_wave);
        //start top animation
        ivTop.setAnimation(animation1);

        //Initialise object animator
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
                ivMiddle,
                PropertyValuesHolder.ofFloat("scaleX",1.2f),
                PropertyValuesHolder.ofFloat("scaleY",1.2f)
        );

        //set duration
        objectAnimator.setDuration(500);
        //set repeat count
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        //set repeat mode
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        //start animator
        objectAnimator.start();

        //set animate text
        animatText("Shades of Red");

        //Initialise bottom animation
        Animation animation2 = AnimationUtils.loadAnimation(this,R.anim.bottom_wave);
        //start bottom animation
        ivBottom.setAnimation(animation2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(splash_screen.this , login.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        },3000);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //set text when runnable is run
            textView.setText(charSequence.subSequence(0,index++));
            if(index <= charSequence.length()){
                handler.postDelayed(runnable,delay);
            }
        }
    };

    //create animated text method
    public void animatText(CharSequence cs){
        //set text
        charSequence = cs;
        //clear index
        index = 0;
        //clear text
        textView.setText(" ");
        //remove callbacks
        handler.removeCallbacks(runnable);
        //run handler
        handler.postDelayed(runnable,delay);
    }
}


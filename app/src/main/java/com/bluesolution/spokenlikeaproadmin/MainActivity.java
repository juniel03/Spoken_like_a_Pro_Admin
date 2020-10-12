package com.bluesolution.spokenlikeaproadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Guideline guidelinemid;
    Guideline guidelinelt;
    Guideline guidelinelb;
    Guideline guidelinert;
    Guideline guidelinerb;
    ConstraintLayout.LayoutParams paramsmid;
    ConstraintLayout.LayoutParams paramslt;
    ConstraintLayout.LayoutParams paramslb;
    ConstraintLayout.LayoutParams paramsrt;
    ConstraintLayout.LayoutParams paramsrb ;
    Button btnChecker;
    Button btnCode;
    ImageView ivPrem;
    ImageView ivReg;
    long prev=0;
    long current = 0;
    long dif =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideSystemUI.fullscreen(this);

        guidelinemid =  (Guideline) findViewById(R.id.guidelinemid);
        guidelinelt =   (Guideline) findViewById(R.id.guidelinelt);
        guidelinelb =   (Guideline) findViewById(R.id.guidelinelb);
        guidelinert =   (Guideline) findViewById(R.id.guidelinert);
        guidelinerb =   (Guideline) findViewById(R.id.guidelinerb);

        paramsmid = (ConstraintLayout.LayoutParams) guidelinemid.getLayoutParams();
        paramslt = (ConstraintLayout.LayoutParams) guidelinelt.getLayoutParams();
        paramslb = (ConstraintLayout.LayoutParams) guidelinelb.getLayoutParams();
        paramsrt = (ConstraintLayout.LayoutParams) guidelinert.getLayoutParams();
        paramsrb = (ConstraintLayout.LayoutParams) guidelinerb.getLayoutParams();

        btnChecker = findViewById(R.id.btnChecker);
        btnCode = findViewById(R.id.btnCode);

        ivPrem  = findViewById(R.id.ivPrem);
        ivReg   = findViewById(R.id.ivRegular);

        ivPrem.setElevation(10);
        ivReg.setElevation(10);

        btnCode.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        Log.d("TAG", "up");
                        current = System.currentTimeMillis() / 1000;
                        dif = current - prev;
                        if (dif == 0) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent gallery = new Intent(MainActivity.this,PremiumCodes.class);
                                    startActivity(gallery);
                                    btnChecker.setEnabled(false);
                                }
                            }, 500);
                        }else {
                            paramsmid.guidePercent = 0.50f;
                            paramslt.guidePercent = 0.60f;
                            paramslb.guidePercent = 0.80f;
                            paramsrt.guidePercent = 0.60f;
                            paramsrb.guidePercent = 0.80f;
                            guidelinemid.setLayoutParams(paramsmid);
                            guidelinelt.setLayoutParams(paramslt);
                            guidelinelb.setLayoutParams(paramslb);
                            guidelinert.setLayoutParams(paramsrt);
                            guidelinerb.setLayoutParams(paramsrb);
                            view.setBackgroundResource(R.drawable.first_btn_background);
                            btnCode.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.purple));
                            ivReg.setImageResource(R.drawable.not_checked);
                            btnChecker.setEnabled(true);
                            btnCode.setTextSize(24);
                            btnChecker.setTextSize(24);
                        }
                        break;
                    case MotionEvent.ACTION_DOWN:
                        Log.d("tag", "down");
                        prev = System.currentTimeMillis() / 1000;
                        paramsmid.guidePercent = 0.40f;
                        paramslt.guidePercent = 0.60f;
                        paramslb.guidePercent = 0.80f;
                        paramsrt.guidePercent = 0.56f;
                        paramsrb.guidePercent = 0.84f;
                        guidelinemid.setLayoutParams(paramsmid);
                        guidelinelt.setLayoutParams(paramslt);
                        guidelinelb.setLayoutParams(paramslb);
                        guidelinert.setLayoutParams(paramsrt);
                        guidelinerb.setLayoutParams(paramsrb);
                        view.setBackgroundResource(R.drawable.first_btn_clicked);
                        btnCode.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                        ivReg.setImageResource(R.drawable.checked);
                        btnChecker.setEnabled(false);
                        btnCode.setTextSize(30);
                        btnChecker.setTextSize(18);
                        break;
                }
                return true;
            }
        });

        btnChecker.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        Log.d("TAG", "up");
                        current = System.currentTimeMillis() / 1000;
                        dif = current - prev;
                        if (dif == 0) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent gallery = new Intent(MainActivity.this,CheckerRecy.class);
                                    startActivity(gallery);
                                    btnCode.setEnabled(false);
                                }
                            }, 500);
                        }else {
                            paramsmid.guidePercent = 0.50f;
                            paramslt.guidePercent = 0.60f;
                            paramslb.guidePercent = 0.80f;
                            paramsrt.guidePercent = 0.60f;
                            paramsrb.guidePercent = 0.80f;
                            guidelinemid.setLayoutParams(paramsmid);
                            guidelinelt.setLayoutParams(paramslt);
                            guidelinelb.setLayoutParams(paramslb);
                            guidelinert.setLayoutParams(paramsrt);
                            guidelinerb.setLayoutParams(paramsrb);
                            view.setBackgroundResource(R.drawable.first_btn_background);
                            btnChecker.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.purple));
                            ivPrem.setImageResource(R.drawable.not_checked);
                            btnCode.setEnabled(true);
                            btnCode.setTextSize(24);
                            btnChecker.setTextSize(24);
                        }
                        break;
                    case MotionEvent.ACTION_DOWN:
                        Log.d("tag", "down");
                        prev = System.currentTimeMillis() / 1000;
                        paramsmid.guidePercent = 0.60f;
                        paramslt.guidePercent = 0.56f;
                        paramslb.guidePercent = 0.84f;
                        paramsrt.guidePercent = 0.60f;
                        paramsrb.guidePercent = 0.80f;
                        guidelinemid.setLayoutParams(paramsmid);
                        guidelinelt.setLayoutParams(paramslt);
                        guidelinelb.setLayoutParams(paramslb);
                        guidelinert.setLayoutParams(paramsrt);
                        guidelinerb.setLayoutParams(paramsrb);
                        view.setBackgroundResource(R.drawable.first_btn_clicked);
                        btnChecker.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                        ivPrem.setImageResource(R.drawable.checked);
                        btnCode.setEnabled(false);
                        btnChecker.setTextSize(30);
                        btnCode.setTextSize(18);
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI.fullscreen(this);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("tag", "ONRESUME");
        paramsmid.guidePercent = 0.50f;
        paramslt.guidePercent = 0.60f;
        paramslb.guidePercent = 0.80f;
        paramsrt.guidePercent = 0.60f;
        paramsrb.guidePercent = 0.80f;
        guidelinemid.setLayoutParams(paramsmid);
        guidelinelt.setLayoutParams(paramslt);
        guidelinelb.setLayoutParams(paramslb);
        guidelinert.setLayoutParams(paramsrt);
        guidelinerb.setLayoutParams(paramsrb);
        btnChecker.setEnabled(true);
        btnCode.setEnabled(true);
        ivPrem.setImageResource(R.drawable.not_checked);
        ivReg.setImageResource(R.drawable.not_checked);
        btnChecker.setBackgroundResource(R.drawable.first_btn_background);
        btnCode.setBackgroundResource(R.drawable.first_btn_background);
        btnChecker.setTextColor(getResources().getColor(R.color.purple));
        btnCode.setTextColor(getResources().getColor(R.color.purple));
        btnCode.setTextSize(24);
        btnChecker.setTextSize(24);
    }

}
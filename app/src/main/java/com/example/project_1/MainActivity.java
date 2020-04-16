package com.example.project_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.project_1.Model.ShapeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<ShapeModel> shapeModelList;
    String keyColor;
    ShapeModel shapeModel;
    TextView tv_dialog;
    Button bt_pink, bt_blue;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bt_blue = findViewById(R.id.bt_blue);
        bt_pink = findViewById(R.id.bt_pink);
        init();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void init() {
        RandomColor();
        bt_blue.setOnTouchListener(new View.OnTouchListener() {
            boolean result;
            long time = 0L;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final Dialog dialog = getDialog();
                keyColor = shapeModelList.get(0).getKey_Color();
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    time = event.getDownTime();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    time = event.getEventTime() - time;
                    if (time > 1999 && keyColor.equals("LB")) {
                        shapeModelList.remove(0);
                        result = true;
                    } else {
                        tv_dialog.setText("โปรดกดค้างไว้ 2 วินาที");
                        result = false;
                        if (!dialog.isShowing()) {
                            dialog.show();
                        }
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                dialog.dismiss();
                            }
                        }, 1500);
                    }
                }
                return result;
            }
        });

        bt_pink.setOnTouchListener(new View.OnTouchListener() {
            boolean result;
            long time = 0L;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final Dialog dialog = getDialog();
                keyColor = shapeModelList.get(0).getKey_Color();
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    time = event.getDownTime();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    time = event.getEventTime() - time;
                    if (time > 1999 && keyColor.equals("PI")) {
                        shapeModelList.remove(0);
                        result = true;
                    } else {
                        tv_dialog.setText("Please press hold 2 second time.");
                        result = false;
                        if (!dialog.isShowing()) {
                            dialog.show();
                        }
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                dialog.dismiss();
                            }
                        }, 1500);
                    }
                }
                return result;
            }
        });
    }

    private Dialog getDialog() {
        final Dialog dialog = new Dialog(MainActivity.this, android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen);
        final View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_alert, null);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(dialogView);
        if (dialog.getWindow() != null) {
            dialog.setCancelable(false);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }

        tv_dialog = dialog.findViewById(R.id.tv_dialog);
        return dialog;
    }

    boolean isDo;

    private List RandomColor() {
        shapeModelList = new ArrayList<>();
        if (isDo != true) {
            int pink = R.color.pink;
            int light_blue = R.color.light_blue;
            List colorSet = new ArrayList();
            colorSet.add(pink);
            colorSet.add(light_blue);

            int sizeBlock = 5;
            for (int i = 0; i < sizeBlock; i++) {
                int randomAndroidColor = (int) colorSet.get(new Random().nextInt(colorSet.size()));
                shapeModel = new ShapeModel();
                shapeModel.setColor(randomAndroidColor);
                if (randomAndroidColor == pink) {
                    shapeModel.setKey_Color("PI");
                } else {
                    shapeModel.setKey_Color("LB");
                }
                if (i == sizeBlock - 1) {
                    shapeModel.setColor(R.color.purple);
                    shapeModel.setKey_Color("PP");
                }
                shapeModelList.add(shapeModel);
            }
            isDo = true;
        } else {
            isDo = false;
        }
        return shapeModelList;
    }
}
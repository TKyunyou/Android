package com.example.ch04_massager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txv = findViewById(R.id.txv);
        txv.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            vb.vibrate(5000);
        }
        else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
            vb.cancel();
        }
        else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE){
            vb.vibrate(5000);
        }
        return true;
    }
}
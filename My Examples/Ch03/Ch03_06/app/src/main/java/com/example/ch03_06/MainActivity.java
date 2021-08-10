package com.example.ch03_06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Change(View v){
        Random x = new Random();
        int txtsize = x.nextInt(100);
        if (txtsize == 0){
            Change(v);
        }
        TextView txv = findViewById(R.id.txv);
        txv.setTextSize(txtsize);
    }
}
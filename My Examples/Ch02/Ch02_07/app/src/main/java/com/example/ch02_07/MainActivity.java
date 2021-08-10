package com.example.ch02_07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void morning(View v) {
        TextView txv = (TextView)findViewById(R.id.txv);
        txv.setText("早安！");
    }
    public void noon(View v) {
        TextView txv = (TextView)findViewById(R.id.txv);
        txv.setText("午安！");
    }
    public void night(View v) {
        TextView txv = (TextView)findViewById(R.id.txv);
        txv.setText("晚安！");
    }
}
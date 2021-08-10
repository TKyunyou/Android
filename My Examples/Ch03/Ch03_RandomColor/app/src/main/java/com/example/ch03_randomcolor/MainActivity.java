package com.example.ch03_randomcolor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView txvR,txvG,txvB;
    Button btn;
    View colorBlock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取3个TextView的对象及画面最下方的LinearLayout
        txvR = findViewById(R.id.txvR);
        txvG = findViewById(R.id.txvG);
        txvB = findViewById(R.id.txvB);
        btn = findViewById(R.id.button);
        colorBlock = findViewById(R.id.colorBlock);
    }

    public void changeColor(View v){
        Random x=new Random();
        int red=x.nextInt(256);
        txvR.setText("红：" + red);
        txvR.setTextColor(Color.rgb(red,0,0));

        int green=x.nextInt(256);
        txvG.setText("绿：" + red);
        txvG.setTextColor(Color.rgb(0, green,0));

        int blue=x.nextInt(256);
        txvB.setText("蓝：" + red);
        txvB.setTextColor(Color.rgb(0,0,blue));

        btn.setTextColor(Color.rgb(red, green, blue));
        colorBlock.setBackgroundColor(Color.rgb(red, green, blue));
    }
}
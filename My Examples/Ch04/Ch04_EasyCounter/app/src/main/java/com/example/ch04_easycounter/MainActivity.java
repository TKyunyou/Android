package com.example.ch04_easycounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ch04_easycounter.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
//        , View.OnLongClickListener
{

    TextView txv;
    Button btn,btn2,btn3;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txv = findViewById(R.id.textView);
        btn = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
//        txv.setOnClickListener(this);
        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
//        txv.setOnLongClickListener(this);
//        btn.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button){
            txv.setText(String.valueOf(++counter));
        }
        else if (view.getId() == R.id.button2){
            txv.setText(String.valueOf(--counter));
        }
        else if (view.getId() == R.id.button3){
            finish();
        }
    }

//    @Override
//    public boolean onLongClick(View view) {
//        if (view.getId() == R.id.textView){
//            counter = 0;
//            txv.setText("0");
//        }
//        else if (view.getId() == R.id.button){
//            counter += 2;
//            txv.setText(String.valueOf(counter));
//        }
//        return true;
//    }
}
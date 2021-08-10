package com.example.ch02_button;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sayHello(View v) {
        //获取代表布局中EditText和TextView的对象
        EditText name = findViewById(R.id.name);
        TextView txv = findViewById(R.id.txv);

        //trim()方法是Java String类内建的方法，它会删除String对象前后的空格符，再将字符串返回
        String str = name.getText().toString().trim();

        if (str.length() == 0)
        {
            txv.setText("请输入大名！");
        }
        else
        {
            txv.setText(str + "，您好！");
        }
    }

}
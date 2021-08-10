package com.example.ch03_linearlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //声明UI组件的变量
    EditText sname,fname,phone;
    TextView txv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化变量
        sname = findViewById(R.id.surname);
        fname = findViewById(R.id.firstname);
        phone = findViewById(R.id.phone);
        txv =findViewById(R.id.txv);
    }
    public void onclick(View v){
        txv.setText(sname.getText().toString() +
                fname.getText().toString() +
                "的电话是" +
                phone.getText());
    }
}
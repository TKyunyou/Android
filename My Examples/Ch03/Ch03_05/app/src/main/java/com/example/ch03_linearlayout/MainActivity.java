package com.example.ch03_linearlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
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
        String surname = sname.getText().toString();
        String firstname = fname.getText().toString();
        String phonenumber = phone.getText().toString();
        if (surname.length() == 0){
            txv.setTextColor(Color.RED);
            txv.setText("请输入姓！");
        }
        else if (firstname.length() == 0){
            txv.setTextColor(Color.RED);
            txv.setText("请输入名！");
        }
        else if (phonenumber.length() == 0){
            txv.setTextColor(Color.RED);
            txv.setText("请输入电话！");
        }
        else{
            txv.setTextColor(Color.BLACK);
            txv.setText(surname + firstname + "的电话是" + phonenumber);
        }
    }
}
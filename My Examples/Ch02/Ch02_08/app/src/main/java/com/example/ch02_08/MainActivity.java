package com.example.ch02_08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ShowNumber(View v) {
        TextView txv = findViewById(R.id.txv);
        EditText edittext = findViewById(R.id.edittext);
        String str = edittext.getText().toString();
        int length = str.length();
        txv.setText("" + length);//直接写length会出错，用空字符串加length即可避免
    }
}
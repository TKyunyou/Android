package com.example.ch05_buyticket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    原始版本
    public void show(View v){
        TextView txv = findViewById(R.id.txv);
        RadioGroup ticketType = findViewById(R.id.ticketType);

        switch(ticketType.getCheckedRadioButtonId()){
            case R.id.adult:
                txv.setText("买全票");
                break;
            case R.id.child:
                txv.setText("买半票");
                break;
            case R.id.senior:
                txv.setText("买敬老票");
                break;
        }
    }
     */

    //简化版本
    public void show(View v){
        TextView txv = findViewById(R.id.txv);
        RadioGroup ticketType = findViewById(R.id.ticketType);

        int id = ticketType.getCheckedRadioButtonId();
        RadioButton select = findViewById(id);
        txv.setText("买" + select.getText());
    }
}
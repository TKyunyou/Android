package tw.com.flag.ch06_energycalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener{
    // 字符串数组中各项运动的能量消耗率:“千卡/公斤/小时”
    double[] energyRate={3.1, 4.4, 13.2, 9.7, 5.1, 3.7};
    EditText weight,time;  // 体重和运动时间字段
    TextView total,txvRate;    // 显示能量消耗率, 计算结果的 TextView
    Spinner sports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weight= (EditText)findViewById(R.id.weight);
        time= (EditText)findViewById(R.id.timeSpan);
        total= (TextView)findViewById(R.id.total);
        txvRate= (TextView)findViewById(R.id.txvRate);
        sports=(Spinner) findViewById(R.id.sports);
        sports.setOnItemSelectedListener(this); // 注册监听器
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // 显示选取的运动项目, 其基本的能量消耗率
        txvRate.setText(String.valueOf(energyRate[position]));
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // 不会用到, 但仍需保留
    }

    public void calc(View v){
        // 获取用户输入的体重
        double w=Double.parseDouble(weight.getText().toString());
        // 获取用户输入的运动时间长度
        double t=Double.parseDouble(time.getText().toString());

        int selected=sports.getSelectedItemPosition(); // 获取当前选取项目的索引

        // 计算消耗能量=能量消耗率*体重*运动时间长度
        long consumedEnergy=Math.round(energyRate[selected]*w*t);

        total.setText(    // 显示计算结果
                String.format("消耗能量\n  %d千卡", consumedEnergy));
    }

}
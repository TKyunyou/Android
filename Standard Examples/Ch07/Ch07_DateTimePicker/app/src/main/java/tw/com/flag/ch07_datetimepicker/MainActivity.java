package tw.com.flag.ch07_datetimepicker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity
        implements View.OnClickListener,
        DatePickerDialog.OnDateSetListener,     // 实现监听日期对话框事件的接口
        TimePickerDialog.OnTimeSetListener {    // 实现监听时间对话框事件的接口

    Calendar c = Calendar.getInstance();  //创建日历对象
    TextView txDate;                 // 记录日期文字的组件
    TextView txTime;                 // 记录时间文字的组件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txDate = (TextView)findViewById(R.id.textView1);
        txTime = (TextView)findViewById(R.id.textView2);

        txDate.setOnClickListener(this); //设置单击日期文字时的监听对象
        txTime.setOnClickListener(this); //设置单击时间文字时的监听对象
    }

    @Override
    public void onClick(View v) {
        if(v == txDate) { // 单击的是日期文字
            //创建日期选择对话框, 并传入设置完成时的监听对象
            new DatePickerDialog(this, this,    // 由活动对象监听事件
                    c.get(Calendar.YEAR),   //由Calendar对象获取当前的公元年
                    c.get(Calendar.MONTH),  //获取当前月 (从 0 算起)
                    c.get(Calendar.DAY_OF_MONTH)) //获取当前日
                    .show();  //显示出来
        }
        else if(v == txTime) { // 单击的是时间文字
            //创建时间选择对话框, 并传入设置完成时的监听对象
            new TimePickerDialog(this, this, // 由活动对象监听事件
                    c.get(Calendar.HOUR_OF_DAY), //获取当前的时 (24小时制)
                    c.get(Calendar.MINUTE),      //获取当前的分
                    true)                        //使用24小时制
                    .show();   //显示出来
        }
    }

    @Override
    public void onDateSet(DatePicker v, int y, int m, int d) {
        txDate.setText("日期：" + y + "/" + (m+1) + "/" + d);  // 将选定日期显示在屏幕上
    }

    @Override
    public void onTimeSet(TimePicker v, int h, int m) {
        txTime.setText("时间：" + h + ":" + m);    // 将选定的时间显示在屏幕上
    }
}
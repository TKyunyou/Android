package tw.com.flag.ch06_arrayadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    Spinner drink, temp; // 显示饮品选项与温度选项的 Spinner
    TextView txv; // 显示订单内容的 TextView
    String[] tempSet1 = { "冰", "去冰", "温" }; // 三种温度
    String[] tempSet2 = { "冰", "去冰" }; // 两种温度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txv = (TextView) findViewById(R.id.order);
        temp = (Spinner) findViewById(R.id.temp); // 找出显示温度的 Spinner
        drink = (Spinner) findViewById(R.id.drink); // 找出显示饮品选项的 Spinner
        drink.setOnItemSelectedListener(this); // 设置监听选取事件
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String[] tempSet;
        if (position == 3) // 若选取柠檬汁
            tempSet = tempSet2; // 温度选项没有“温”
        else
            tempSet = tempSet1;
        ArrayAdapter<String> tempAd = // 根据温度选项建立 ArrayAdapter
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, // 选单位打开时的显示样式
                        tempSet); // 温度选项
        tempAd.setDropDownViewResource( // 设置下拉选单的选项样式
                android.R.layout.simple_spinner_dropdown_item);
        temp.setAdapter(tempAd); // 设置使用 Adapter 对象
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // 按钮的事件处理方法
    public void showOrder(View v) {
        // 将饮料名称及温度选择组成一个字符串
        String msg = drink.getSelectedItem() + ", " + // 获取饮料名称
                temp.getSelectedItem(); // 获取甜度选项

        // 将订单内容显示在文本框中
        txv.setText(msg);
    }

}

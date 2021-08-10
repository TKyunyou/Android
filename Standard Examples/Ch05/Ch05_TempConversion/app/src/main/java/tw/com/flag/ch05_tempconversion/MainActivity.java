package tw.com.flag.ch05_tempconversion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
        implements RadioGroup.OnCheckedChangeListener, TextWatcher {

    RadioGroup unit;
    EditText value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unit  = (RadioGroup)findViewById(R.id.unit);  // 获取“单位”单选钮群组
        unit.setOnCheckedChangeListener(this);        // 设置 this 为监听器

        value = (EditText)  findViewById(R.id.value); // 获取输入字段
        value.addTextChangedListener(this);           // 设置 this 为监听器
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        calc();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        calc();
    }
    protected void calc() {
        // 获取文本框
        TextView degF = (TextView) findViewById(R.id.degF);
        TextView degC = (TextView) findViewById(R.id.degC);

        double f, c;  // 存储温度值换算结果

        // 若选择输入华氏温度
        if(unit.getCheckedRadioButtonId()==R.id.unitF){
            f = Double.parseDouble(
                    value.getText().toString());
            c = (f-32)*5/9;  // 华氏 => 摄氏

        } else{   // 若选择输入摄氏温度
            c = Double.parseDouble(
                    value.getText().toString());
            f = c*9/5+32;    // 摄氏 => 华氏
        }

        degC.setText(String.format("%.1f",c)+
                // 从项目资源加载字符串
                getResources().getString(R.string.charC));
        degF.setText(String.format("%.1f",f)+
                getResources().getString(R.string.charF));
    }
}
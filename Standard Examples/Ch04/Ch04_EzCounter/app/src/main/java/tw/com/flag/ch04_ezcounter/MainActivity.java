package tw.com.flag.ch04_ezcounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener { // 实现 OnClickListener 接口
    TextView txv;     // 用来引用 textView1 组件的变量
    Button btn;          // 用来引用 button1 组件的变量
    int counter = 0;   // 用来存储计数的值, 初值为 0

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txv = (TextView) findViewById(R.id.textView); // 找出要引用的对象
        btn = (Button) findViewById(R.id.button); // 找出要引用的对象

        btn.setOnClickListener(this); // 登录监听对象, this 表示活动对象本身
    }

    @Override
    public void onClick(View v) { // 监听器接口中定义的 onClick 方法
        txv.setText(String.valueOf(++counter)); // 将计数值加 1, 然后转成字符串显示出来
    }
}

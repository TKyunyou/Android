package tw.com.flag.ch04_ezcounter;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity
        implements View.OnClickListener, View.OnLongClickListener { // 实现 2 个接口
    TextView txv;       // 用来引用 textView1 组件的变量
    Button btn;         // 用来引用 button1 组件的变量
    int counter = 0;     // 用来存储计数的值, 初值为 0

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txv = (TextView) findViewById(R.id.textView);   // 找出要引用的对象
        btn = (Button) findViewById(R.id.button);       // 找出要引用的对象

        btn.setOnClickListener(this);       // 登录监听对象, this 表示活动对象本身
        btn.setOnLongClickListener(this);   //  将活动对象登录为按钮的长按监听器
    }

    @Override
    public void onClick(View v) { // 实现监听器接口中定义的 onClick 方法
        txv.setText(String.valueOf(++counter)); // 将计数值加 1, 然后转成字符串显示出来
    }

    @Override
    public boolean onLongClick(View v) {
        counter = 0;
        txv.setText("0");
        return true;
    }
}

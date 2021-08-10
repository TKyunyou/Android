package tw.com.flag.ch07_dialogask;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
        implements DialogInterface.OnClickListener {

    TextView txv; // 记录默认的 TextView 组件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txv = (TextView) findViewById(R.id.answer); // 找出默认的 TextView 组件
        new AlertDialog.Builder(this) // 创建 Builder 对象
                .setMessage("你喜欢 Android 手机吗？") // 设置显示信息
                .setCancelable(false) // 禁用返回键关闭对话框
                .setIcon(android.R.drawable.ic_menu_edit) // 采用内建的图标
                .setTitle("Android 问卷调查") // 设置对话框的标题
                .setPositiveButton("喜欢", this)  // 加入否定按钮
                .setNegativeButton("讨厌", this)    // 加入肯定按钮
                .setNeutralButton("没意见", null) // 不监听按钮事件
                .show();// 显示对话框
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which == DialogInterface.BUTTON_POSITIVE) { // 如果按下肯定的“喜欢”
            txv.setText("你喜欢 Android 手机");
        }
        else if(which == DialogInterface.BUTTON_NEGATIVE) { // 如果按下否定的“讨厌”
            txv.setText("你讨厌 Android 手机");
        }
    }
}
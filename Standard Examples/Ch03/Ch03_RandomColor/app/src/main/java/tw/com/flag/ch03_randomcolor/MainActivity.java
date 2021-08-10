package tw.com.flag.ch03_randomcolor;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    TextView txvR,txvG,txvB;
    View colorBlock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取3个TextView的对象, 画面最下方的 LinearLayout
        txvR = (TextView) findViewById(R.id.txvR);
        txvG= (TextView) findViewById(R.id.txvG);
        txvB= (TextView) findViewById(R.id.txvB);
        colorBlock = findViewById(R.id.colorBlock);
    }

    public void changeColor(View v){
        // 获取随机数对象, 产生3个随机数值(rgb值)
        Random x=new Random();
        int red=x.nextInt(256);       // 取0～255之间的随机数
        txvR.setText("红："+ red);   // 显示随机数值
        txvR.setTextColor(Color.rgb(red, 0, 0));   // 将文字设为随机数颜(红)色值

        int green=x.nextInt(256);
        txvG.setText("绿："+ green);
        txvG.setTextColor(Color.rgb(0,green,0));// 将文字设为随机数颜(绿)色值

        int blue=x.nextInt(256);
        txvB.setText("蓝：   "+ blue);
        txvB.setTextColor(Color.rgb(0,0,blue));    // 将文字设为随机数颜(蓝)色值

        // 设置画面最下方的空白 LinearLayout 的背景颜色
        colorBlock.setBackgroundColor(Color.rgb(red, green, blue));
    }
}

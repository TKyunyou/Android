package tw.com.flag.ch09_fastdialer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        Intent it = new Intent();         //新建 Intent 对象
        it.setAction(Intent.ACTION_VIEW); //设置动作：显示数据
        it.setData(Uri.parse("tel:800")); //设置数据：用URI指定电话号码
        startActivity(it);                //启动适合 Intent 的 Activity
    }
}

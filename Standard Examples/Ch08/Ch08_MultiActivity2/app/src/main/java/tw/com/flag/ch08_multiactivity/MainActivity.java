package tw.com.flag.ch08_multiactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoSecondActivity(View v) {
        Intent it = new Intent(this, SecondActivity.class); //创建 Intent 并设置目标 Activity
        startActivity(it); // 启动 Intent 中的目标 Activity
    }
}

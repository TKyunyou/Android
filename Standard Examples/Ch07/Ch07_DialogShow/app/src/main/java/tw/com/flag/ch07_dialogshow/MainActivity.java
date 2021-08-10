package tw.com.flag.ch07_dialogshow;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlertDialog.Builder bdr = new AlertDialog.Builder(this);
        bdr.setMessage("对话框示范教学！\n" // 加入文字信息
                + "请按返回键关闭对话框");
        bdr.setTitle("欢迎");        // 加入标题
        bdr.setIcon(android.R.drawable.btn_star_big_on); // 加入图标
        bdr.setCancelable(true);   // 允许按返回键关闭对话框
        bdr.show();
    }
}

package tw.com.flag.ch03_linearlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    // 声明代表 UI 组件的变量
    EditText sname,fname,phone;
    TextView txv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化变量
        sname = (EditText) findViewById(R.id.surName);
        fname = (EditText) findViewById(R.id.firstName);
        phone = (EditText) findViewById(R.id.phone);
        txv = (TextView) findViewById(R.id.txv);
    }

    public void onclick(View v){
        txv.setText(sname.getText().toString()+    // 获取姓
                fname.getText()+                   // 获取名
                "的电话是 "+ phone.getText());   // 获取电话
    }
}


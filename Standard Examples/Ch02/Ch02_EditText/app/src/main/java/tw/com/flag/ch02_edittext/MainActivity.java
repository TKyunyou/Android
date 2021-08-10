package tw.com.flag.ch02_edittext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sayHello(View v){
        // 获取代表布局中 EditText 及 TextView 的对象
        EditText name = (EditText)findViewById(R.id.name);
        TextView txv = (TextView)findViewById(R.id.txv);

        // 将 EditText 文字串连接自定义信息再设置給 TextView
        txv.setText(name.getText().toString() + ", 您好！");
    }
}

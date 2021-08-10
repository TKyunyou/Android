package tw.com.flag.ch06_ticketspinner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    TextView txv;
    Spinner cinema;    // 戏院列表对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txv = (TextView)findViewById(R.id.txv);
        cinema = (Spinner) findViewById(R.id.cinema);
    }

    public void order(View v){
        String[] cinemas=getResources().           // 获取字符串资源中
                getStringArray(R.array.cinemas);   // 的字符串数组

        int index=cinema.getSelectedItemPosition();    // 获取选取的选项
        txv.setText("订"+cinemas[index]+"的票");     // 显示选取的选项
    }

}

package tw.com.flag.ch06_ticketspinner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    TextView txv;
    Spinner cinema, time;    // 戏院、场次列表对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txv = (TextView)findViewById(R.id.txv);
        cinema = (Spinner) findViewById(R.id.cinema);
        time = (Spinner) findViewById(R.id.time);
    }

    public void order(View v){
        String[] cinemas=getResources().           // 获取戏院字符串资源中
                getStringArray(R.array.cinemas);   // 的字符串数组
        String[] times=getResources().           // 获取场次字符串资源中
                getStringArray(R.array.times);   // 的字符串数组
        int index=cinema.getSelectedItemPosition();    // 获取选取的戏院
        int idxTime	=time.getSelectedItemPosition();    // 获取选取的场次
        txv.setText("订"+cinemas[index]+times[idxTime]+"的票");     // 显示选取的选项
    }

}

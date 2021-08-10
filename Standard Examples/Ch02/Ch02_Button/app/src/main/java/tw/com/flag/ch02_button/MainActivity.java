package tw.com.flag.ch02_button;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int size=31; //字体大小, 初值 30(sp)

    public void bigger(View v) {
        TextView txv;
        txv = (TextView) findViewById(R.id.txv);
        txv.setTextSize(++size);
    }
}

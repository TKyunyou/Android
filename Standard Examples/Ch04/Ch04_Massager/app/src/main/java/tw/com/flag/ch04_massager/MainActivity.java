package tw.com.flag.ch04_massager;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txv = (TextView) findViewById(R.id.txv);
        txv.setOnTouchListener(this);   // 登录触控监听对象
    }

    @Override
    public boolean onTouch(View v, MotionEvent e) {
        Vibrator vb =
                (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if(e.getAction() == MotionEvent.ACTION_DOWN) { // 按住屏幕中间的文字
            vb.vibrate(5000); // 震动 5 秒
        }
        else if(e.getAction() == MotionEvent.ACTION_UP) { // 放开手指
            vb.cancel(); // 停止震动
        }
        return true;
    }
}

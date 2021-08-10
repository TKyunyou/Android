package tw.com.flag.ch04_massager;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 修改继承自 Activity (或ActionBarActivity) 的 onTouchEvent 触控监听方法
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if(e.getAction() == MotionEvent.ACTION_DOWN) {      //按下屏幕
            vb.vibrate(new long[]{0,100,1000,100}, 2);      //每秒震动0.1秒,不断重复
        }
        else if(e.getAction() == MotionEvent.ACTION_UP){    //放开屏幕
            vb.cancel();
        }
        return true;
    }
}

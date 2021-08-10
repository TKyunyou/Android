package tw.com.flag.ch12_level;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
        implements SensorEventListener {  //实现传感器监听接口
    SensorManager sm;  //用来引用【传感器管理员】
    Sensor sr;         //用来引用【加速传感器对象】
    TextView txv;      //用来引用画面中的文字组件
    ImageView igv;     //用来引用画面中要移动的小图
    RelativeLayout layout;  //用来引用画面的 Layout 组件
    double mx = 0, my = 0;  //用来存储 x,y 方向每一等分的大小

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);//设置屏幕不随手机旋转

        sm = (SensorManager) getSystemService(SENSOR_SERVICE); //从系统服务获取传感器管理员
        sr = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //获取加速传感器
        txv = (TextView) findViewById(R.id.txvIno);       //获取TextView组件
        igv = (ImageView) findViewById(R.id.igvMove);   //获取要移动的ImageView组件
        layout = (RelativeLayout) findViewById(R.id.layout);  //获取layout组件
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(mx == 0) { //如果还没计算过
            mx = (layout.getWidth()-igv.getWidth()) /20.0;   //计算 x 方向每一等分的大小
            my = (layout.getHeight()-igv.getHeight()) /20.0; //计算 y 方向每一等分的大小
        }

        RelativeLayout.LayoutParams parms =     //获取小图的 LayoutParm 对象
                (RelativeLayout.LayoutParams) igv.getLayoutParams();
        parms.leftMargin = (int)((5-event.values[0]) * 2 * mx);  //设置左边界
        parms.topMargin = (int)((5+event.values[1]) * 2 * my);    //设置上边界
        igv.setLayoutParams(parms);    //将小图应用 LayoutParm, 使边界设置生效

        txv.setText(String.format("X轴: %1.2f, Y轴: %1.2f, Z轴: %1.2f",   // 显示传感器的数据
                event.values[0], event.values[1], event.values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {    }

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this, sr, SensorManager.SENSOR_DELAY_NORMAL); //向加速传感器 (sr) 注册监听对象(this)
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);  //取消监听对象(this) 的注册
    }
}
package tw.com.flag.ch12_accsensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
        implements SensorEventListener {
    SensorManager sm;  //用来引用【传感器管理员】
    Sensor sr;         //用来引用【加速传感器对象】
    TextView txv;      //用来引用画面中的文字组件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE); //从系统服务获取传感器管理员
        sr = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //获取加速传感器
        txv = (TextView) findViewById(R.id.textView);     // 获取TextView组件
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        txv.setText(String.format("X轴: %1.2f, Y轴: %1.2f, Z轴: %1.2f",
                event.values[0], event.values[1], event.values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

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
package tw.com.flag.ch14_simplelocation;

import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements LocationListener {
    static final int MIN_TIME = 5000;// 位置更新条件：5000 毫秒
    static final float MIN_DIST = 5; // 位置更新条件：5 米
    LocationManager mgr;        // 定位管理器
    TextView txv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txv = (TextView) findViewById(R.id.txv);
        // 获取系统服务的LocationManager对象
        mgr = (LocationManager)getSystemService(LOCATION_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 获取最佳的定位提供者
        String best = mgr.getBestProvider(new Criteria(), true);
        if (best != null) { // 获取快取的最后位置,如果有的话
            txv.setText("获取定位信息中...");
            mgr.requestLocationUpdates(best, MIN_TIME, MIN_DIST, this);    // 注册位置事件监听器
        }
        else { // 无提供者, 显示提示信息
            txv.setText("请确认已开启定位功能!");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mgr.removeUpdates(this);   // 停止监听位置事件
    }

    @Override
    public void onLocationChanged(Location location) { // 位置变更事件
        String str="定位提供者:"+location.getProvider();
        str+= String.format("\n纬度:%.6f\n经度:%.6f\n高度:%.2f米",
                location.getLatitude(),       // 纬度
                location.getLongitude(),   // 经度
                location.getAltitude());   // 高度
        txv.setText(str);
    }

    @Override
    public void onProviderDisabled(String provider) { }
    @Override
    public void onProviderEnabled(String provider) { }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) { }

    // 显示手机的定位设置画面
    public void setup(View v) {
        Intent it =    // 使用Intent对象启动"定位"设置程序
                new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(it);
    }
}
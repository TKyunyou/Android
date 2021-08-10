package tw.com.flag.ch14_simplelocation;

import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements LocationListener {
    static final int MIN_TIME = 5000;// 位置更新条件：5000 毫秒
    static final float MIN_DIST = 5; // 位置更新条件：5 公尺
    LocationManager mgr;        // 定位管理器
    TextView txv;
    Location myLocation;   // 存储最近的定位数据
    Geocoder geocoder;    // 用来查询地址的Geocoder对象
    EditText edtLat,edtLon; // 经纬度输入字段

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txv = (TextView) findViewById(R.id.txv);
        // 获取系统服务的LocationManager对象
        mgr = (LocationManager)getSystemService(LOCATION_SERVICE);
        edtLat = (EditText) findViewById(R.id.edtLan);
        edtLon = (EditText) findViewById(R.id.edtLon);
        geocoder = new Geocoder(this, Locale.getDefault());    // 创建 Geocoder 对象
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
        myLocation = location; // 存储定位数据
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

    public void getLocation(View v) {  // "以手机位置当输入"按钮的On Click 事件
        if(myLocation!=null){  // 若位置对象非null
            edtLat.setText(Double.toString(       // 将经度转成字符串
                    myLocation.getLatitude()));
            edtLon.setText(Double.toString(       // 将纬度值转成字符串
                    myLocation.getLongitude()));
        }
        else
            txv.setText("无法获取定位数据！");
    }

    public void onQuery(View view) {   // "用经纬度查地址"按钮的On Click 事件
        String strLat = edtLat.getText().toString();   // 获取输入的纬度字符串
        String strLon = edtLon.getText().toString();   // 获取输入的经度字符串
        if(strLat.length() == 0 || strLon.length() == 0) // 当字符串为空白时
            return;                                // 结束处理

        txv.setText("读取中...");
        double latitude = Double.parseDouble(strLat);  // 获取纬度值
        double longitude = Double.parseDouble(strLon); // 获取经度值

        String strAddr = "";   // 用来创建所要显示的信息字符串 (地址字符串)
        try {
            List<Address> listAddr = geocoder.
                    getFromLocation(latitude, longitude,// 用经纬度查地址
                            1);    // 只需返回1项地址数据

            if (listAddr == null || listAddr.size() == 0)  //检查是否获取了地址
                strAddr += "无法获取地址数据!";
            else {
                Address addr = listAddr.get(0);    // 获取 List 中的第一项(也是唯一的一项)
                for (int i = 0; i <= addr.getMaxAddressLineIndex(); i++)
                    strAddr += addr.getAddressLine(i) + "\n";
//                if (addr.getCountryCode() != null)
//                    strAddr += addr.getCountryCode(); //查询国码或地区码
            }
        } catch (Exception ex) {
            strAddr += "获取地址发生错误:" + ex.toString();
        }
        txv.setText(strAddr);
    }
}
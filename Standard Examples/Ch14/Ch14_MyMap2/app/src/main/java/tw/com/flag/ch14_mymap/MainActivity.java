package tw.com.flag.ch14_mymap;

import android.app.AlertDialog;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity
        implements LocationListener {

    static final int MIN_TIME = 5000;// 位置更新条件：5000 毫秒
    static final float MIN_DIST = 5; // 位置更新条件：5 米
    LocationManager mgr;        // 定位管理器
    TextView txv;
    GoogleMap map;  //操控地图的对象
    LatLng currPoint; // 存储当前的位置

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txv = (TextView) findViewById(R.id.txv);
        mgr = (LocationManager)getSystemService(LOCATION_SERVICE);

        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();

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

    private void setUpMapIfNeeded() {
        if (map == null) {
            map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();                     //获取 Google Map 对象
            if (map != null) {
                map.setMyLocationEnabled(true);    // 显示“我的位置”图标按钮
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);    // 设置地图为普通街道模式
                map.moveCamera(CameraUpdateFactory.zoomTo(18));    // 将地图缩放级数改为 18
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mgr.removeUpdates(this);   // 停止监听位置事件
    }

    @Override
    public void onLocationChanged(Location location) { // 位置变更事件
        if(location != null) { // 如果可以获取坐标
            txv.setText(String.format("纬度 %.4f, 经度 %.4f (%s 定位 )",
                    location.getLatitude(),  // 当前纬度
                    location.getLongitude(), // 当前经度
                    location.getProvider()));// 定位方式

            // 将地图中心点移到当前的经纬度
            currPoint = new LatLng(location.getLatitude(), location.getLongitude());
        }
        else { // 无法获取坐标
            txv.setText("暂时无法获取定位信息...");
        }
    }

    @Override
    public void onProviderDisabled(String provider) { }
    @Override
    public void onProviderEnabled(String provider) { }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) { }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) { // 按照选项的 id 来处理
            case R.id.mark:
                map.clear();
                map.addMarker(new MarkerOptions()
                        .position(map.getCameraPosition().target)
                        .title("到此一游"));
                break;
            case R.id.satellite:
                item.setChecked(!item.isChecked()); // 切换菜单项的勾选状态
                if(item.isChecked())               // 设置是否显示卫星图
                    map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                else
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case R.id.traffic:
                item.setChecked(!item.isChecked()); // 切换菜单项的勾选状态
                map.setTrafficEnabled(item.isChecked()); // 设置是否显示交通图
                break;
            case R.id.currLoction:
                map.animateCamera( // 在地图中移动到当前位置
                        CameraUpdateFactory.newLatLng(currPoint));
                break;
            case R.id.setGPS:
                Intent i = new Intent( // 利用 Intent 启动系统的定位服务设置
                        Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
                break;
            case R.id.about:
                new AlertDialog.Builder(this) // 用对话框显示程序版本与版权声明
                        .setTitle("关于 我的地图")
                        .setMessage("我的地图 体验版 v1.0\nCopyright 2015 Flag Corp.")
                        .setPositiveButton("关闭", null)
                        .show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
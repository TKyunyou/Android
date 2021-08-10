package tw.com.flag.ch16_itankled;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import tw.com.flag.api.FlagBt;
import tw.com.flag.api.FlagTank;
import tw.com.flag.api.OnFlagMsgListener;


public class MainActivity extends AppCompatActivity
        implements OnFlagMsgListener {

    FlagBt bt;      //声明蓝牙对象
    FlagTank tank;  //声明 iTank 对象
    TextView txv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setRequestedOrientation( // 让手机屏幕保持直立模式
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        txv = (TextView)findViewById(R.id.txv);
        bt = new FlagBt(this);      // 创建蓝牙对象
        tank = new FlagTank(bt);    // 创建 iTank 对象
    }

    public void onDestroy() {
        bt.stop(); // 确保程序结束前会停止蓝牙连接
        super.onDestroy();
    }

    public void connect(View v) {
        if(!bt.connect()) // 选取已配对设备进行连接
            txv.setText("找不到任何已配对设备");
    }

    public void quit(View v) {
        bt.stop();
        finish();
    }

    @Override
    public void onFlagMsg(Message msg) {
        switch(msg.what) {
            case FlagBt.CONNECTING: // 尝试与已配对设备连接
                txv.setText("正在连接到：" + bt.getDeviceName());
                break;
            case FlagBt.CONNECTED:  // 与已配对设备连接成功
                txv.setText("已连接到：" + bt.getDeviceName());
                break;
            case FlagBt.CONNECT_FAIL: // 连接失败
                txv.setText("连接失败！请重连");
                break;
            case FlagBt.CONNECT_LOST: // 当前连接意外中断
                txv.setText("连接中断!请重连");
                break;
        }
    }
    public void forward(View v) {
        tank.moveF();
    }

    public void backward(View v) {
        tank.moveB();
    }

    public void left(View v) {
        tank.moveL();
    }

    public void right(View v) {
        tank.moveR();
    }

    public void stop(View v) {
        tank.stop();
    }
}
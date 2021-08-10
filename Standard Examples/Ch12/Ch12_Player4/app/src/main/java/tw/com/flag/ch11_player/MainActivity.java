package tw.com.flag.ch11_player;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements
        MediaPlayer.OnPreparedListener,   //实现 MediaPlayer 的 3 个的事件监听接口
        MediaPlayer.OnErrorListener,
        MediaPlayer.OnCompletionListener,
        SensorEventListener {
    SensorManager sm;  //用来引用【传感器管理员】
    Sensor sr;         //用来引用【加速传感器对象】
    int delay = 0;     //用来延迟体感控制的检测间隔

    Uri uri;      //存储影音文件的 Uri
    TextView txvName, txvUri;  //引用到画面中的组件
    boolean isVideo = false;   //记录是否为视频文件

    Button btnPlay, btnStop;  //用来引用播放按钮、停止按钮
    CheckBox ckbLoop;         //用来引用重复播放多选按钮
    MediaPlayer mper;         //用来引用 MediaPlayer 对象
    Toast tos;                //用来引用 Toast 对象 (用于显示信息)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置屏幕不随手机旋转以及画面直向显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);//设置屏幕不随手机旋转
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设置屏幕直向显示
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//设置屏幕不进入休眠

        txvName = (TextView)findViewById(R.id.txvName); //引用到第1个文字组件
        txvUri = (TextView)findViewById(R.id.txvUri);   //引用到第2个文字组件
        btnPlay = (Button)findViewById(R.id.btnPlay);   //引用到播放按钮
        btnStop = (Button)findViewById(R.id.btnStop);   //引用到停止按钮
        ckbLoop = (CheckBox)findViewById(R.id.ckbLoop); //引用到重复播放多选按钮

        uri = Uri.parse("android.resource://" + //默认会播放程序内的音乐文件
                getPackageName() + "/" + R.raw.welcome);
        txvName.setText("welcome.mp3");         //在画面中显示文件名
        txvUri.setText("程序内的乐曲：" + uri.toString());//显示 Uri

        mper = new MediaPlayer();           //创建 MediaPlayer 对象
        mper.setOnPreparedListener(this);   //设置 3 个事件监听器
        mper.setOnErrorListener(this);
        mper.setOnCompletionListener(this);
        tos = Toast.makeText(this, "", Toast.LENGTH_SHORT); //创建 Toast 对象

        prepareMusic();   //准备音乐 (welcome.mp3) 的播放

        sm = (SensorManager) getSystemService(SENSOR_SERVICE); //从系统服务获得传感器管理员
        sr = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);   //获得加速传感器
    }

    void prepareMusic() {
        btnPlay.setText("播放");     //将按钮文字恢复为 "播放"
        btnPlay.setEnabled(false);   //使播放按钮不能单击 (要等准备好才能单击)
        btnStop.setEnabled(false);   //使停止钮不能单击

        try {
            mper.reset();       //如果之前播过歌, 必须 reset 后才能换歌
            mper.setDataSource(this, uri);  //指定歌曲来源
            mper.setLooping(ckbLoop.isChecked()); //设置是否重复播放
            mper.prepareAsync();  //要求 MediaPlayer 准备播放指定的音乐
        } catch (Exception e) {    //拦截错误并显示信息
            tos.setText("指定音乐文件错误！" + e.toString());
            tos.show();
        }
    }

    public void onPick(View v) {
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);    //创建动作为 "选取" 的 Intent
        if(v.getId() == R.id.btnPickAudio) {  //如果是 "选取歌曲" 按钮的 ID
            it.setType("audio/*");     //要选取所有音乐类型
            startActivityForResult(it, 100);
        }
        else {  //否则就是 "选取视频" 按钮
            it.setType("video/*");     //要选取所有视频类型
            startActivityForResult(it, 101);
        }
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK) {
            isVideo = (requestCode == 101); //记录是否选取了视频文件 (当标识符为101时)
            uri = convertUri(data.getData());  //取得选取文件的 Uri 并进行 Uri 格式转换
            txvName.setText(uri.getLastPathSegment ());  //显示文件名 (Uri 最后的节点文字)
            txvUri.setText("文件位置：" + uri.getPath());//显示文件的路径
            if(!isVideo) prepareMusic();   //如果是音乐就准备播放
        }
    }

    Uri convertUri(Uri uri) { //将 "content://" 类型的 Uri 转换为 "file://" 的 Uri
        if(uri.toString().substring(0, 7).equals("content")) {  //如果是以 "content" 开头
            String[] colName = { MediaStore.MediaColumns.DATA };    //声明要查询的字段
            Cursor cursor = getContentResolver().query(uri, colName,  //以 uri 进行查询
                    null, null, null);
            cursor.moveToFirst();      //移到查询结果的第一个记录
            uri = Uri.parse("file://" + cursor.getString(0)); //将路径转为 Uri
            cursor.close();     //关闭查询结果
        }
        return uri;   //返回 Uri 物件
    }

    //********************************************************

    @Override
    public void onPrepared(MediaPlayer mp) {
        btnPlay.setEnabled(true);  //当准备好时, 只需让【播放】按钮可以单击即可
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mper.seekTo(0);             //将播放位置归 0
        btnPlay.setText("播放");    //让播放按钮显示 "播放"
        btnStop.setEnabled(false);  //让停止按钮不能按
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        tos.setText("发生错误，停止播放");  //显示错误信息
        tos.show();
        return true;
    }

    //********************************************************

    public void onMpPlay(View v) {   //单击【播放】按钮时
        if(isVideo) {   //如果是视频
            Intent it = new Intent(this, Video.class); //创建启动 Video Activity 的 Intent
            it.putExtra("uri", uri.toString());   //将视频的 Uri 以 "uri" 为名加入 Intent 中
            startActivity(it);    //启动 Video Activity
            return;
        }

        if (mper.isPlaying()) {  //如果正在播, 就暂停
            mper.pause();   //暂停播放
            btnPlay.setText("继续");
        }
        else {  //如果没有在播, 就开始播
            mper.start();   //开始播放
            btnPlay.setText("暂停");
            btnStop.setEnabled(true);
        }
    }

    public void onMpStop(View v) {   //单击【停止】按钮时
        mper.pause();   //暂停播放
        mper.seekTo(0); //移到音乐中 0 秒的位置
        btnPlay.setText("播放");
        btnStop.setEnabled(false);
    }

    public void onMpLoop(View v) {   //单击【重复播放】多选按钮时
        if (ckbLoop.isChecked())
            mper.setLooping(true);   //设置要重复播放
        else
            mper.setLooping(false);  //设置不要重复播放
    }

    public void onMpBackward(View v) {   //按下倒退图形按钮时
        if(!btnPlay.isEnabled()) return; //如果还没准备好(播放按钮不能单击), 则不处理
        int len = mper.getDuration();       //读取音乐长度
        int pos = mper.getCurrentPosition();//读取当前播放位置
        pos -= 10000;                     //倒退 10 秒 (10000ms)
        if(pos <0) pos = 0;                 //不可小于 0
        mper.seekTo(pos);                   //移动播放位置
        tos.setText("倒退10秒：" + pos/1000 + "/" + len/1000);  //显示信息
        tos.show();
    }

    public void onMpForward(View v) {   //单击前进图形按钮时
        if(!btnPlay.isEnabled()) return; //如果还没准备好(播放按钮不能单击), 则不处理
        int len = mper.getDuration();       //读取音乐长度
        int pos = mper.getCurrentPosition();//读取当前播放位置
        pos += 10000;                     //前进 10 秒 (10000ms)
        if(pos > len) pos = len;            //不可大于总秒数
        mper.seekTo(pos);                   //移动播放位置
        tos.setText("前进10秒：" + pos/1000 + "/" + len/1000);  //显示信息
        tos.show();
    }

    //********************************************************

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this, sr, SensorManager.SENSOR_DELAY_NORMAL); //向加速传感器 (sr) 注册监听对象(this)
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mper.isPlaying()) {  //如果正在播, 就暂停
            btnPlay.setText("继续");
            mper.pause();  //暂停播放
        }
        sm.unregisterListener(this);  //取消监听对象(this) 的注册
    }

    @Override
    protected void onDestroy() {
        mper.release();  //释放 MediaPlayer 对象
        super.onDestroy();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x, y, z;
        x = event.values[0];
        y = event.values[1];
        z = event.values[2];

        if(Math.abs(x) < 1 && Math.abs(y) < 1 && z < -9) {  //如果手机面朝下平放
            if(mper.isPlaying()) {    //如果正在播放, 就要暂停
                btnPlay.setText("继续");
                mper.pause();
            }
        }
        else {
            if(delay > 0) {  //delay大于 0 时, 表示要略过这次的检测
                delay--;  //将次数减 1, 直到 0 为止
            }
            else {
                if(Math.abs(x) + Math.abs(y) + Math.abs(z) > 32) {  //加速度总合超过 32
                    if(btnPlay.isEnabled()) //如果音乐已准备好 (可以播放)
                        onMpPlay(null);    //模拟按下播放按钮 (切换“播放”与“暂停”状态)
                    delay = 5;  //延迟 5 次不检测 (约 1 秒)
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {    }
}
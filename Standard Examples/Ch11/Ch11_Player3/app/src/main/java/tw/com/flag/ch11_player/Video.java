package tw.com.flag.ch11_player;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;


public class Video extends AppCompatActivity {
    VideoView vdv;  //用来引用 VideoView 对象
    int pos = 0;    //用来记录前次的播放位置

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //以上2项设置必须在本方法之前调用

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏系统的状态栏
        getSupportActionBar().hide();           //隐藏标题栏
        setContentView(R.layout.activity_video);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//保持屏幕一直开着 (不要自动休眠)

        Intent it = getIntent();     //获取传入的 Intent 对象
        Uri uri = Uri.parse(it.getStringExtra("uri")); //取出要播放视频的 Uri
        if(savedInstanceState != null)                 //如果是因旋转而重新启动 Activity
            pos = savedInstanceState.getInt("pos", 0); //取出旋转前所存储的播放位置

        vdv = (VideoView)findViewById(R.id.videoView);    //引用到画面中的 VideoView 组件
        MediaController mediaCtrl = new MediaController(this); //创建播放控制对象
        vdv.setMediaController(mediaCtrl);  //设置要用播放控制对象来控制播放
        vdv.setVideoURI(uri);   //设置要播放视频的 Uri
    }

    @Override
    protected void onResume() { //当 Activity 启动、或由暂停状态回到互动状态时
        super.onResume();
        vdv.seekTo(pos);   //移到 pos 的播放位置
        vdv.start();       //开始播放
    }

    @Override
    protected void onPause() { //当 Activity 进入暂停状态时 (例如切换到其他程序)
        super.onPause();
        pos = vdv.getCurrentPosition(); //存储播放位置
        vdv.stopPlayback();    //停止播放
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("pos", pos);     //将暂停时所获取的当前播放位置存储起来
    }
}
package tw.com.flag.ch11_player;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    Uri uri;      //存储影音文件的 Uri
    TextView txvName, txvUri;  //引用到画面中的组件
    boolean isVideo = false;   //记录是否为视频文件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置屏幕不随手机旋转、以及画面直向显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);//设置屏幕不随手机旋转
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设置屏幕直向显示

        txvName = (TextView)findViewById(R.id.txvName); //引用到第1个文字组件
        txvUri = (TextView)findViewById(R.id.txvUri);   //引用到第2个文字组件

        uri = Uri.parse("android.resource://" + //默认会播放程序内的音乐文件
                getPackageName() + "/" + R.raw.welcome);
        txvName.setText("welcome.mp3");         //在画面中显示文件名
        txvUri.setText("程序内的乐曲：" + uri.toString());//显示 Uri
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
            uri = convertUri(data.getData());  //获取选取文件的 Uri 并进行 Uri 格式转换
            txvName.setText(uri.getLastPathSegment ());  //显示文件名 (Uri 最后的节点文字)
            txvUri.setText("文件位置：" + uri.getPath());//显示文件的路径
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
        return uri;   //返回 Uri 对象
    }
}
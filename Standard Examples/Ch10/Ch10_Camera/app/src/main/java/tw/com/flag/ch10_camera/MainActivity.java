package tw.com.flag.ch10_camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onGet(View v) {
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  //创建动作为拍照的意图
        startActivityForResult(it, 100);   //启动意图并要求返回数据
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK && requestCode==100) {
            Bundle extras = data.getExtras();         //将 Intent 的附加数据转为 Bundle 对象
            Bitmap bmp = (Bitmap) extras.get("data"); //由 Bundle 取出名为 "data" 的 Bitmap 数据
            ImageView imv = (ImageView)findViewById(R.id.imageView);
            imv.setImageBitmap(bmp);                //将 Bitmap 数据显示在 ImageView 中
        }
        else {
            Toast.makeText(this, "没有拍到照片", Toast.LENGTH_LONG).show();
        }
    }
}
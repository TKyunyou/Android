package tw.com.flag.ch10_camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Uri imgUri;    //用来引用拍照存盘的 Uri 对象
    ImageView imv; //用来引用 ImageView 对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imv = (ImageView)findViewById(R.id.imageView);  //引用 Layout 中的 ImageView 组件
    }

    public void onGet(View v) {
        String dir = Environment.getExternalStoragePublicDirectory(  //获取系统的公用图像文件路径
                Environment.DIRECTORY_PICTURES).toString();
        String fname = "p" + System.currentTimeMillis() + ".jpg";  //利用当前时间组合出一个不会重复的文件名
        imgUri = Uri.parse("file://" + dir + "/" + fname);    //按照前面的路径和文件名创建 Uri 对象

        Intent it = new Intent("android.media.action.IMAGE_CAPTURE");
        it.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);    //将 uri 加到拍照 Intent 的额外数据中
        startActivityForResult(it, 100);
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK && requestCode==100) {
            showImg();
        }
        else {
            Toast.makeText(this, "没有拍到照片", Toast.LENGTH_LONG).show();
        }
    }

    void showImg() {
        int iw, ih, vw, vh;

        BitmapFactory.Options option = new BitmapFactory.Options(); //创建选项对象
        option.inJustDecodeBounds = true;      //设置选项：只读取图像文件信息而不加载图像文件
        BitmapFactory.decodeFile(imgUri.getPath(), option);  //读取图像文件信息存入 Option 中
        iw = option.outWidth;   //从 option 中读出图像宽度
        ih = option.outHeight;  //从 option 中读出图像高度
        vw = imv.getWidth();    //获取 ImageView 的宽度
        vh = imv.getHeight();   //获取 ImageView 的高度

        int scaleFactor = Math.min(iw/vw, ih/vh); // 计算缩小比率

        option.inJustDecodeBounds = false;  //关闭只加载图像文件信息的选项
        option.inSampleSize = scaleFactor;  //设置缩小比例, 例如 2 则长宽都将缩小为原来的 1/2
        Bitmap bmp = BitmapFactory.decodeFile(imgUri.getPath(), option); //载入图像文件
        imv.setImageBitmap(bmp);
    }
}
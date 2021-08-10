package tw.com.flag.ch10_camera;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
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

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);//设置屏幕不随手机旋转
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设置屏幕直向显示
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

    public void onPick(View v) {
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);    //动作设为 "选取内容"
        it.setType("image/*");              //设置要选取的媒体类型为：所有类型的图片
        startActivityForResult(it, 101);  //启动意图, 并要求返回选取的图像
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK) {   //要求的意图成功了
            switch(requestCode) {
                case 100: //拍照
                    Intent it = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, imgUri);//设为系统共享媒体文件
                    sendBroadcast(it);
                    break;
                case 101: //选取相片
                    imgUri = convertUri(data.getData());  //获取选取相片的 Uri 并进行 Uri 格式转换
                    break;
            }
            showImg();  //显示 imgUri 所指明的相片
        }
        else {
            Toast.makeText(this, "没有拍到照片", Toast.LENGTH_LONG).show();
        }
    }

    Uri convertUri(Uri uri) {
        if(uri.toString().substring(0, 7).equals("content")) {  //如果是以 "content" 开头
            String[] colName = { MediaStore.MediaColumns.DATA };    //声明要查询的字段
            Cursor cursor = getContentResolver().query(uri, colName,  //以 imgUri 进行查询
                    null, null, null);
            cursor.moveToFirst();      //移到查询结果的第一个记录
            uri = Uri.parse("file://" + cursor.getString(0)); //将路径转为 Uri
            cursor.close();     //关闭查询结果
        }
        return uri;   //返回 Uri 对象
    }

    void showImg() {
        int iw, ih, vw, vh;
        boolean needRotate;  //用来存储是否需要旋转

        BitmapFactory.Options option = new BitmapFactory.Options(); //创建选项对象
        option.inJustDecodeBounds = true;      //设置选项：只读取图像文件信息而不加载图像文件
        BitmapFactory.decodeFile(imgUri.getPath(), option);  //读取图像文件信息存入 Option 中
        iw = option.outWidth;   //从 option 中读出图像宽度
        ih = option.outHeight;  //从 option 中读出图像高度
        vw = imv.getWidth();    //获取 ImageView 的宽度
        vh = imv.getHeight();   //获取 ImageView 的高度

        int scaleFactor;
        if(iw<ih) {    //如果图片的宽度小于高度
            needRotate = false;                 //不需要旋转
            scaleFactor = Math.min(iw/vw, ih/vh);   // 计算缩小比率
        }
        else {
            needRotate = true;                  //需要旋转
            scaleFactor = Math.min(iw/vh, ih/vw);   // 将 ImageView 的宽、高互换来计算缩小比率
        }

        option.inJustDecodeBounds = false;  //关闭只加载图像文件信息的选项
        option.inSampleSize = scaleFactor;  //设置缩小比例, 例如 2 则长宽都将缩小为原来的 1/2
        Bitmap bmp = BitmapFactory.decodeFile(imgUri.getPath(), option); //载入图像

        if(needRotate) { //如果需要旋转
            Matrix matrix = new Matrix();  //创建 Matrix 对象
            matrix.postRotate(90);         //设置旋转角度
            bmp = Bitmap.createBitmap(bmp , //用原来的 Bitmap 产生一个新的 Bitmap
                    0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
        }
        imv.setImageBitmap(bmp);
    }
}
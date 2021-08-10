package tw.com.flag.ch09_intentstarter;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        Intent it = new Intent(Intent.ACTION_VIEW); //创建意图并指定默认动作

        switch(v.getId()) {//读取按钮的 Id 来做相关处理
            case R.id.buttonEmail:   //指定 E-mail 地址
                it.setData(Uri.parse("mailto:service@flag.com.tw"));
                it.putExtra(Intent.EXTRA_CC,                      //设置副本收件人
                        new String[] {"test@flag.com.tw"});
                it.putExtra(Intent.EXTRA_SUBJECT, "您好");        //设置主题
                it.putExtra(Intent.EXTRA_TEXT, "谢谢！");//设置内容
                break;
            case R.id.buttonSms:  //指定短信的传送对象及内容
                it.setData(Uri.parse("sms:0999-123456?body=您好！"));
                break;
            case R.id.buttonWeb:  //指定网址
                it.setData(Uri.parse("http://www.sina.com.cn"));
                break;
            case R.id.buttonGps:  //指定 GPS 坐标：北京西站
                it.setData(Uri.parse("geo:39.89534,116.32128"));
                break;
            case R.id.buttonWebSearch:  //搜索 Web 数据
                it.setAction(Intent.ACTION_WEB_SEARCH);  //将动作改为搜索
                it.putExtra(SearchManager.QUERY, "新浪网站");
                break;
        }
        startActivity(it);  //启动适合意图的程序
    }
}
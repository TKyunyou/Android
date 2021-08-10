package tw.com.flag.ch13_hellowebview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


public class MainActivity extends AppCompatActivity {
    WebView wv;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wv = (WebView) findViewById(R.id.wv);
        pb = (ProgressBar) findViewById(R.id.pb);
        wv.getSettings().setJavaScriptEnabled(true);   // 启用 JavaScript
        wv.getSettings().setBuiltInZoomControls(true); // 启用缩放功能
        wv.invokeZoomPicker();                         // 显示缩放小工具
        wv.setWebViewClient(new WebViewClient());     // 创建及使用 WebViewClient 对象
        wv.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                pb.setProgress(progress);       //设置进度
                pb.setVisibility(progress < 100? View.VISIBLE: View.GONE);  //按进度来让进度条显示或消失
            }
        });
        wv.loadUrl("http://www.jd.com");   // 连接到京东网站，可以不用加网页的文件名了
    }

    @Override
    public void onBackPressed() {
        if(wv.canGoBack()){   // 如果 WebView 有上一页
            wv.goBack();     // 回上一页
            return;
        }
        super.onBackPressed();  //调用父类的同名方法, 以执行默认动作(结束程序)
    }
}

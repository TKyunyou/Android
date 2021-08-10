package tw.com.flag.ch13_searchflickr;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ProgressBar;


public class MainActivity extends AppCompatActivity {
    WebView wv;
    ProgressBar pb;
    EditText keyText;
    String keyword;    //用来记录关键字
    String baseURL="https://m.flickr.com/#/search/advanced_QM_q_IS_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wv = (WebView) findViewById(R.id.webView);
        pb = (ProgressBar) findViewById(R.id.progressBar);
        keyText=(EditText)findViewById(R.id.editText);

        wv.getSettings().setJavaScriptEnabled(true);   // 启用 JavaScript
        wv.setWebViewClient(new WebViewClient());     // 创建及使用 WebViewClient 对象
        wv.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                pb.setProgress(progress);       //设置进度
                pb.setVisibility(progress < 100 ? View.VISIBLE : View.GONE);  //按进度来让进度条显示或消失
            }
        });
    }

    @Override
    public void onBackPressed() {  //按下返回键时的事件处理
        if(wv.canGoBack()){   // 如果 WebView 有上一页
            wv.goBack();     // 回上一页
            return;
        }
        super.onBackPressed();  //调用父类的同名方法, 以执行默认动作(结束程序)
    }

    public void search(View v){
        keyword = keyText.getText().toString().replaceAll("\\s+", "+"); //将字符串中的单一或连续空白置换成 +
        wv.loadUrl(baseURL + keyword);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putString("关键字", keyword);  // 存储当前的查询参数
        editor.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences myPref = getPreferences(MODE_PRIVATE);
        keyword = myPref.getString("关键字", "Taipei+101");
        if(wv.getUrl()==null)
            wv.loadUrl(baseURL+keyword);
    }
}
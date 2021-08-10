package tw.com.flag.ch08_multiactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void goBack(View v) {
        finish(); // 結束 Activity, 即可回到前一个 Activity
    }
}

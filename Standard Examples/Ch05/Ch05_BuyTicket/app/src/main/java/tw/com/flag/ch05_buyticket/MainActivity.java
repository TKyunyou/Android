package tw.com.flag.ch05_buyticket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void show(View v){
        TextView txv=(TextView)findViewById(R.id.txv);
        RadioGroup ticketType =
                (RadioGroup) findViewById(R.id.ticketType);

        // 按选取选项显示不同信息
        switch(ticketType.getCheckedRadioButtonId()){
            case R.id.adult:   // 选全票
                txv.setText("买全票");
                break;
            case R.id.child:   // 选半票
                txv.setText("买半票");
                break;
            case R.id.senior:  // 选敬老票
                txv.setText("买敬老票");
                break;
        }
    }
}

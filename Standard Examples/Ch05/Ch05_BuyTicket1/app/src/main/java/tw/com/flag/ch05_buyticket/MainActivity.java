package tw.com.flag.ch05_buyticket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
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
        RadioGroup ticketNumber =
                (RadioGroup) findViewById(R.id.ticketNumber);

        int id;
        id=ticketType.getCheckedRadioButtonId();
        RadioButton type= (RadioButton)findViewById(id);
        id=ticketNumber.getCheckedRadioButtonId();
        RadioButton number= (RadioButton)findViewById(id);
        txv.setText("买" + type.getText() + number.getText());
    }
}

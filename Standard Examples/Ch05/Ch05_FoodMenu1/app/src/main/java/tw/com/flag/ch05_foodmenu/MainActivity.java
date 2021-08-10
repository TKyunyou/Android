package tw.com.flag.ch05_foodmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void takeOrder(View v) {
        CheckBox chk;
        String msg="";
        // 用数组存放所有 CheckBox 组件的 ID
        int[] id={R.id.chk1, R.id.chk2, R.id.chk3, R.id.chk4};

        for(int i:id){    // 以循环逐一查看各 CheckBox 是否被选取
            chk = (CheckBox) findViewById(i);
            if(chk.isChecked())            // 若有被选取
                msg+="\n"+chk.getText();   // 将换行字符及选项文字
        }                                  // 附加到 msg 字符串后面

        if(msg.length()>0) // 有点餐
            msg ="你点购的餐点是："+msg;
        else
            msg ="请点餐!";

        // 在文本框中显示点购的餐点项
        ((TextView) findViewById(R.id.showOrder)).setText(msg);
    }
}

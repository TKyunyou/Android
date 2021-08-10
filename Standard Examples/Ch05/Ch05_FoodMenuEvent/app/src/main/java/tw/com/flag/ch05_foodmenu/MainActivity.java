package tw.com.flag.ch05_foodmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
        implements CompoundButton.OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 所有复选框 ID 的数组
        int[] chk_id={R.id.chk1,R.id.chk2,R.id.chk3,R.id.chk4,
                R.id.chk5,R.id.chk6,R.id.chk7,R.id.chk8};

        for(int id:chk_id){ // 用循环替所有复选框加上监听对象
            CheckBox chk=(CheckBox) findViewById(id);
            chk.setOnCheckedChangeListener(this);
        }
    }

    // 用来存储已选取选项的 ID 集合对象
    ArrayList<CompoundButton> selected=new ArrayList<>();

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isChecked)		// 若选项被选取
            selected.add(buttonView);	// 加到集合之中
        else			// 若选项被取消
            selected.remove(buttonView);	// 从集合中删除
    }

    public void takeOrder(View v) {
        String msg="";  // 存储信息的字符串

        for(CompoundButton i:selected)   // 以循环逐一将换行字符及
            msg+="\n"+i.getText();   	// 选项文字附加到 msg 字符串后面

        if(msg.length()>0) // 有点餐
            msg ="你点购的餐点是："+msg;
        else
            msg ="请点餐!";

        // 在文本框中显示点购的选项
        ((TextView) findViewById(R.id.showOrder)).setText(msg);
    }

}

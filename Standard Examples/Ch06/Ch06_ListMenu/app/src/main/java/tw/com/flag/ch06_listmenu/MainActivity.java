package tw.com.flag.ch06_listmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取 ListView 对象, 并设置按下操作的监听器
        ListView lv=(ListView) findViewById(R.id.lv);
        lv.setOnItemClickListener(this);
    }

    // 存储当前选取的选项 (餐点) 名称字符串
    ArrayList<String> selected= new ArrayList<>();

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView txv = (TextView) view; // 将被单击的 View 对象转成 TextView
        String item=txv.getText().toString();

        if(selected.contains(item)) // 若 ArrayList 已有同名选项
            selected.remove(item);  // 就将它删除
        else                        // 若 ArrayList 没有同名选项
            selected.add(item);     // 就将它加到 ArrayList (当成选取的选项)

        String msg;
        if(selected.size()>0){   // 若 ArrayList 中的选项数大于 0
            msg="你点了:";
            for(String str:selected)
                msg+=" "+str;    // 每个选项 (餐点) 名称前空一格
        }                        // 并附加到信息字符串后面
        else                     // 若 ArrayList 中的选项数等于 0
            msg="请点餐!";

        TextView msgTxv =(TextView) findViewById(R.id.msgTxv);
        msgTxv.setText(msg);  // 显示信息字符串
    }

}

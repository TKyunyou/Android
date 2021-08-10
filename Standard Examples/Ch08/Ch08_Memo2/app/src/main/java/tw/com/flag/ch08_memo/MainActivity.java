package tw.com.flag.ch08_memo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    String[] aMemo = { // 默认的备忘内容
            "1. 单击可以编辑备忘",
            "2. 长按可以清除备忘","3.","4.","5.","6." };
    ListView lv; // 显示备忘录的 ListView
    ArrayAdapter<String> aa; // ListView 与备忘数据的桥梁

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView)findViewById(R.id.listView);
        aa = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, aMemo);

        lv.setAdapter(aa);    //设置 listView1 的内容

        //设置 listView1 被单击的监听器
        lv.setOnItemClickListener(this);
        //设置 listView1 被长按的监听器
        lv.setOnItemLongClickListener(this);
    }

    public void onItemClick(AdapterView<?> a, View v, int pos, long id) {
        Intent it = new Intent(this, Edit.class);
        it.putExtra("备忘", aMemo[pos]); //附加备忘选项的内容
        startActivityForResult(it, pos); //启动 Edit 并以选项位置为标识符
    }

    public boolean onItemLongClick(AdapterView<?> a, View v, int pos, long id) {
        aMemo[pos] = (pos+1) + "."; //将内容清除 (只剩编号)
        aa.notifyDataSetChanged();  //通知 Adapter 要更新数组内容
        return true;             //返回 true 表示此事件已处理
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent it) {
        if(resultCode == RESULT_OK) {
            aMemo[requestCode] = it.getStringExtra("备忘"); // 使用返回的数据更新数组内容
            aa.notifyDataSetChanged(); // 通知 Adapter 数组内容有更新
        }
    }
}
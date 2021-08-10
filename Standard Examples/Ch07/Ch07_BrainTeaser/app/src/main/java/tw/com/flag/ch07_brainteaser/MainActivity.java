package tw.com.flag.ch07_brainteaser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {
    // 创建问题数组
    String[] queArr = {"什么门远永关不上？","什么东西没人爱吃？",
            "什么瓜不能吃？","什么布切不断？",
            "什么鼠最爱干净？","偷什么不犯法？"};
    // 创建答案数组
    String[] ansArr = { "球门", "亏",
            "傻瓜","瀑布",
            "环保署","偷笑" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 创建供 ListView 使用的 ArrayAdapter 对象
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1, // 使用内建的布局资源
                queArr);                  // 以 queArr 数组当数据源

        ListView lv = (ListView)findViewById(R.id.lv);  //获取  ListView
        lv.setAdapter(adapter);           //设置 ListView 使用的 Adapter
        lv.setOnItemClickListener(this); //设置 ListView 选项被单击时的事件监听器
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,
                "答案︰" + ansArr[position], Toast.LENGTH_SHORT).show();
    }
}
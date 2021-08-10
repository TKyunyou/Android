package tw.com.flag.ch08_memo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class Edit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent it = getIntent();               //获取传入的 Intent 对象
        int no = it.getIntExtra("编号", 0);    //读出名为 "编号" 的 Int 数据, 若没有则返回 0
        String s = it.getStringExtra("备忘");  //读出名为 "备忘" 的 String 数据

        TextView txv = (TextView)findViewById(R.id.textView);
        txv.setText(no + ".");                 //在画面左上角显示编号
        EditText edt = (EditText)findViewById(R.id.editText);
        if(s.length() > 3)
            edt.setText(s.substring(3)); //将传来的备忘数据去除前3个字, 然后填入编辑组件中
    }

    public void onCancel(View v) {  //单击取消按钮时
        finish();    //结束活动
    }

    public void onSave(View v) {    //单击存储按钮时
        finish();    //结束活动
    }

}

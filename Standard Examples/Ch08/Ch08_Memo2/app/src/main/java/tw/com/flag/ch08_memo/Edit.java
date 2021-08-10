package tw.com.flag.ch08_memo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class Edit extends AppCompatActivity {

    TextView txv;
    EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent it = getIntent();               //获取传入的 Intent 对象
        String s = it.getStringExtra("备忘");  //读出名为 "备忘" 的 String 数据

        txv = (TextView)findViewById(R.id.textView);
        txv.setText(s.substring(0, 2));                 //在画面左上角显示编号
        edt = (EditText)findViewById(R.id.editText);
        if(s.length() > 3)
            edt.setText(s.substring(3)); //将传来的备忘数据去除前3个字, 然后填入编辑组件中
    }

    public void onCancel(View v) {  //单击取消按钮时
        setResult(RESULT_CANCELED); // 返回代表取消的结果码
        finish();    //结束活动
    }

    public void onSave(View v) {    //单击存储按钮时
        Intent it2 = new Intent();
        it2.putExtra("备忘", txv.getText() + " " + edt.getText()); // 附加选项编号与修改后的内容
        setResult(RESULT_OK, it2); // 返回代表成功的结果码, 以及修改的数据
        finish();    //结束活动
    }

}

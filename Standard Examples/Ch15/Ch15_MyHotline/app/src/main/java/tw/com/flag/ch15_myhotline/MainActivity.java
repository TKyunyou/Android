package tw.com.flag.ch15_myhotline;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {
    static final String DB_NAME="HotlineDB";
    static final String TB_NAME="hotlist";
    static final int MAX=8;
    static final String[] FROM=new String[] {"name","phone","email"};
    SQLiteDatabase db;
    Cursor cur;
    SimpleCursorAdapter adapter;
    EditText etName,etPhone,etEmail;
    Button btInsert, btUpdate, btDelete;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName=(EditText)findViewById(R.id.etName);
        etPhone=(EditText)findViewById(R.id.etPhone);
        etEmail=(EditText)findViewById(R.id.etEmail);
        btInsert =(Button)findViewById(R.id.btInsert);
        btUpdate =(Button)findViewById(R.id.btUpdate);
        btDelete =(Button)findViewById(R.id.btDelete);

        // 打开或创建数据库
        db = openOrCreateDatabase(DB_NAME,  Context.MODE_PRIVATE, null);

        // 创建数据表
        String createTable="CREATE TABLE IF NOT EXISTS " + TB_NAME +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " + //索引字段
                "name VARCHAR(32), " +
                "phone VARCHAR(16), " +
                "email VARCHAR(64))";
        db.execSQL(createTable);

        cur=db.rawQuery("SELECT * FROM "+ TB_NAME, null);  // 查询数据

        // 若查询结果是空的则写入 2 项测试数据
        if(cur.getCount()==0){
            addData("旗标公司","02-23963257","service@flag.com.tw");
            addData("旗讯公司","02-23214335","service@pcdiy.com.tw");
        }

        adapter=new SimpleCursorAdapter(this,
                R.layout.item, cur,
                FROM,
                new int[] {R.id.name,R.id.phone,R.id.email}, 0);

        lv=(ListView)findViewById(R.id.lv);
        lv.setAdapter(adapter);           // 设置 Adapter
        lv.setOnItemClickListener(this); // 设置单击事件的监听器
        requery(); // 调用自定义方法, 重新查询及设置按钮状态
    }

    private void addData(String name, String phone, String email) {
        ContentValues cv=new ContentValues(3); // 创建含 3 个字段的 ContentValues对象
        cv.put(FROM[0], name);
        cv.put(FROM[1], phone);
        cv.put(FROM[2], email);

        db.insert(TB_NAME, null, cv);  // 新增1个记录
    }

    private void update(String name, String phone, String email, int id) {
        ContentValues cv=new ContentValues(3);
        cv.put(FROM[0], name);
        cv.put(FROM[1], phone);
        cv.put(FROM[2], email);

        db.update(TB_NAME, cv, "_id="+id, null);   // 更新 id 所指的字段
    }

    private void requery() {   // 重新查询的自定义方法
        cur=db.rawQuery("SELECT * FROM "+TB_NAME, null);
        adapter.changeCursor(cur); //更改 Adapter的Cursor
        if(cur.getCount()==MAX) // 已达上限, 停用新增按钮
            btInsert.setEnabled(false);
        else
            btInsert.setEnabled(true);
        btUpdate.setEnabled(false);    // 停用更新按钮
        btDelete.setEnabled(false);    // 停用删除按钮
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        cur.moveToPosition(position); //   移动 Cursor 至用户选取的选项
        // 读出姓名,电话,Email数据并显示
        etName.setText(cur.getString(
                cur.getColumnIndex(FROM[0])));
        etPhone.setText(cur.getString(
                cur.getColumnIndex(FROM[1])));
        etEmail.setText(cur.getString(
                cur.getColumnIndex(FROM[2])));

        btUpdate.setEnabled(true); // 启用更新按钮
        btDelete.setEnabled(true); // 启用删除按钮
    }

    public void onInsertUpdate(View v){
        String nameStr=etName.getText().toString().trim();
        String phoneStr=etPhone.getText().toString().trim();
        String emailStr=etEmail.getText().toString().trim();
        if(nameStr.length()==0 ||  // 任一字段的内容为空即返回
                phoneStr.length()==0 ||
                emailStr.length()==0) return;

        if(v.getId()==R.id.btUpdate)   // 单击更新按钮
            update(nameStr, phoneStr, emailStr, cur.getInt(0));
        else                     // 单击新增按钮
            addData(nameStr, phoneStr, emailStr);

        requery(); // 更新 Cursor 内容
    }

    public void onDelete(View v){  // 删除按钮的On Click事件方法
        db.delete(TB_NAME, "_id="+cur.getInt(0),null);
        requery();
    }

    public void call(View v){  // 打电话
        String uri="tel:" + cur.getString(
                cur.getColumnIndex(FROM[1]));
        Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(it);
    }

    public void mail(View v){  // 发送电子邮件
        String uri="mailto:"+cur.getString(
                cur.getColumnIndex(FROM[2]));
        Intent it = new Intent(Intent.ACTION_SENDTO, Uri.parse(uri));
        startActivity(it);
    }
}
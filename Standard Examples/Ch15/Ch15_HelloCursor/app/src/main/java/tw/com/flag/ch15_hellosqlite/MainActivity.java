package tw.com.flag.ch15_hellosqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    static final String db_name="testDB";  // 数据库名称
    static final String tb_name="test";       // 数据表名称
    SQLiteDatabase db; //数据库

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 打开或创建数据库
        db = openOrCreateDatabase(db_name,  Context.MODE_PRIVATE, null);

        String createTable="CREATE TABLE IF NOT EXISTS " +
                tb_name +        // 数据表名称
                "(name VARCHAR(32), " +    //姓名字段
                "phone VARCHAR(16), " +    //电话字段
                "email VARCHAR(64))";  //Email字段
        db.execSQL(createTable);   // 创建数据表

        Cursor c=db.rawQuery("SELECT * FROM "+tb_name, null);  // 查询tb_name数据表中的所有数据
        if (c.getCount()==0){  // 若无数据, 则立即新增 2笔数据
            addData("Flag Publishing Co.","02-23963257","service@flag.com.tw");
            addData("PCDIY Magazine","02-23214335","service@pcdiy.com.tw");
            c=db.rawQuery("SELECT * FROM "+tb_name, null); // 重新查询
        }

        if (c.getCount()>0){   // 若有数据
            String str="总共有 "+c.getCount()+"项数据\n";
            str+="-----\n";

            c.moveToLast();    // 移到第 1 项数据
            do{       // 逐项读出数据
                str+="name:"+c.getString(0)+"\n";
                str+="phone:"+c.getString(1)+"\n";
                str+="email:"+c.getString(2)+"\n";
                str+="-----\n";
            } while(c.moveToPrevious());   // 有一下项就继续循环

            TextView txv=(TextView)findViewById(R.id.txv);
            txv.setText(str);
        }
        db.close();       // 关闭数据库
    }

    private void addData(String name, String phone, String email) {
        ContentValues cv=new ContentValues(3); // 创建含3个数据项的对象
        cv.put("name", name);
        cv.put("phone", phone);
        cv.put("email", email);

        db.insert(tb_name, null, cv);  // 将数据加到数据表
    }
}
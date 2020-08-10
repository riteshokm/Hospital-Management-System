package com.riteshkm.hms.DB;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.riteshkm.hms.R;
import com.riteshkm.hms.person._Adapter;
import com.riteshkm.hms.person.Person;

import java.util.ArrayList;

public class Search_DB extends AppCompatActivity {

    ListView lv;
    ArrayList<Person> list;
    _Adapter adapter;
    EditText et_name,et_id;
    ImageView img;
    String personType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__db);

        img = findViewById(R.id.img);
        et_name = findViewById(R.id.et_name);
        et_id = findViewById(R.id.et_id);
        lv = findViewById(R.id.lv);

        Intent intent = getIntent();
        personType = intent.getStringExtra("TypeOfPerson");

        switch (personType) {
            case "Patient":
                img.setImageResource(R.drawable.patient);
                break;
            case "Doctor":
                img.setImageResource(R.drawable.doctor);
                break;
            case "Nurse":
                img.setImageResource(R.drawable.nurse);
                break;
        }
    }

    public void onSearch(View view) {
        DB_Helper db_helper = new DB_Helper(this);
        SQLiteDatabase db = db_helper.getReadableDatabase();

        String name = et_name.getText().toString();
        long id = 0;
        String temp_id = et_id.getText().toString();
        if(temp_id.equals(""));
        else id = Long.parseLong(temp_id);
        if(name.equals("")&&id==0) Toast.makeText(getApplicationContext(),"Fields Can't be empty...! ",Toast.LENGTH_SHORT).show();
        else{
            list = db_helper.search(name,temp_id,personType,db);
            if(list.size()>0) {
                adapter = new _Adapter(this, R.layout.support_simple_spinner_dropdown_item, list);
                lv.setAdapter(adapter);
                lv.setVisibility(View.VISIBLE);
            }else{
                lv.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(),"Record Doesn't exist...! ",Toast.LENGTH_SHORT).show();
            }
        }

    }
}

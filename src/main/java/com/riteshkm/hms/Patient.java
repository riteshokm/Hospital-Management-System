package com.riteshkm.hms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.riteshkm.hms.DB.Add_DB;
import com.riteshkm.hms.DB.DB_Helper;
import com.riteshkm.hms.DB.On_delete;
import com.riteshkm.hms.DB.Search_DB;
import com.riteshkm.hms.DB.Update_DB;
import com.riteshkm.hms.person._Adapter;
import com.riteshkm.hms.person.Person;

import java.util.ArrayList;


public class Patient extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    DB_Helper db;
    ListView lv;
    ArrayList<Person> list;
    _Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_patient);
        bottomNavigationView = findViewById(R.id.b_nav);
        lv = findViewById(R.id.lv);
        db = new DB_Helper(getApplicationContext());
        list = db.showList("Patient");
        adapter = new _Adapter(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,list);
        lv.setAdapter(adapter);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch(item.getItemId()) {

                case R.id.add:
                    Intent intent1 = new Intent(Patient.this, Add_DB.class);
                    intent1.putExtra("TypeOfPerson","Patient");
                    startActivity(intent1);
                    break;
                case R.id.remove:
                    Intent intent2 = new Intent(Patient.this, On_delete.class);
                    intent2.putExtra("TypeOfPerson","Patient");
                    startActivity(intent2);
                    break;
                case R.id.update:
                    Intent intent3 = new Intent(Patient.this, Update_DB.class);
                    intent3.putExtra("TypeOfPerson","Patient");
                    startActivity(intent3);
                    break;
                case R.id.search:
                    Intent intent4 = new Intent(Patient.this, Search_DB.class);
                    intent4.putExtra("TypeOfPerson","Patient");
                    startActivity(intent4);
                    break;
            }
                return true;
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        list = db.showList("Patient");
        adapter = new _Adapter(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,list);
        lv.setAdapter(adapter);
    }
}

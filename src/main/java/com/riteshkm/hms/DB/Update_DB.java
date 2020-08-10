package com.riteshkm.hms.DB;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.riteshkm.hms.R;

public class Update_DB extends AppCompatActivity {

    EditText et_id, et;
    ImageView img;
    Spinner spinner;
    String option;
    String Update;
    String[] options;
    String personType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__db);
        et_id = findViewById(R.id.et_id);
        et = findViewById(R.id.et);
        img = findViewById(R.id.img);
        spinner = findViewById(R.id.item);
        Intent intent = getIntent();
        personType = intent.getStringExtra("TypeOfPerson");

        switch (personType) {
            case "Patient":
                img.setImageResource(R.drawable.patient);
                options = new String[]{"Name", "Age", "Gender", "Appointed Doctor", "Contact", "Address", "Bill Status"};
                break;
            case "Doctor":
                img.setImageResource(R.drawable.doctor);
                options = new String[]{"Name", "Age", "Gender", "Appointed Patient", "Contact", "Address", "Salary"};
                break;
            case "Nurse":
                img.setImageResource(R.drawable.nurse);
                options = new String[]{"Name", "Age", "Gender", "Contact", "Address", "Salary"};
                break;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                option = parent.getItemAtPosition(position).toString();

                switch (option) {
                    case "Name":
                        Update = "Name";
                        et.setHint("Enter New Name");
                        et.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case "Age":
                        Update = "Age";
                        et.setHint("New Age");
                        et.setInputType(InputType.TYPE_CLASS_NUMBER);
                        break;
                    case "Gender":
                        Update = "Gender";
                        et.setHint("New Gender");
                        et.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case "Appointed Doctor":
                        Update = "r_person";
                        et.setHint("New Appointed Doctor");
                        et.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case "Appointed Patient":
                        Update = "r_person";
                        et.setHint("New Appointed Patient");
                        et.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case "Contact":
                        Update = "Contact";
                        et.setHint("New Contact No.");
                        et.setInputType(InputType.TYPE_CLASS_NUMBER);
                        break;
                    case "Address":
                        Update = "Address";
                        et.setHint("New Address");
                        et.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case "Bill Status":
                        Update = "Money";
                        et.setHint("New Bill Status");
                        et.setInputType(InputType.TYPE_CLASS_TEXT);
                        et.setMaxLines(1);
                        break;
                    case "Salary":
                        Update = "Money";
                        et.setHint("New Salary");
                        et.setInputType(InputType.TYPE_CLASS_NUMBER);
                        et.setMaxLines(1);
                        break;
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "No Option Selected...!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onUpdate(View view) {

        DB_Helper db_helper = new DB_Helper(this);
        SQLiteDatabase db = db_helper.getWritableDatabase();

        long id = 0;
        String temp_id = et_id.getText().toString();
        if (temp_id.equals("")) ;
        else id = Long.parseLong(temp_id);
        if (temp_id.equals("") || id == 0)
            Toast.makeText(getApplicationContext(), "Invail ID...! ", Toast.LENGTH_SHORT).show();
        else {

            String data = et.getText().toString();

            if (option.equals("Age")) {
                if (data.length() > 0) {
                    int temp = Integer.parseInt(data);
                    if (temp > 0 || temp <= 100) {
                        db_helper.updateData(temp_id, Update, data, personType, db);
                    } else {
                        Toast.makeText(getApplicationContext(), "Invail Age...! ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Invail Age...! ", Toast.LENGTH_SHORT).show();
                }
            } else if(data.length()==0){
                Toast.makeText(getApplicationContext(), "Invail " + option +"...! ", Toast.LENGTH_SHORT).show();
            }else{
                db_helper.updateData(temp_id, Update, data, personType, db);
            }
        }
    }
}

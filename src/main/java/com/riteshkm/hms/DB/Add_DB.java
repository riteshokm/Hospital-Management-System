package com.riteshkm.hms.DB;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.riteshkm.hms.R;

public class Add_DB extends AppCompatActivity {

    EditText et_name, et_age, r_person, et_contact, et_address, et_money;
    RadioGroup gender;
    RadioButton radioGenderButton;
    ImageView img;
    String personType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__d_b);

        img = findViewById(R.id.img);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        r_person = findViewById(R.id.r_person);
        et_contact = findViewById(R.id.et_contact);
        et_address = findViewById(R.id.et_address);
        et_money = findViewById(R.id.et_money);
        gender = findViewById(R.id.Gender);


        Intent intent = getIntent();
        personType = intent.getStringExtra("TypeOfPerson");

        switch (personType) {
            case "Patient":
                img.setImageResource(R.drawable.patient);
                r_person.setHint("Appointed Doctor");
                et_money.setHint("Bill Status - Paid | Unpaid");
                break;
            case "Doctor":
                img.setImageResource(R.drawable.doctor);
                r_person.setHint("Appointed Patient");
                et_money.setHint("Salary");
                break;
            case "Nurse":
                img.setImageResource(R.drawable.nurse);
                r_person.setVisibility(View.INVISIBLE);
                et_money.setHint("Salary");
                break;
        }

    }

    public void Add(View view) {
        DB_Helper db_helper = new DB_Helper(this);
        SQLiteDatabase db = db_helper.getWritableDatabase();

        String Name = ""; int Age = 0; String Gender = ""; String Category = ""; String rperson = ""; long Contact = 0; String Address = ""; String Money = "";

        String temp1 = et_age.getText().toString();
        String temp2 = et_contact.getText().toString();

        Name = et_name.getText().toString();
        if(temp1.equals(""));
            else Age = Integer.parseInt(temp1);

        int selectedID = gender.getCheckedRadioButtonId();
        radioGenderButton = (RadioButton) findViewById(selectedID);
        Gender = radioGenderButton.getText().toString();

        Category = personType;
        rperson = r_person.getText().toString();
        if(temp2.equals(""));
            else Contact = Long.parseLong(temp2);
        Address = et_address.getText().toString();
        Money = et_money.getText().toString();

        if(Name.equals("")||Age==0||Gender.equals("")||Category.equals("")||rperson.equals("")||Contact==0||Address.equals("")||Money.equals("")){
            Toast.makeText(getApplicationContext(),"Fill All The Columns Correctly",Toast.LENGTH_SHORT).show();
        }
        else if(Age<0||Age>100) Toast.makeText(getApplicationContext(),"Invalid Age",Toast.LENGTH_SHORT).show();
        else if(personType.equals("Patient") && !Money.equals("Unpaid") && !Money.equals("Paid")) Toast.makeText(getApplicationContext(),"Invalid Bill Status",Toast.LENGTH_SHORT).show();
        else{
            db_helper.insertData(Name,Age,Gender,Category,rperson,Contact,Address,Money,db);
            Toast.makeText(getApplicationContext(),"Record Inserted Successfully",Toast.LENGTH_SHORT).show();
        }

    }
}

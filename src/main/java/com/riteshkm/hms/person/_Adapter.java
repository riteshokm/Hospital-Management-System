package com.riteshkm.hms.person;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.riteshkm.hms.DB.Search_DB;
import com.riteshkm.hms.R;

import java.util.ArrayList;

public class _Adapter extends ArrayAdapter<Person> {
    ArrayList<Person> person;
    Context context;
    int resource;

    public _Adapter(@NonNull Context context, int resource, ArrayList<Person> person) {
        super(context,resource, person);
        this.person = person;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        if(this.context instanceof Search_DB) convertView = layoutInflater.inflate(R.layout.custom_layout2,null,false);
        else convertView = layoutInflater.inflate(R.layout.custom_layout1,null,false);

        TextView tv_id,tv_name,tv_gender,tv_age,tvr_person,tv_contact,tv_adrress,tv_money;
        TextView tvR_person,tvMoney;
        ImageView img;

        img = convertView.findViewById(R.id.img);
        tv_id = convertView.findViewById(R.id.tv_id);
        tv_name = convertView.findViewById(R.id.tv_name);
        tv_gender = convertView.findViewById(R.id.tv_gender);
        tv_age = convertView.findViewById(R.id.tv_age);
        tvr_person = convertView.findViewById(R.id.tvr_person);
        tv_contact = convertView.findViewById(R.id.tv_contact);
        tv_adrress = convertView.findViewById(R.id.tv_address);
        tv_money = convertView.findViewById(R.id.tv_money);

        tvR_person = convertView.findViewById(R.id.tvR_person);
        tvMoney = convertView.findViewById(R.id.tvMoney);

        Person _person = person.get(position);

        String personType = _person.getCategory();
        String Gender = _person.getGender();

        switch (personType){
            case "Patient":
                if (Gender.equals("Male")) img.setImageResource(R.drawable.male);
                else img.setImageResource(R.drawable.female);
                tvR_person.setText("Appointed Doctor: ");
                tvMoney.setText("Bill Status: ");
                break;
            case "Doctor":
                if (Gender.equals("Male")) img.setImageResource(R.drawable.m_doctor);
                else img.setImageResource(R.drawable.f_doctor);
                tvR_person.setText("Appointed Patient: ");
                tvMoney.setText("Salary: ");
                break;
            case "Nurse":
                if (Gender.equals("Male")) img.setImageResource(R.drawable.m_nurse);
                else img.setImageResource(R.drawable.f_nurse);
                tvR_person.setVisibility(View.GONE);
                tvr_person.setVisibility(View.GONE);
                tvMoney.setText("Salary: ");
                break;
        }

        tv_id.setText(Integer.toString(_person.getId()));
        tv_name.setText(_person.getName());
        tv_gender.setText(Gender);
        tv_age.setText(Integer.toString(_person.getAge()));
        tvr_person.setText(_person.getR_person());
        tv_contact.setText(Long.toString(_person.getContact()));
        tv_adrress.setText(_person.getAddress());
        tv_money.setText(_person.getMoney());

        return convertView;
    }
}

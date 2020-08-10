package com.riteshkm.hms.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.riteshkm.hms.person.Person;

import java.util.ArrayList;

public class DB_Helper extends SQLiteOpenHelper {

    static final String dbname = "DB";
    static final int version = 1;
    Context context;

    public DB_Helper(Context context) {
        super(context, dbname, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "create table info (_id integer primary key Autoincrement, Name Text not null, Age real , Gender Text not null,Category text, r_person text, Contact real, Address text, Money text)";
        db.execSQL(query);

        insertData("Ramesh Kumar", 23, "Male", "Patient", "Dr. Rahul Sharma", 9976543214L, "Vaishali Nagar,Jaipur", "Paid", db);
        insertData("Meghana Menon", 20, "Female", "Patient", "Dr. Rohit Mishra", 9123453214L, "Lajput Nagar,Jaipur", "Paid", db);
        insertData("Dr. Rahul Sharma", 25, "Male", "Doctor", "Ramesh Kumar", 9987654321L, "Kishor Nagar,Jaipur", "50000", db);
        insertData("Dr. Rohit Mishra", 30, "Male", "Doctor", "Meghana Menon", 9123456789L, "Vibha Nagar,Jaipur", "90000", db);
        insertData("Roshini Gupta", 20, "Female", "Nurse", "", 7748159263L, "Gopalpura,Jaipur", "15000", db);
        insertData("Vaibhav Verma", 21, "Male", "Nurse", "", 7848159623L, "Nehru Chok,Jaipur", "15000", db);

    }

    void insertData(String Name, int Age, String Gender, String Category, String r_person, long Contact, String Address, String Money, SQLiteDatabase db) {

        ContentValues values = new ContentValues();
        values.put("Name", Name);
        values.put("Age", Age);
        values.put("Gender", Gender);
        values.put("Category", Category);
        values.put("r_person", r_person);
        values.put("Contact", Contact);
        values.put("Address", Address);
        values.put("Money", Money);
        db.insert("info", null, values);

    }

    public ArrayList<Person> showList(String personType) {

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from info where Category = ?", new String[]{personType});

        int Id = 0;
        String Name = "";
        int Age = 0;
        String Gender = "";
        String Category = "";
        String r_person = "";
        long Contact = 0;
        String Address = "";
        String Money = "";

        ArrayList<Person> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            Id = cursor.getInt(0);
            Name = cursor.getString(1);
            Age = cursor.getInt(2);
            Gender = cursor.getString(3);
            Category = cursor.getString(4);
            r_person = cursor.getString(5);
            Contact = cursor.getLong(6);
            Address = cursor.getString(7);
            Money = cursor.getString(8);

            list.add(new Person(Id, Name, Age, Gender, Category, r_person, Contact, Address, Money));
        }
        return list;
    }

    void updateData(String id, String option, String update, String personType, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(option, update);
        int temp = db.update("info", values, "Category = ? and _id = ?", new String[]{personType, id});
        if (temp > 0) Toast.makeText(context, "Record Updated", Toast.LENGTH_LONG).show();
        else Toast.makeText(context, "Record Doesn't exist...!", Toast.LENGTH_LONG).show();
    }

    void Delete(String name, String id, String personType, SQLiteDatabase db) {
        int temp = db.delete("info", "Category = ? and (Name = ? or _id = ?)", new String[]{personType, name, id});
        if (temp > 0) Toast.makeText(context, "Record Deleted", Toast.LENGTH_LONG).show();
        else Toast.makeText(context, "Record Doesn't exist...!", Toast.LENGTH_LONG).show();
    }

    public ArrayList<Person> search(String name, String id, String personType, SQLiteDatabase db) {

        Cursor cursor = db.rawQuery("Select * from info where Category = ? and (Name = ? or _id = ?)", new String[]{personType, name, id});

        int Id = 0;
        String Name = "";
        int Age = 0;
        String Gender = "";
        String Category = "";
        String r_person = "";
        long Contact = 0;
        String Address = "";
        String Money = "";

        ArrayList<Person> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            Id = cursor.getInt(0);
            Name = cursor.getString(1);
            Age = cursor.getInt(2);
            Gender = cursor.getString(3);
            Category = cursor.getString(4);
            r_person = cursor.getString(5);
            Contact = cursor.getLong(6);
            Address = cursor.getString(7);
            Money = cursor.getString(8);

            list.add(new Person(Id, Name, Age, Gender, Category, r_person, Contact, Address, Money));
        }
        return list;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}

package com.riteshkm.hms.person;

public class Person {
    int Id; String Name; int Age; String Gender; String Category; String r_person; long Contact; String Address; String Money;

    public Person(int id, String name, int age, String gender, String category, String r_person, long contact, String address, String money) {
        Id = id;
        Name = name;
        Age = age;
        Gender = gender;
        Category = category;
        this.r_person = r_person;
        Contact = contact;
        Address = address;
        Money = money;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public int getAge() {
        return Age;
    }

    public String getGender() {
        return Gender;
    }

    public String getCategory() {
        return Category;
    }

    public String getR_person() {
        return r_person;
    }

    public long getContact() {
        return Contact;
    }

    public String getAddress() {
        return Address;
    }

    public String getMoney() {
        return Money;
    }
}

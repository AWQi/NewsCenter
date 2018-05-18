package com.example.dell.news;
public class Student {
    //姓名
    private String name;
    //年龄
    private int age;
    //住址
    private String address;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Student(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", address="
                + address + "]";
    }
}
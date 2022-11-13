package com.example.demo;

public class User {
    String name;
    int age;

    public User(){
        this.age = 20;
        this.name = "Lin";
    }
    public User(String name, int age)
    {
        this.age = age;
        this.name = name;
    }

    public User(String na)
    {
        String[] na2 = na.split(" ");
        name = na2[0];
        age = Integer.parseInt(na2[1]);
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString()
    {
        return "Пользователь " + name + ", " + age + (age % 10 == 1 ? " год": age % 10 == 0 ? " лет" : age % 10 <= 4 ? " года" : " лет");
    }
}

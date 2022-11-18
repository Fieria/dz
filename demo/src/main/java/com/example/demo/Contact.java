package com.example.demo;

public class Contact {
    String name;
    int number;
    String email;

    public Contact(String name, int number, String email){
        this.name = name;
        this.number = number;
        this.email = email;
    }

    public Contact(String s){
        String[] s2 = s.split(" ");
        name = s2[0];
        number = Integer.parseInt(s2[1]);
        this.number = number;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString()
    {
        return "Контакт " + name + ", номер: " + number + ", email: " + email;
    }

}

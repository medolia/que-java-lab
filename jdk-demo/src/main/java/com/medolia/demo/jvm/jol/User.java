package com.medolia.demo.jvm.jol;

/**
 * @author lbli@trip.com
 * @date 2021/9/15
 */
public class User {
    int id,age,weight;
    byte gender;
    long phone;
    char local;
    String name;

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public void setLocal(char local) {
        this.local = local;
    }

    public void setName(String name) {
        this.name = name;
    }
}

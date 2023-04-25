package com.mrgrd56.models.people;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Person {
    private String fullName;
    private int age;
    private Gender gender;

    public Person() {
    }

    public Person(String fullName, int age, Gender gender) {
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("fullName", fullName)
                .append("age", age)
                .append("gender", gender)
                .toString();
    }
}
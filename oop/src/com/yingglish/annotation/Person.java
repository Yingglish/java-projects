package com.yingglish.annotation;


@Table("user")
public class Person {
    @Column("id")
    private int id;
    @Column("userName")
    private String userName;
    @Column("age")
    private int age;
    @Column("Email")
    private String Email;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}

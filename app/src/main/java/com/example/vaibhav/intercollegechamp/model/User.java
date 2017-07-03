package com.example.vaibhav.intercollegechamp.model;

/**
 * Created by vaibhav on 25/4/17.
 */

public class User {
    public User() {    }

    private String firstname,lastname,collegename,dept,sclass,email,password,mobile;


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCollegename() {
        return collegename;
    }

    public void setCollegename(String collegename) {
        this.collegename = collegename;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public User(String firstname, String lastname, String collegename, String dept, String sclass, String email, String password, String mobile) {

        this.firstname=firstname;
        this.lastname=lastname;
        this.collegename=collegename;
        this.dept=dept;
        this.sclass=sclass;
        this.email=email;
        this.password=password;
        this.mobile=mobile;





    }


}

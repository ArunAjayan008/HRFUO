package com.polymorfuz.hrfuo.model;

public class Profile {
    public String profname;
    public String age;

    public Profile(String profname, String age) {
        this.profname = profname;
        this.age = age;
    }

    public String getProfname() {
        return profname;
    }

    public void setProfname(String profname) {
        this.profname = profname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

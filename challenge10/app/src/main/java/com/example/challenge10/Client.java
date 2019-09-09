package com.example.challenge10;

public class Client {
    String Name;
    String Birth;
    String Mobile;

    public Client(String name, String birth, String mobile) {
        Name = name;
        Birth = birth;
        Mobile = mobile;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBirth() {
        return Birth;
    }

    public void setBirth(String birth) {
        Birth = birth;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }
}

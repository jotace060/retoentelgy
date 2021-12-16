package com.reto.rest.alias;
public class Data {
    public int id;
    public String last_name;
    public String email;
    //public String first_name;
    //public String avatar;
    public Data(int id, String last_name, String email) {
    }

    public Data() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }


}

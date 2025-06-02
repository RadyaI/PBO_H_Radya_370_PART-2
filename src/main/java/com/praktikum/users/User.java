package com.praktikum.users;

public abstract class User {
    private String nama;
    private String nim;

    public User(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setNim(String nim) { 
        this.nim = nim;
    }

    public String getNim() {
        return nim;
    }

    // Ga dipake kayanya ini  
    // abstract public void login(String username, String pass);

    abstract public void displayInfo(String nama, String nim);

    abstract public void displayAppMenu();
}
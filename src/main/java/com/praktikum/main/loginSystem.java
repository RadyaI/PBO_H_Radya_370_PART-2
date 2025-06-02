package com.praktikum.main;

import com.praktikum.data.Item;
import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;
import com.praktikum.users.User;

import java.util.ArrayList;

public class loginSystem {
    public static ArrayList<User> userList = new ArrayList<>();
    public static ArrayList<Item> reportedItem = new ArrayList<>();
    public static User currentUser;

    public static User doLogin(String userInput, String passInput) {
        for (User u : userList) {
            if (u instanceof Admin) {
                Admin admin = (Admin) u;
                if (admin.getUsername().equals(userInput) && admin.getPassword().equals(passInput)) {
                    return admin;
                }
            } else if (u instanceof Mahasiswa) {
                Mahasiswa mhs = (Mahasiswa) u;
                if (mhs.getNama().equals(userInput) && mhs.getNim().equals(passInput)) {
                    return mhs;
                }
            }
        }
        return null;
    }

}
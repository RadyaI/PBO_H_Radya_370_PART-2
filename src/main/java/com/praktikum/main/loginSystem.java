package com.praktikum.main;

import com.praktikum.data.Item;
import com.praktikum.error.loginExeptions;
import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;
import com.praktikum.users.User;

import java.util.ArrayList;
import java.util.Scanner;

public class loginSystem {
    public static ArrayList<User> userList = new ArrayList<>();
    public static ArrayList<Item> reportedItem = new ArrayList<>();

    public static void main(String[] args) {
        userList.add(new Admin("Admin", "Admin370"));
        userList.add(new Mahasiswa("Radya", "370"));

        Scanner scanner = new Scanner(System.in);
        User loggedInUser = null;

        System.out.println("\n=== Sistem Login ===");

        while (loggedInUser == null) {
            System.out.print("Masukkan username/nama: ");
            String username = scanner.nextLine();
            System.out.print("Masukkan password/NIM: ");
            String password = scanner.nextLine();

            try {
                loggedInUser = doLogin(username, password);

                if (loggedInUser == null) {
                    throw new loginExeptions("\nUser not found!");
                }

                System.out.println("Login berhasil!");
                loggedInUser.displayInfo(username, password);
                loggedInUser.displayAppMenu();
            } catch (loginExeptions e) {
                System.out.println("ERROR: " + e.getMessage());
                System.out.println("Silakan coba lagi.\n");
            }
        }

        scanner.close();
    }

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
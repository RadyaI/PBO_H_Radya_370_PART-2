package com.praktikum.users;

import java.util.*;
import com.praktikum.actions.AdminActions;
import com.praktikum.data.Item;
import com.praktikum.main.loginSystem;

public class Admin extends User implements AdminActions {
    private String username = "Admin370";
    private String password = "Password370";

    public Admin(String nama, String nim) {
        super(nama, nim);
    }

    public List<Item> getDaftarBarang() {
        return loginSystem.reportedItem;
    }

    public List<Mahasiswa> getDaftarMahasiswa() {
        List<Mahasiswa> daftar = new ArrayList<>();
        for (User u : loginSystem.userList) {
            if (u instanceof Mahasiswa) {
                daftar.add((Mahasiswa) u);
            }
        }
        return daftar;
    }

    public boolean tambahMahasiswa(String nama, String nim) {
        for (User u : loginSystem.userList) {
            if (u instanceof Mahasiswa && u.getNim().equals(nim)) {
                return false;
            }
        }
        loginSystem.userList.add(new Mahasiswa(nama, nim));
        return true;
    }

    public boolean hapusMahasiswa(String nim) {
        Iterator<User> it = loginSystem.userList.iterator();
        while (it.hasNext()) {
            User u = it.next();
            if (u instanceof Mahasiswa && u.getNim().equals(nim)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public boolean tandaiBarangClaimed(Item item) {
        if (item != null && "Reported".equalsIgnoreCase(item.getStatus())) {
            item.setStatus("Claimed");
            return true;
        }
        return false;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public void displayInfo(String nama, String nim) {
        // dikosongkan sudah pakai GUI
    }

    @Override
    public void displayAppMenu() {
        // dikosongkan sudah pakai GUI
    }

    @Override
    public void manageUsers() {
        // dikosongkan sudah pakai GUI
    }

    public void manageItems() {
        // dikosongkan sudah pakai GUI
    }
}
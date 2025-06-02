package com.praktikum.users;

import com.praktikum.actions.MahasiswaActions;
import com.praktikum.data.Item;
import com.praktikum.main.loginSystem;

import java.util.ArrayList;

public class Mahasiswa extends User implements MahasiswaActions {

    public Mahasiswa(String nama, String nim) {
        super(nama, nim);
    }

    public void reportItem(String namaBarang, String deskripsiBarang, String lokasiTerakhir) {
        loginSystem.reportedItem.add(new Item(namaBarang, deskripsiBarang, lokasiTerakhir));
    }

    public ArrayList<Item> getReportedItems() {
        ArrayList<Item> userItems = new ArrayList<>();
        for (Item i : loginSystem.reportedItem) {
            if (i.getStatus().equals("Reported")) {
                userItems.add(i);
            }
        }
        return userItems;
    }

    @Override
    public void displayInfo(String nama, String nim) {
        // Ga kepake lagi, karena udah pindah ke GUI
    }

    @Override
    public void displayAppMenu() {
        // Ga kepake lagi, karena udah pindah ke GUI
    }

    @Override
    public void reportItem() {
        // Ga dipakai karena yang versi CLI
    }

    @Override
    public void viewReportedItems() {
        // Ga dipakai karena yang versi CLI
    }
}

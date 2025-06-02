package com.praktikum.users;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import com.praktikum.actions.*; 
import com.praktikum.data.Item;
import com.praktikum.main.loginSystem;

public class Mahasiswa extends User implements MahasiswaActions {
    public Mahasiswa(String nama, String nim) {
        super(nama, nim);
    }

    Scanner scan = new Scanner(System.in);

    @Override
    public void displayInfo(String nama, String nim) {
        System.out.printf("\nNama: %s\n", nama);
        System.out.printf("Nim: %s\n", nim);
    }

    @Override
    public void displayAppMenu() {
        boolean loop = true;

        do {
            System.out.println("\n===== MAHASISWA MENU =====");
            System.out.println("1. Laporkan Barang Hilang");
            System.out.println("2. Lihat Daftar Laporan");
            System.out.println("0: LogOut");
            int pilihan = 0;
            try {
                System.out.print("Masukkan pilihan (1/2/0): ");
                pilihan = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Masukkan hanya angka!!");
                scan.nextLine();
                continue;
            }
            switch (pilihan) {
                case 1:
                    reportItem();
                    break;
                case 2:
                    viewReportedItems();
                    break;
                case 0:
                    loop = false;
                    loginSystem.main(null);
                    break;
                default:
                    System.err.println("Pilihan Tidak Valid...coba lagi");
                    break;
            }
        } while (loop);
    }

    @Override
    public void reportItem() {
        System.out.print("Masukkan nama barang: ");
        String namaBarang = scan.nextLine();

        System.out.print("Masukkan deskripsi barang: ");
        String deskripsiBarang = scan.nextLine();

        System.out.print("Lokasi terakhir ditemukan: ");
        String lokasiTerakhir = scan.nextLine();

        loginSystem.reportedItem.add(new Item(namaBarang, deskripsiBarang, lokasiTerakhir));

        System.out.println("\n===============");
        System.out.println("Barang: " + namaBarang);
        System.out.println("Deskripsi Barang: " + deskripsiBarang);
        System.out.println("Lokasi terakhir: " + lokasiTerakhir);
        System.out.println("===============");
    }

    @Override
    public void viewReportedItems() {
        Iterator<Item> it_item = loginSystem.reportedItem.iterator();
        int index = 1;
        boolean adaYangDilapor = true;

        while (it_item.hasNext()) {
            Item barang = it_item.next();
            if (barang.getStatus().equals("Reported")) {
                if (adaYangDilapor) {
                    System.out.printf("%-5s %-25s %-40s %-30s\n", "NO", "NAMA", "DESKRIPSI", "LOKASI");
                    adaYangDilapor = false;
                }
                System.out.printf("%-5d %-25s %-40s %-30s\n", index++, barang.getItemName(),
                        barang.getDescription(), barang.getLocation());
            }
        }

        if (adaYangDilapor) {
            System.out.println("Tidak ada barang berstatus 'Reported'...");
        }
    }

}
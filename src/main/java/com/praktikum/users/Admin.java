package com.praktikum.users;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import com.praktikum.actions.*;
import com.praktikum.data.Item;
import com.praktikum.main.loginSystem;

public class Admin extends User implements AdminActions {
    private String username = "Admin370";
    private String password = "Password370";

    public Admin(String nama, String nim) {
        super(nama, nim);
    }

    Scanner scan = new Scanner(System.in);

    @Override
    public void displayAppMenu() {
        boolean loop = true;
        int pilihan = 1;

        do {
            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. Kelola Laporan Barang");
            System.out.println("2. Kelola Data Mahasiswa");
            System.out.println("0: LogOut");

            try {
                System.out.print("Masukkan pilihan (1/2/0): ");
                pilihan = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Hanya Menerima input angka!!");
                scan.nextLine();
                continue;
            }
            switch (pilihan) {
                case 1:
                    manageItems();
                    break;
                case 2:
                    manageUsers();
                    break;
                case 0:
                    loop = false;
                    loginSystem.main(null);
                    return;
                default:
                    System.err.println("Pilihan Tidak Valid");
                    loop = false;
                    break;
            }
        } while (loop);
    }

    @Override
    public void displayInfo(String nama, String nim) {
        System.out.printf("\nNama: %s\n", nama);
        System.out.printf("Nim: %s ", nim);
    }

    @Override
    public void manageUsers() {
        boolean loop = true;

        while (loop) {
            System.out.println("\n===== KELOLA MAHASISWA =====");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Hapus Mahasiswa");
            System.out.println("3. Kembali");

            System.out.print("Masukkan pilihan: ");
            try {
                int pilihan = scan.nextInt();
                scan.nextLine();

                switch (pilihan) {
                    case 1:
                        System.out.print("Masukkan Nama Mahasiswa: ");
                        String nama = scan.nextLine();

                        System.out.print("Masukkan NIM Mahasiswa: ");
                        String nim = scan.nextLine();

                        loginSystem.userList.add(new Mahasiswa(nama, nim));
                        System.out.println("Mahasiswa berhasil ditambahkan!");
                        break;

                    case 2:
                        System.out.print("Masukkan NIM Mahasiswa yang ingin dihapus: ");
                        String targetNim = scan.nextLine();
                        boolean ditemukan = false;

                        Iterator<User> it_user = loginSystem.userList.iterator();
                        while (it_user.hasNext()) {
                            User user = it_user.next();
                            if (user instanceof Mahasiswa && user.getNim().equals(targetNim)) {
                                it_user.remove();
                                ditemukan = true;
                                System.out.println("Mahasiswa dengan NIM " + targetNim + " berhasil dihapus.");
                                break;
                            }
                        }

                        if (!ditemukan) {
                            System.out.println("Mahasiswa dengan NIM tersebut tidak ditemukan.");
                        }

                        break;

                    case 3:
                        loop = false;
                        break;

                    default:
                        System.out.println("Pilihan tidak valid.");
                }

            } catch (InputMismatchException e) {
                System.out.println("ERROR: Input harus berupa angka!");
                scan.nextLine();
            }
        }
    }

    public void manageItems() {
        boolean loop = true;
        int pilihan = 1;
        while (loop) {
            try {
                System.out.println("\n1. Lihat Semua Laporan\n2. Tandai Barang Yang Diambil\n3. Kembali");
                System.out.print("Masukkan pilihan: ");
                pilihan = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Hanya Masukkan Angka!!");
                scan.nextLine();
                continue;
            }

            switch (pilihan) {
                case 1:
                    Iterator<Item> it_item = loginSystem.reportedItem.iterator();

                    if (it_item.hasNext()) {
                        int index = 1;
                        System.out.printf("%-5s %-25s %-40s %-30s %-30s\n", "NO", "NAMA", "DESKRIPSI", "LOKASI",
                                "STATUS");
                        while (it_item.hasNext()) {
                            Item barang = it_item.next();
                            System.out.printf("%-5d %-25s %-40s %-30s %-30s\n", index++, barang.getItemName(),
                                    barang.getDescription(), barang.getLocation(), barang.getStatus());
                        }
                    } else {
                        System.out.println("Tidak ada barang...");
                    }

                    break;
                case 2:
                    Iterator<Item> iterator = loginSystem.reportedItem.iterator();
                    int index = 0;
                    boolean adaReported = false;

                    System.out.printf("%-5s %-25s %-40s %-30s %-30s\n", "NO", "NAMA", "DESKRIPSI", "LOKASI", "STATUS");
                    while (iterator.hasNext()) {
                        Item barang = loginSystem.reportedItem.get(index);
                        if ("Reported".equalsIgnoreCase(barang.getStatus())) {
                            System.out.printf("%-5d %-25s %-40s %-30s %-30s\n", index, barang.getItemName(),
                                    barang.getDescription(), barang.getLocation(), barang.getStatus());
                            adaReported = true;
                        }
                        index++;
                        iterator.next();
                    }

                    if (!adaReported) {
                        System.out.println("Tidak ada barang yang masih berstatus 'Reported'.");
                        break;
                    }

                    try {
                        System.out.print("Masukkan nomor indeks barang yang ingin ditandai: ");
                        int inputIndex = scan.nextInt();
                        scan.nextLine();

                        Item barangDipilih = loginSystem.reportedItem.get(inputIndex);

                        if ("Claimed".equalsIgnoreCase(barangDipilih.getStatus())) {
                            System.out.println("Barang sudah ditandai sebagai 'Claimed'.");
                        } else {
                            barangDipilih.setStatus("Claimed");
                            System.out.println("Barang berhasil ditandai sebagai 'Claimed'!");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("ERROR: Input harus berupa angka!");
                        scan.nextLine();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("ERROR: Indeks tidak valid. Pastikan angka sesuai daftar!");
                    }

                    break;
                case 3:
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

}
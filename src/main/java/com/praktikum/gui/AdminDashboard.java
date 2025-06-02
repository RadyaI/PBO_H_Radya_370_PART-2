package com.praktikum.gui;

import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;
import com.praktikum.data.Item;
import com.praktikum.main.loginSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class AdminDashboard extends VBox {
    private Admin admin;

    private VBox mahasiswaBox;
    private VBox barangBox;

    public AdminDashboard(Stage stage) {
        if (loginSystem.currentUser instanceof Admin) {
            admin = (Admin) loginSystem.currentUser;
        } else {
            System.err.println("User bukan Admin!");
            return;
        }

        this.setPadding(new Insets(20));
        this.setSpacing(20);

        Label title = new Label("Dashboard Admin");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));

        HBox mainContent = new HBox(50);
        mainContent.setPadding(new Insets(10));

        mahasiswaBox = new VBox(10);
        barangBox = new VBox(10);

        Button logOut = new Button("Logout");
        logOut.setOnAction(e -> {
            LoginPane loginPane = new LoginPane(stage);
            stage.setScene(new Scene(loginPane, 600, 400));
        });

        mainContent.getChildren().addAll(mahasiswaBox, barangBox);

        this.getChildren().addAll(title, mainContent, logOut);

        setupKelolaMahasiswa();
        setupKelolaBarang();
    }

    private void setupKelolaMahasiswa() {
        mahasiswaBox.getChildren().clear();

        Label label = new Label("Daftar Mahasiswa");
        label.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));

        ObservableList<Mahasiswa> data = FXCollections.observableArrayList(admin.getDaftarMahasiswa());

        TableView<Mahasiswa> table = new TableView<>();
        table.setItems(data);
        table.setPrefHeight(300);
        table.setPrefWidth(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Mahasiswa, String> namaCol = new TableColumn<>("Nama");
        namaCol.setCellValueFactory(
                cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNama()));

        TableColumn<Mahasiswa, String> nimCol = new TableColumn<>("NIM");
        nimCol.setCellValueFactory(
                cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNim()));

        table.getColumns().addAll(namaCol, nimCol);

        TextField namaField = new TextField();
        namaField.setPromptText("Nama Mahasiswa");

        TextField nimField = new TextField();
        nimField.setPromptText("NIM Mahasiswa");

        Button tambahBtn = new Button("Tambah Mahasiswa");
        tambahBtn.setOnAction(e -> {
            String nama = namaField.getText();
            String nim = nimField.getText();
            if (!nama.isEmpty() && !nim.isEmpty()) {
                admin.tambahMahasiswa(nama, nim);
                table.setItems(FXCollections.observableArrayList(admin.getDaftarMahasiswa()));
                namaField.clear();
                nimField.clear();
            }
        });

        Button hapusBtn = new Button("Hapus Mahasiswa Terpilih");
        hapusBtn.setOnAction(e -> {
            Mahasiswa mhs = table.getSelectionModel().getSelectedItem();
            if (mhs != null) {
                admin.hapusMahasiswa(mhs.getNim());
                table.setItems(FXCollections.observableArrayList(admin.getDaftarMahasiswa()));
            }
        });

        mahasiswaBox.getChildren().addAll(label, table, namaField, nimField, tambahBtn, hapusBtn);
    }

    private void setupKelolaBarang() {
        barangBox.getChildren().clear();

        Label label = new Label("Daftar Barang Hilang");
        label.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));

        ObservableList<Item> data = FXCollections.observableArrayList(admin.getDaftarBarang());

        TableView<Item> table = new TableView<>();
        table.setItems(data);
        table.setPrefHeight(300);
        table.setPrefWidth(500);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Item, String> namaCol = new TableColumn<>("Nama");
        namaCol.setCellValueFactory(
                cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getItemName()));

        TableColumn<Item, String> lokasiCol = new TableColumn<>("Lokasi");
        lokasiCol.setCellValueFactory(
                cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getLocation()));

        TableColumn<Item, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(
                cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getStatus()));

        table.getColumns().addAll(namaCol, lokasiCol, statusCol);

        Button tandaiBtn = new Button("Tandai Sebagai Claimed");
        tandaiBtn.setOnAction(e -> {
            Item dipilih = table.getSelectionModel().getSelectedItem();
            if (dipilih != null) {
                admin.tandaiBarangClaimed(dipilih);
                table.setItems(FXCollections.observableArrayList(admin.getDaftarBarang()));
            }
        });

        barangBox.getChildren().addAll(label, table, tandaiBtn);
    }

}

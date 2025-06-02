package com.praktikum.gui;

import com.praktikum.data.Item;
import com.praktikum.main.loginSystem;
import com.praktikum.users.Mahasiswa;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MahasiswaDashboard extends VBox {

    private TableView<Item> table;
    private Mahasiswa currentMahasiswa;

    public MahasiswaDashboard(Stage stage) {
        if (loginSystem.currentUser instanceof Mahasiswa) {
            currentMahasiswa = (Mahasiswa) loginSystem.currentUser;
        } else {
            System.err.println("User bukan Mahasiswa!");
            return;
        }

        setPadding(new Insets(20));
        setSpacing(10);

        Label welcomeLabel = new Label("Selamat datang, " + currentMahasiswa.getNama());
        welcomeLabel.setFont(new Font(14));

        Label instructionLabel = new Label("Laporkan Barang Hilang/Temuan");

        TextField namaBarangField = new TextField();
        namaBarangField.setPromptText("Nama Barang");

        TextField deskripsiField = new TextField();
        deskripsiField.setPromptText("Deskripsi");

        TextField lokasiField = new TextField();
        lokasiField.setPromptText("Lokasi");

        Button laporkanBtn = new Button("Laporkan");

        HBox inputForm = new HBox(10, namaBarangField, deskripsiField, lokasiField, laporkanBtn);

        Label daftarLabel = new Label("Daftar Laporan Anda");

        table = new TableView<>();
        TableColumn<Item, String> namaCol = new TableColumn<>("Nama");
        namaCol.setCellValueFactory(
                data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getItemName()));

        TableColumn<Item, String> lokasiCol = new TableColumn<>("Lokasi");
        lokasiCol.setCellValueFactory(
                data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getLocation()));

        table.getColumns().addAll(namaCol, lokasiCol);
        table.setItems(FXCollections.observableArrayList(currentMahasiswa.getReportedItems()));
        table.setPrefHeight(200);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Button logoutBtn = new Button("Logout");
        logoutBtn.setOnAction(e -> {
            LoginPane loginPane = new LoginPane(stage);
            stage.setScene(new Scene(loginPane, 600, 400));
        });

        laporkanBtn.setOnAction(e -> {
            String nama = namaBarangField.getText();
            String deskripsi = deskripsiField.getText();
            String lokasi = lokasiField.getText();

            if (!nama.isEmpty() && !deskripsi.isEmpty() && !lokasi.isEmpty()) {
                currentMahasiswa.reportItem(nama, deskripsi, lokasi);
                table.getItems().add(new Item(nama, deskripsi, lokasi));

                namaBarangField.clear();
                deskripsiField.clear();
                lokasiField.clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Semua field harus diisi!", ButtonType.OK);
                alert.showAndWait();
            }
        });

        getChildren().addAll(welcomeLabel, instructionLabel, inputForm, daftarLabel, table, logoutBtn);
    }
}

package com.praktikum.gui;

import com.praktikum.data.Item;
import com.praktikum.main.loginSystem;
import com.praktikum.users.Mahasiswa;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
        setSpacing(20);
        setStyle("-fx-background-color: #0B192C;");

        Label welcomeLabel = new Label("Selamat datang, " + currentMahasiswa.getNama());
        welcomeLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD , 24));
        welcomeLabel.setTextFill(Color.WHITE);

        Label instructionLabel = new Label("Laporkan Barang Hilang/Temuan");
        instructionLabel.setFont(Font.font("Segoe UI", 14));
        instructionLabel.setTextFill(Color.LIGHTGRAY);

        TextField namaBarangField = new TextField();
        namaBarangField.setPromptText("Nama Barang");
        styleTextField(namaBarangField);

        TextField deskripsiField = new TextField();
        deskripsiField.setPromptText("Deskripsi");
        styleTextField(deskripsiField);

        TextField lokasiField = new TextField();
        lokasiField.setPromptText("Lokasi");
        styleTextField(lokasiField);

        Button laporkanBtn = new Button("Laporkan");
        stylePrimaryButton(laporkanBtn);

        HBox inputForm = new HBox(10, namaBarangField, deskripsiField, lokasiField, laporkanBtn);
        inputForm.setAlignment(Pos.CENTER_LEFT);

        Label daftarLabel = new Label("Daftar Laporan Anda");
        daftarLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        daftarLabel.setTextFill(Color.LIGHTGRAY);

        table = new TableView<>();
        TableColumn<Item, String> namaCol = new TableColumn<>("Nama");
        namaCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getItemName()));

        TableColumn<Item, String> lokasiCol = new TableColumn<>("Lokasi");
        lokasiCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getLocation()));

        table.getColumns().addAll(namaCol, lokasiCol);
        table.setItems(FXCollections.observableArrayList(currentMahasiswa.getReportedItems()));
        table.setPrefHeight(200);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setStyle("-fx-background-color: #1E3E62; -fx-text-fill: white; -fx-font-size: 13px;");

        Button logoutBtn = new Button("Logout");
        styleDangerButton(logoutBtn);
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

    private void styleTextField(TextField tf) {
        tf.setStyle(
            "-fx-background-color: #1E3E62;" +
            "-fx-text-fill: white;" +
            "-fx-prompt-text-fill: #BBBBBB;" +
            "-fx-border-color: #334155;" +
            "-fx-border-radius: 8;" +
            "-fx-background-radius: 8;" +
            "-fx-padding: 8 12;"
        );
        tf.setFont(Font.font("Segoe UI", 13));
        tf.setPrefWidth(150);
    }

    private void stylePrimaryButton(Button btn) {
        btn.setStyle(
            "-fx-background-color: #FF6500;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8;" +
            "-fx-padding: 8 14;"
        );
        btn.setFont(Font.font("Segoe UI", 13));
    }

    private void styleDangerButton(Button btn) {
        btn.setStyle(
            "-fx-background-color: #EF4444;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8;" +
            "-fx-padding: 8 14;"
        );
        btn.setFont(Font.font("Segoe UI", 13));
    }
}

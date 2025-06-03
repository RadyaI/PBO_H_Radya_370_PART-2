package com.praktikum.gui;

import com.praktikum.data.DataStore;
import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
    
public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        DataStore.userList.add(new Admin("Admin", "Admin370"));
        DataStore.userList.add(new Mahasiswa("Radya", "370"));

        LoginPane loginPane = new LoginPane(stage);
        Scene scene = new Scene(loginPane, 600, 400);

        stage.setTitle("Sistem Barang Hilang");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

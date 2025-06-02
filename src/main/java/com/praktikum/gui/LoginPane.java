package com.praktikum.gui;

import com.praktikum.main.loginSystem;
import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;
import com.praktikum.users.User;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LoginPane extends VBox {
    public LoginPane(Stage stage) {
        Label title = new Label("Login Sistem Lost & Found");
        title.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 28));

        ComboBox<String> loginType = new ComboBox<>();
        loginType.getItems().addAll("Mahasiswa", "Admin");
        loginType.setPromptText("Pilih Login");
        loginType.setPrefWidth(280);
        loginType.setStyle(
                "-fx-background-radius: 12; -fx-border-radius: 12; -fx-border-color: #d1d5db; -fx-border-width: 1; -fx-padding: 8 12 8 12; -fx-font-size: 14px; -fx-font-family: Segoe UI; -fx-text-fill: #374151; -fx-background-color: white; ");

        TextField username = new TextField();
        username.setPromptText("Username");
        username.setPrefWidth(280);
        username.setStyle(
                "-fx-background-radius: 12; -fx-border-radius: 12; -fx-border-color: #d1d5db; -fx-border-width: 1; -fx-padding: 8 12 8 12; -fx-font-size: 14px; -fx-font-family: Segoe UI; -fx-text-fill: #374151; -fx-background-color: white;");

        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        password.setPrefWidth(280);
        password.setStyle(username.getStyle());

        Button login = new Button("Login");
        login.setPrefWidth(280);
        login.setStyle(
                "-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-weight: 600; -fx-font-family: Segoe UI; -fx-font-size: 16px; -fx-background-radius: 12; -fx-cursor: hand;");

        login.setOnAction(e -> {
            User tryLogin = loginSystem.doLogin(username.getText(), password.getText());

            if (tryLogin == null) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("User tidak ditemukan");
                alert.show();
            } else {
                loginSystem.currentUser = tryLogin;

                if (tryLogin instanceof Admin && "Admin".equals(loginType.getValue())) {
                    stage.setScene(new Scene(new AdminDashboard(stage), 800, 600));

                } else if (tryLogin instanceof Mahasiswa && "Mahasiswa".equals(loginType.getValue())) {
                    stage.setScene(new Scene(new MahasiswaDashboard(stage), 800, 600));

                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Login Invalid");
                    alert.show();
                }
            }
        });

        this.getChildren().addAll(title, loginType, username, password, login);
        this.setSpacing(20);
        this.setPadding(new Insets(40));
        this.setStyle(
                "-fx-background-color: #f9fafb; -fx-alignment: center; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.07), 10, 0, 0, 2);");
        this.setMaxWidth(320);
    }
}

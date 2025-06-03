package com.praktikum.gui;

import com.praktikum.main.loginSystem;
import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;
import com.praktikum.users.User;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LoginPane extends VBox {
    public LoginPane(Stage stage) {
        Label title = new Label("Login Sistem Lost & Found");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        title.setTextFill(Color.web("#FFFFFF"));

        ComboBox<String> loginType = new ComboBox<>();
        loginType.getItems().addAll("Mahasiswa", "Admin");
        loginType.setPromptText("Pilih Login");
        loginType.setPrefWidth(320);
        loginType.setStyle(
                "-fx-background-radius: 10;" +
                "-fx-border-radius: 10;" +
                "-fx-border-color: #334155;" +
                "-fx-border-width: 1;" +
                "-fx-font-size: 14px;" +
                "-fx-font-family: 'Segoe UI', sans-serif;" +
                "-fx-text-fill: #FFFFFF;" +
                "-fx-background-color: #1E3E62;" +
                "-fx-padding: 10 14;"
        );

        String inputStyle = "-fx-background-radius: 10;" +
                "-fx-border-radius: 10;" +
                "-fx-border-color: #334155;" +
                "-fx-border-width: 1;" +
                "-fx-font-size: 14px;" +
                "-fx-font-family: 'Segoe UI', sans-serif;" +
                "-fx-text-fill: #FFFFFF;" +
                "-fx-background-color: #1E3E62;" +
                "-fx-padding: 10 14;";

        TextField username = new TextField();
        username.setPromptText("Username");
        username.setPrefWidth(320);
        username.setStyle(inputStyle);

        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        password.setPrefWidth(320);
        password.setStyle(inputStyle);

        Button login = new Button("Login");
        login.setPrefWidth(320);
        login.setStyle(
                "-fx-background-color: #FF6500;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 15px;" +
                "-fx-font-family: 'Segoe UI', sans-serif;" +
                "-fx-background-radius: 10;" +
                "-fx-padding: 12 16;" +
                "-fx-cursor: hand;"
        );

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
                    stage.setScene(new Scene(new AdminDashboard(stage), 1000, 600));
                } else if (tryLogin instanceof Mahasiswa && "Mahasiswa".equals(loginType.getValue())) {
                    stage.setScene(new Scene(new MahasiswaDashboard(stage), 1000, 600));
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Login Invalid");
                    alert.show();
                }
            }
        });

        this.setSpacing(20);
        this.setPadding(new Insets(50));
        this.setAlignment(Pos.CENTER);
        this.setStyle(
                "-fx-background-color: #0B192C;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 12, 0.1, 0, 4);"
        );
        this.setMaxWidth(360);

        this.getChildren().addAll(title, loginType, username, password, login);
    }
}

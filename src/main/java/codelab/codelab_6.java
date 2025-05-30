package codelab;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class codelab_6 extends Application {
    String status = "Start";
    int kesempatan = 5;
    int angkaRandom;

    Label title = new Label("🎯 Tebak Angka 1-100");
    HBox titleBox = new HBox();

    TextField tebakInput = new TextField();
    Button actionBtn = new Button(status);

    Label guide = new Label("Masukkan Tebakanmu!");
    Label infoTebakan = new Label();
    Label sisaKesempatan = new Label();

    @Override
    public void start(Stage stage) {
        guide.setStyle("-fx-font-size: 16px;");
        infoTebakan.setStyle("-fx-text-fill: darkorange; -fx-font-size: 15px;");
        title.setStyle("-fx-text-fill: darkblue; -fx-font-size: 25px;");
        titleBox.getChildren().add(title);
        titleBox.setAlignment(Pos.BASELINE_CENTER);

        tebakInput.setVisible(false);
        tebakInput.setManaged(false);
        tebakInput.setPrefWidth(200);
        tebakInput.setStyle("-fx-outline: none; -fx-border: none; -fx-padding: 8px; -fx-font-size: 13;");

        actionBtn.setOnAction(e -> handleAction());
        actionBtn.setStyle(
                "-fx-cursor: pointer; -fx-font-size: 15px; -fx-border: none; -fx-background-color: green; -fx-text-fill: white;");

        HBox input = new HBox(15, tebakInput, actionBtn);
        input.setAlignment(Pos.BASELINE_CENTER);

        HBox infoTebakanBox = new HBox(infoTebakan, guide);
        infoTebakanBox.setAlignment(Pos.BASELINE_CENTER);

        HBox sisaKesempatanBox = new HBox(sisaKesempatan);
        sisaKesempatanBox.setAlignment(Pos.BASELINE_CENTER);

        VBox root = new VBox(15, titleBox, infoTebakanBox, input, sisaKesempatanBox);
        root.setStyle("-fx-background-color:rgb(211, 227, 239);");
        root.setAlignment(Pos.CENTER);

        stage.setScene(new Scene(root, 400, 250));
        stage.setTitle("Tebak Angka Random");
        stage.show();
    }

    void handleAction() {
        if (status.equals("Start")) {
            mulaiGame();
        } else if (status.equals("selesai")) {
            resetGame();
        } else {
            prosesTebakan();
        }
    }

    void mulaiGame() {
        status = "started";
        infoTebakan.setText("");
        actionBtn.setText("🎲 Coba Tebak!");
        tebakInput.setVisible(true);
        tebakInput.setManaged(true);
        angkaRandom = generateAngka();
        guide.setVisible(true);
        guide.setManaged(true);
    }

    void prosesTebakan() {
        guide.setVisible(false);
        guide.setManaged(false);
        int tebakInputValue;
        try {
            tebakInputValue = Integer.parseInt(tebakInput.getText());
        } catch (NumberFormatException ex) {
            showAlert("Input tidak valid!" + angkaRandom);
            return;
        }

        if (tebakInputValue == angkaRandom) {
            String pesan = (kesempatan == 5) ? "✅ FIRST TRY 🔥🔥" : "✅ BENAR!!";
            infoTebakan.setStyle("-fx-text-fill: green;");
            infoTebakan.setText(pesan + " ANGKANYA: " + angkaRandom);
            status = "selesai";
            actionBtn.setText("Kembali");
        } else {
            kesempatan--;
            sisaKesempatan.setText("Sisa Percobaan: " + kesempatan);

            if (kesempatan == 0) {
                showAlert("Kesempatan Sudah Habis... Angkanya adalah " + angkaRandom);
                resetGame();
            } else {
                infoTebakan.setText(tebakInputValue > angkaRandom ? "🔺 Terlalu Besar!" : "🔻 Terlalu Kecil!");
            }
        }
    }

    void resetGame() {
        status = "Start";
        actionBtn.setText("Start");
        tebakInput.setVisible(false);
        tebakInput.setManaged(false);
        kesempatan = 5;
        infoTebakan.setText("");
        sisaKesempatan.setText("");
        tebakInput.setText("");
    }

    int generateAngka() {
        return (int) (Math.random() * 100);
    }

    void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
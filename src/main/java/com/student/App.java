package com.student;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        VBox layout = new VBox(10);
        Scene scene = new Scene(layout, 400, 200);
        stage.setScene(scene);
        stage.setTitle("JavaFx");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

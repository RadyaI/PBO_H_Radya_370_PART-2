module com.praktikum {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.praktikum to javafx.fxml;
    exports com.praktikum;
    // exports com.praktikum.actions;
    exports codelab;
}
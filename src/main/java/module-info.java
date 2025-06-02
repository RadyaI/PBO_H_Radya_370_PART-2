module com.praktikum {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.praktikum to javafx.fxml;
    exports com.praktikum;
    exports com.praktikum.actions;
    exports com.praktikum.gui;
    exports com.praktikum.data;
    exports com.praktikum.users;
    exports com.praktikum.main;
    exports codelab;
}
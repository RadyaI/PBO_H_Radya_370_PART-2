module com.student {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.student to javafx.fxml;
    exports com.student;
    exports codelab;
}
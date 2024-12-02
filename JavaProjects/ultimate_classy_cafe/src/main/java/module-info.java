module classy_code {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires com.google.gson;

    opens classy_code to javafx.fxml;
    opens classy_code.controller to javafx.fxml;
    exports classy_code;
    exports classy_code.controller;
}

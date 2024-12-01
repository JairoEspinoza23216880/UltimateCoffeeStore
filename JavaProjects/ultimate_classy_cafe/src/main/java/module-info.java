module classy_code {
    requires javafx.controls;
    requires javafx.fxml;

    opens classy_code to javafx.fxml;
    exports classy_code;
}

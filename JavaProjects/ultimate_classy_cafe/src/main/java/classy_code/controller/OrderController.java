package classy_code.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class OrderController extends ListNInfoController {
    @FXML private Button menuButton;
    @FXML private Button orderButton;
    @FXML private Button ingredientButton;
    @FXML private Button saleButton;
    @FXML private Button customerButton;
    @FXML private Button tableButton;
    @FXML private Button reportButton;

    @FXML private Button exitButton;
    @FXML private Button addButton;

    @FXML private Label titleLabel;

    @Override
    public void initialize() {
        System.out.println("OrderController");
    }

}

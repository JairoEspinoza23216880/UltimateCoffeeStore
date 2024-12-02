package classy_code.controller;

import classy_code.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class BasicController {
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

    public void initialize() {
        System.out.println("BasicController");
    }

    @FXML
    public void toMenu() throws Exception {
        App.setRoot("MenuView");
    }

    @FXML
    public void toOrder() throws Exception {
        App.setRoot("OrderView");
    }

    @FXML
    public void toIngredient() throws Exception {
        App.setRoot("IngredientView");
    }

    @FXML
    public void toSale() throws Exception {
        App.setRoot("SaleView");
    }

    @FXML
    public void toCustomer() throws Exception {
        App.setRoot("CustomerView");
    }

    @FXML
    public void toTable() throws Exception {
        App.setRoot("TableView");
    }

    @FXML
    public void toReport() throws Exception {
        App.setRoot("ReportView");
    }

    @FXML
    public void toExit() {
        System.exit(0);
    }
}

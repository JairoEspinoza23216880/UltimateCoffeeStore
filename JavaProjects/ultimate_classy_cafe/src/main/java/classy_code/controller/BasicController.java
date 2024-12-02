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

    /*
     * metodo initialize
     * inicializa el controlador
     */
    public void initialize() {
        System.out.println("BasicController initialized");
    }

    /*
     * metodo toMenu
     * cambia la vista a MenuView
     */
    @FXML
    public void toMenu() throws Exception {
        App.setRoot("MenuView", App.menuController);
    }

    /*
     * metodo toOrder
     * cambia la vista a OrderView
     */
    @FXML
    public void toOrder() throws Exception {
        App.setRoot("OrderView",App.orderController);
    }

    /*
     * metodo toIngredient
     * cambia la vista a IngredientView
     */
    @FXML
    public void toIngredient() throws Exception {
        App.setRoot("IngredientView", App.ingredientController);
    }

    /*
     * metodo toSale
     * cambia la vista a SaleView
     */
    @FXML
    public void toSale() throws Exception {
        App.setRoot("SaleView", App.saleController);
    }

    /*
     * metodo toCustomer
     * cambia la vista a CustomerView
     */
    @FXML
    public void toCustomer() throws Exception {
        App.setRoot("CustomerView", App.customerController);
    }

    /*
     * metodo toTable
     * cambia la vista a TableView
     */
    @FXML
    public void toTable() throws Exception {
        App.setRoot("TableView", App.tableController);
    }

    /*
     * metodo toReport
     * cambia la vista a ReportView
     */
    @FXML
    public void toReport() throws Exception {
        App.setRoot("ReportView", App.reportController);
    }

    /*
     * metodo toBasic
     * cambia la vista a BasicView
     */
    @FXML
    public void toBasic() throws Exception {
        App.setRoot("BasicView", App.basicController);
    }
}

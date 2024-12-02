package classy_code.controller;

import java.util.stream.Collectors;

import classy_code.App;
import classy_code.model.order.OrderWarehouse;
import classy_code.model.order.TableOrder;
import classy_code.model.customer.Customer;
import classy_code.model.order.ChairOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

public class OrderController extends ListNInfoController {
    @FXML private Button exitButton;
    @FXML private Button addButton;

    @FXML private Label titleLabel;
    @FXML private VBox orderContainer;
    @FXML private VBox infoContainer;

    private OrderWarehouse model;
    @FXML ObservableList<TableOrder> orderList;

    //Constructor
    /*
     * Constructor de la clase OrderController
     * Inicializa el modelo y la lista de ordenes
     * @param void
     */
    public OrderController() {
        System.out.println("OrderController created");
        model = new OrderWarehouse();
        orderList = FXCollections.observableArrayList();
    }

    //Metodos
    /*
     * Metodo initialize
     * Inicializa el controlador
     * @param void
     * @return void
     */
    @Override
    public void initialize() {
        System.out.println("OrderController");
        addButton.setOnAction(event -> showAddOrderPopup());
        updateOrderContainer();
    }

    /*
     * Metodo getModel
     * Devuelve el modelo
     */
    @SuppressWarnings("exports")
    public OrderWarehouse getModel() {
        return model;
    }

    /*
     * Metodo showAddOrderPopup
     * Muestra un popup para agregar una orden
     */
    private void showAddOrderPopup() {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initStyle(StageStyle.UTILITY);

        VBox popupVBox = new VBox(10);
        popupVBox.setStyle("-fx-padding: 10;");

        ComboBox<String> tableComboBox = new ComboBox<>();
        ObservableList<String> tableNames = FXCollections.observableArrayList(
            App.tableController.getModel().getTableList().stream()
            .map(t -> String.valueOf(t.getId())) // Assuming Table has a getId() method
            .collect(Collectors.toList())
        );
        tableComboBox.setItems(tableNames);

        ComboBox<String> customerComboBox = new ComboBox<>();
        ObservableList<String> customerNames = FXCollections.observableArrayList(
            App.customerController.getModel().getCustomerList().stream()
            .map(c -> c.getCustomer_name()) // Ensure Customer has a getName() method
            .collect(Collectors.toList())
        );
        customerComboBox.setItems(customerNames);

        Button addButton = new Button("Añadir");
        addButton.setOnAction(event -> {
            String selectedTableId = tableComboBox.getSelectionModel().getSelectedItem();
            String selectedCustomerName = customerComboBox.getSelectionModel().getSelectedItem();
            if (selectedTableId != null && selectedCustomerName != null) {
                int tableId = Integer.parseInt(selectedTableId);
                Customer selectedCustomer = App.customerController.getModel().getCustomerList().stream()
                    .filter(c -> c.getCustomer_name().equals(selectedCustomerName))
                    .findFirst()
                    .orElse(null);
                if (selectedCustomer != null) {
                    TableOrder newOrder = new TableOrder(tableId, selectedCustomer); // Assuming TableOrder has this constructor
                    orderList.add(newOrder);
                    updateOrderContainer();
                    popupStage.close();
                }
            }
        });

        popupVBox.getChildren().addAll(new Label("Seleccionar Mesa:"), tableComboBox, new Label("Seleccionar Cliente:"), customerComboBox, addButton);

        Scene scene = new Scene(popupVBox);
        popupStage.setScene(scene);
        popupStage.showAndWait();
    }

    /*
     * Metodo updateOrderContainer
     * Actualiza el contenedor de ordenes
     */
    private void updateOrderContainer() {
        orderContainer.getChildren().clear();
        for (TableOrder order : orderList) {
            HBox orderHBox = createOrderHBox(order);
            orderContainer.getChildren().add(orderHBox);
        }
    }

    /*
     * Metodo createOrderHBox
     * Crea un HBox con la informacion de una orden
     * @param TableOrder order
     * @return HBox
     */
    private HBox createOrderHBox(TableOrder order) {
        HBox orderHBox = new HBox(10);
        orderHBox.setStyle("-fx-padding: 10; -fx-background-color: #f0f0f0;");

        Label tableLabel = new Label("Mesa: " + order.getTableId());
        Label customerLabel = new Label("Cliente: " + order.getCustomer().getCustomer_name());
        Label priceLabel = new Label("Precio: " + order.getPrice_total());

        Button deleteButton = new Button("Eliminar");
        deleteButton.setOnAction(event -> {
            orderList.remove(order);
            updateOrderContainer();
        });

        Button editButton = new Button("Editar");
        editButton.setOnAction(event -> showEditOrderPopup(order));

        Button payButton = new Button("Pagar");
        payButton.setOnAction(event -> System.out.println("Pagos no Disponibles"));

        orderHBox.getChildren().addAll(tableLabel, customerLabel, priceLabel, deleteButton, editButton, payButton);

        orderHBox.setOnMouseEntered(event -> {
            orderHBox.setStyle("-fx-padding: 10; -fx-background-color: #f0f0f0; -fx-border-color: blue;");
            deleteButton.setVisible(true);
            editButton.setVisible(true);
            payButton.setVisible(true);
            showOrderInfo(order);
        });

        orderHBox.setOnMouseExited(event -> {
            orderHBox.setStyle("-fx-padding: 10; -fx-background-color: #f0f0f0;");
            deleteButton.setVisible(false);
            editButton.setVisible(false);
            payButton.setVisible(false);
            infoContainer.getChildren().clear();
        });

        deleteButton.setVisible(false);
        editButton.setVisible(false);
        payButton.setVisible(false);

        return orderHBox;
    }

    /*
     * Metodo showOrderInfo
     * Muestra la informacion de una orden
     * @param TableOrder order
     */
    private void showOrderInfo(TableOrder order) {
        infoContainer.getChildren().clear();
        Label customerLabel = new Label("Cliente: " + order.getCustomer().getCustomer_name());
        Label tableLabel = new Label("Mesa ID: " + order.getTableId());
        infoContainer.getChildren().addAll(customerLabel, tableLabel);

        for (ChairOrder chairOrder : order.getOrder_list()) {
            VBox chairVBox = new VBox(5);
            int tableId = order.getTableId();
            App.tableController.getModel().getTableList().stream()
                .filter(t -> t.getId() == tableId)
                .findFirst()
                .ifPresent(table -> {
                    table.getChairList().forEach(chair -> {
                        Label chairLabel = new Label("Silla ID: " + chair.getId());
                        Button addOrderButton = new Button("Agregar Pedido");
                        addOrderButton.setOnAction(event -> showAddItemPopup(chairVBox, chairOrder));
                        chairVBox.getChildren().addAll(chairLabel, addOrderButton);
                    });
                });
            Label chairLabel = new Label("Silla ID: " + chairOrder.getChair().getId());
            Button addOrderButton = new Button("Agregar Pedido");
            addOrderButton.setOnAction(event -> showAddItemPopup(chairVBox, chairOrder));
            chairVBox.getChildren().addAll(chairLabel, addOrderButton);
            infoContainer.getChildren().add(chairVBox);
        }
    }

    /*
     * Metodo showAddItemPopup
     * Muestra un popup para agregar un item a una orden
     * @param VBox chairVBox, ChairOrder chairOrder
     */
    private void showAddItemPopup(VBox chairVBox, ChairOrder chairOrder) {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initStyle(StageStyle.UTILITY);

        VBox popupVBox = new VBox(10);
        popupVBox.setStyle("-fx-padding: 10;");

        ComboBox<String> foodCategoryComboBox = new ComboBox<>();
        ComboBox<String> drinkCategoryComboBox = new ComboBox<>();
        ComboBox<String> foodComboBox = new ComboBox<>();
        ComboBox<String> drinkComboBox = new ComboBox<>();

        // Populate food and drink categories
        foodCategoryComboBox.setItems(FXCollections.observableArrayList("Entradas", "Platos Principales", "Postres"));
        drinkCategoryComboBox.setItems(FXCollections.observableArrayList("Refrescos", "Vinos", "Cervezas"));

        foodCategoryComboBox.setOnAction(event -> {
            // Populate foodComboBox based on selected category
            String selectedCategory = foodCategoryComboBox.getSelectionModel().getSelectedItem();
            foodComboBox.setItems(App.menuController.getFoodsByCategory(selectedCategory));
        });

        drinkCategoryComboBox.setOnAction(event -> {
            // Populate drinkComboBox based on selected category
            String selectedCategory = drinkCategoryComboBox.getSelectionModel().getSelectedItem();
            drinkComboBox.setItems(App.menuController.getDrinksByCategory(selectedCategory));
        });

        Button addButton = new Button("Añadir");
        addButton.setOnAction(event -> {
            String selectedFood = foodComboBox.getSelectionModel().getSelectedItem();
            String selectedDrink = drinkComboBox.getSelectionModel().getSelectedItem();
            if (selectedFood != null) {
                chairOrder.getFoodList().add(App.menuController.getFoodByName(selectedFood));
                chairVBox.getChildren().add(new Label("Comida: " + selectedFood));
            }
            if (selectedDrink != null) {
                chairOrder.getDrinkList().add(App.menuController.getDrinkByName(selectedDrink));
                chairVBox.getChildren().add(new Label("Bebida: " + selectedDrink));
            }
            popupStage.close();
        });

        popupVBox.getChildren().addAll(new Label("Seleccionar Categoría de Comida:"), foodCategoryComboBox, new Label("Seleccionar Comida:"), foodComboBox, new Label("Seleccionar Categoría de Bebida:"), drinkCategoryComboBox, new Label("Seleccionar Bebida:"), drinkComboBox, addButton);

        Scene scene = new Scene(popupVBox);
        popupStage.setScene(scene);
        popupStage.showAndWait();
    }

    /*
     * Metodo showEditOrderPopup
     * Muestra un popup para editar una orden
     * @param TableOrder order
     */
    private void showEditOrderPopup(TableOrder order) {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initStyle(StageStyle.UTILITY);

        VBox popupVBox = new VBox(10);
        popupVBox.setStyle("-fx-padding: 10;");

        ComboBox<String> tableComboBox = new ComboBox<>();
        ObservableList<String> tableNames = FXCollections.observableArrayList(
            App.tableController.getModel().getTableList().stream()
            .map(t -> String.valueOf(t.getId())) // Assuming Table has a getId() method
            .collect(Collectors.toList())
        );
        tableComboBox.setItems(tableNames);

        ComboBox<String> customerComboBox = new ComboBox<>();
        ObservableList<String> customerNames = FXCollections.observableArrayList(
            App.customerController.getModel().getCustomerList().stream()
            .map(c -> c.getCustomer_name()) // Ensure Customer has a getName() method
            .collect(Collectors.toList())
        );
        customerComboBox.setItems(customerNames);

        Button editButton = new Button("Editar");
        editButton.setOnAction(event -> {
            String selectedTableId = tableComboBox.getSelectionModel().getSelectedItem();
            String selectedCustomerName = customerComboBox.getSelectionModel().getSelectedItem();
            if (selectedTableId != null && selectedCustomerName != null) {
                int tableId = Integer.parseInt(selectedTableId);
                Customer selectedCustomer = App.customerController.getModel().getCustomerList().stream()
                    .filter(c -> c.getCustomer_name().equals(selectedCustomerName))
                    .findFirst()
                    .orElse(null);
                if (selectedCustomer != null) {
                    order.setTable(tableId);
                    order.setCustomer(selectedCustomer);
                    updateOrderContainer();
                    popupStage.close();
                }
            }
        });

        popupVBox.getChildren().addAll(new Label("Seleccionar Mesa:"), tableComboBox, new Label("Seleccionar Cliente:"), customerComboBox, editButton);

        Scene scene = new Scene(popupVBox);
        popupStage.setScene(scene);
        popupStage.showAndWait();
    }
}

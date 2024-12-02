package classy_code.controller;

import classy_code.App;
import classy_code.model.ingredient.Ingredient;
import classy_code.model.ingredient.IngredientWarehouse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class IngredientController extends ListNInfoController {
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

    @FXML private VBox ingredientContainer;
    @FXML private TextField searchIngredient;

    @FXML private ObservableList<Ingredient> ingredient_list;

    private IngredientWarehouse model;

    @Override
    public void initialize() {
        System.out.println("IngredientWhController");
        model = new IngredientWarehouse();
        ingredient_list = FXCollections.observableArrayList();

        searchIngredient.textProperty().addListener((observable, oldValue, newValue) -> {
            updateIngredientList();
        });
    }

    @FXML
    public void toStart() throws Exception {
        App.setRoot("BasicView");
    }

    public void updateIngredientList() {
        ingredientContainer.getChildren().clear();
        String searchText = searchIngredient.getText().toLowerCase();
        for (Ingredient ingredient : ingredient_list) {
            if (ingredient.getName().toLowerCase().contains(searchText)) {
            //HBOx Contenedor
            HBox hbox = new HBox();
            hbox.setPrefWidth(ingredientContainer.getPrefWidth());
            hbox.setStyle("-fx-background-color: lightgrey; -fx-padding: 10;");

                //Elementos dentro del HBox
                    //Label Nombre
                    Label name = new Label(ingredient.getName());
                    name.setStyle("-fx-font-size: 18px;");
                    //Label Stock
                    Label stock = new Label(ingredient.getStock().toString());
                    stock.setStyle("-fx-font-size: 14px;");
                    //Boton Eliminar
                    Button deleteButton = new Button("Borrar");
                    deleteButton.setOnAction(e -> {
                    model.removeIngredient(ingredient.getName());
                    ingredient_list.remove(ingredient);
                    updateIngredientList();
                });
                        //Boton Editar
                    Button editButton = new Button("Editar");
                    editButton.setOnAction(event -> {
                        Stage popUpStage = new Stage();
                        popUpStage.initModality(Modality.APPLICATION_MODAL);
                        popUpStage.setTitle("Editar Ingrediente");

                        VBox vbox = new VBox(10);
                        vbox.setAlignment(javafx.geometry.Pos.CENTER);

                        Label nameLabel = new Label("Nombre:");
                        TextField nameField = new TextField(ingredient.getName());

                        Label stockLabel = new Label("Stock:");
                        TextField stockField = new TextField(ingredient.getStock().toString());

                        Button saveButton = new Button("Guardar");

                        saveButton.setOnAction(e -> {
                            String newName = nameField.getText();
                            double newStock = Double.parseDouble(stockField.getText());
                            model.findIngredient(ingredient.getName()).setName(newName);
                            model.findIngredient(ingredient.getName()).setStock(newStock);
                            ingredient.setName(newName);
                            ingredient.setStock(newStock);

                            updateIngredientList();
                            popUpStage.close();
                    });

                    vbox.getChildren().addAll(nameLabel, nameField, stockLabel, stockField, saveButton);
                    
                    Scene popUpScene = new Scene(vbox, 200, 200);
                    popUpStage.setScene(popUpScene);
                    popUpStage.showAndWait();
                });

                    //Seleccionador
                    hbox.setOnMouseEntered(e -> {
                        hbox.setStyle("-fx-background-color: lightgrey; -fx-padding: 10; -fx-border-color: blue;");
                        hbox.getChildren().addAll(deleteButton, editButton);
                    });

                    hbox.setOnMouseExited(e -> {
                        hbox.setStyle("-fx-background-color: lightgrey; -fx-padding: 10;");
                        hbox.getChildren().removeAll(deleteButton, editButton);
                    });

                hbox.getChildren().addAll(name, stock);
                ingredientContainer.getChildren().add(hbox);
            }
        }
    }

    @SuppressWarnings("exports")
    public void openAddPopUp(ActionEvent event) {
        Stage popUpStage = new Stage();
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.setTitle("Añadir Ingrediente");

        VBox vbox = new VBox(10);
        vbox.setAlignment(javafx.geometry.Pos.CENTER);

        Label nameLabel = new Label("Nombre:");
        TextField nameField = new TextField();

        Label stockLabel = new Label("Stock:");
        TextField stockField = new TextField();

        Button saveButton = new Button("Guardar");

        saveButton.setOnAction(e -> {
            String name = nameField.getText();
            double stock = Double.parseDouble(stockField.getText());
            //Agregar Acciones
            model.addIngredient(name, stock);
            ingredient_list.add(model.findIngredient(name));
            updateIngredientList();
            popUpStage.close();
        });

        vbox.getChildren().addAll(nameLabel, nameField, stockLabel, stockField, saveButton);
        
        Scene popUpScene = new Scene(vbox, 200, 200);
        popUpStage.setScene(popUpScene);
        popUpStage.showAndWait();
    }

}

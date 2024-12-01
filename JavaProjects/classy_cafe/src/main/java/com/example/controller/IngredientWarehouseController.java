package com.example.controller;

import com.example.model.ingredients.Ingredient;
import com.example.model.ingredients.IngredientWarehouse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class IngredientWarehouseController {
    private IngredientWarehouse model;

    //Barra Lateral
    @FXML private Label classyCafeTitle;
    @FXML private Button menuButton;
    @FXML private Button orderButton;
    @FXML private Button ingredientButton;
    @FXML private Button saleButton;
    @FXML private Button customerButton;
    @FXML private Button tableButton;
    @FXML private Button reportButton;

    //Barra Superior
    @FXML private Label titleLabel;
    @FXML private Button exitButton;

    //Zona de Informacion
    @FXML private TextArea searchBar;
    @FXML private Button addButton;
    @FXML private Button removeButton;
    @FXML private Button editButton;
    @FXML private VBox ingredientContainer;

    //Variables
    private ObservableList<Ingredient> ingredients;
    
    //Constructor
    /*
     * Constructor de la clase BasicController
     * Inicializa el modelo de la aplicacion
     */
    public IngredientWarehouseController() {
        this.model = new IngredientWarehouse();
    }

    //Getters y Setters
    /*
     * Metodo getModel
     * Devuelve el modelo de la aplicacion
     * @return IngredientWarehouse modelo de la aplicacion
     */
    public IngredientWarehouse getModel() {return model;}
    /*
     * Metodo setModel
     * Establece el modelo de la aplicacion
     * @param IngredientWarehouse modelo de la aplicacion
     */
    public void setModel(IngredientWarehouse model) {this.model = model;}

    //Metodos
    /*
     * Metodo initialize
     * Inicializa el controlador
     */
    public void initialize() {
        ingredients = FXCollections.observableArrayList(model.getIngredients_list());
        updateVBox();
        System.out.println("BasicController initialized");
    }

    @FXML
    /*
     * Metodo openAddPopUp
     * Abre una ventana emergente para añadir un ingrediente
     */ 
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
            this.addIngredient(model.findIngredient(name));
            popUpStage.close();
        });

        vbox.getChildren().addAll(nameLabel, nameField, stockLabel, stockField, saveButton);
        
        Scene popUpScene = new Scene(vbox, 200, 200);
        popUpStage.setScene(popUpScene);
        popUpStage.showAndWait();
    }

    /*
     * Metodo updateVBox
     * Actualiza la lista de ingredientes en la interfaz grafica
     */
    private void updateVBox() {
        ingredientContainer.getChildren().clear();
        for (Ingredient ingredient : ingredients) {
            //Creación de HBox
            HBox hbox = new HBox();
            hbox.setPrefWidth(ingredientContainer.getPrefWidth());
            hbox.setStyle("-fx-background-color: lightgrey; -fx-padding: 10;");

            //Objetos dentro del HBox
                //Label del Nombre
                Label name = new Label(ingredient.getName());
                name.setStyle("-fx-font-size: 18px;");
                //Label del Stock
                Label stock = new Label(ingredient.getStock().toString());
                stock.setStyle("-fx-font-size: 14px;");
                //Botón de Borrar
                Button deleteButton = new Button("Borrar");
                //Botón de Editar
                Button editButton = new Button("Editar");

            //Al hacer click en el HBox cambia el color del borde y esconde o muestra los botones
            hbox.setOnMouseClicked(event -> {
                if (hbox.getStyle().contains("-fx-border-color: blue;")) {
                    hbox.setStyle("-fx-background-color: lightgrey; -fx-padding: 10;");
                    hbox.getChildren().removeAll(deleteButton, editButton);
                } else {
                    hbox.setStyle("-fx-background-color: lightgrey; -fx-border-color: blue; -fx-border-width: 2; -fx-padding: 10;");
                    hbox.getChildren().addAll(deleteButton, editButton);
                }
            });

                //Al hacer click en el botón de borrar se elimina el ingrediente
                deleteButton.setOnAction(event -> {
                    model.removeIngredient(ingredient.getName());
                    ingredients.remove(ingredient);
                    updateVBox();
                });

                //Al hacer click en el botón de editar se abre una ventana emergente para editar el ingrediente
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

                        updateVBox();
                        popUpStage.close();
                    });

                    vbox.getChildren().addAll(nameLabel, nameField, stockLabel, stockField, saveButton);
                    
                    Scene popUpScene = new Scene(vbox, 200, 200);
                    popUpStage.setScene(popUpScene);
                    popUpStage.showAndWait();
                });

            

            hbox.getChildren().addAll(name, stock);
            ingredientContainer.getChildren().add(hbox);
        }
    }

    /*
     * Metodo addIngredient
     * Agrega un ingrediente a la lista de ingredientes
     */
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        updateVBox();
    }

    public void openMenu(){
        
    }
}


package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class BasicController {

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
    @FXML private Label title;
    @FXML private Button exitButton;

    //Zona de Informacion
    @FXML private Button addButton;
    @FXML private Button removeButton;
    @FXML private Button editButton;
    
    //Constructor
    /*
     * Constructor de la clase BasicController
     * Inicializa el modelo de la aplicacion
     */
    public BasicController() {
        
    }

    //Metodos
    /*
     * Metodo initialize
     * Inicializa el controlador
     */
    public void initialize() {
        System.out.println("BasicController initialized");
    }
}

package classy_code.controller;

import classy_code.model.table.Chair;
import classy_code.model.table.Table;
import classy_code.model.table.TableWarehouse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TableController extends ListNInfoController {
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

    @FXML private Label infoTitleLabel;
    @FXML private VBox tableContainer;
    @FXML private VBox infoContainer;

    @FXML private ObservableList<Table> table_list;

    private TableWarehouse model;

    //Constructor
    /*
     * Constructor de la clase TableController
     * Inicializa el modelo y la lista de mesas
     * @param void
     */
    public TableController() {
        model = new TableWarehouse();
        table_list = FXCollections.observableArrayList();
    }

    /*
     * Metodo initialize
     * Inicializa el controlador
     */
    public void initialize() {
        System.out.println("TableController initialized");
        updateTableList();
    }

    /*
     * Metodo getModel
     * Devuelve el modelo
     * @param void
     */
    @SuppressWarnings("exports")
    public TableWarehouse getModel() {
        return model;
    }

    /*
     * Metodo updateTableList
     * Actualiza la lista de mesas
     */
    public void updateTableList() {
        tableContainer.getChildren().clear();
        for (Table table : table_list) {
            HBox hbox = new HBox();
            hbox.setPrefWidth(tableContainer.getPrefWidth());
            hbox.setStyle("-fx-background-color: lightgrey; -fx-padding: 10;");

            Label nameLabel = new Label("Mesa " + table.getId());

            Button deleteButton = new Button("Eliminar");
            deleteButton.setOnAction(e -> {
                model.removeTable(table.getId());
                table_list.remove(table);
                updateTableList();
            });
            Button editButton = new Button("Editar");
            editButton.setOnAction(event -> {
                Stage popUpStage = new Stage();
                popUpStage.initModality(Modality.APPLICATION_MODAL);
                popUpStage.setTitle("Editar Mesa");

                VBox vbox = new VBox(10);
                vbox.setAlignment(javafx.geometry.Pos.CENTER);

                Label idLabel = new Label("id:");
                TextField idField = new TextField();

                Label chairsLabel = new Label("Cantidad de Sillas:");
                TextField chairsField = new TextField();
        
                Button saveButton = new Button("Guardar");

                saveButton.setOnAction(e -> {
                    int newid = Integer.parseInt(idField.getText());
                    int chairCount = Integer.parseInt(chairsField.getText());
                    //Agregar Acciones
                    model.findTable(table.getId()).setId(newid);

                    model.findTable(newid).getChairList().clear();
                    this.tableContainer.getChildren().clear();
                    for (int i = 0; i < chairCount; i++) {
                        model.findTable(newid).addChair(new Chair(i+1));
                    }

                    table.setId(newid);
                    updateTableList();
                    popUpStage.close();
                });

                vbox.getChildren().addAll(idLabel, idField, chairsLabel, chairsField, saveButton);

                Scene popUpScene = new Scene(vbox, 200, 200);
                popUpStage.setScene(popUpScene);
                popUpStage.showAndWait();
            });

            //Seleccionador
            hbox.setOnMouseEntered(e -> {
                hbox.setStyle("-fx-background-color: lightgrey; -fx-padding: 10; -fx-border-color: blue;");
                hbox.getChildren().addAll(deleteButton, editButton);
                setScope(table);
            });

            hbox.setOnMouseExited(e -> {
                hbox.setStyle("-fx-background-color: lightgrey; -fx-padding: 10;");
                hbox.getChildren().removeAll(deleteButton, editButton);
                //setScope(null);
            });


            hbox.getChildren().addAll(nameLabel);
            tableContainer.getChildren().add(hbox);            
        }
    }

    /*
     * Metodo openAddPopUp
     * Abre una ventana emergente para añadir una mesa
     */
    @FXML
    public void openAddPopUp() {
        Stage popUpStage = new Stage();
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.setTitle("Añadir Ingrediente");

        VBox vbox = new VBox(10);
        vbox.setAlignment(javafx.geometry.Pos.CENTER);

        Label idLabel = new Label("id:");
        TextField idField = new TextField();

        Label chairsLabel = new Label("Cantidad de Sillas:");
        TextField chairsField = new TextField();

        Button saveButton = new Button("Guardar");
        saveButton.setOnAction(e -> {
            int id = Integer.parseInt(idField.getText());
            int chairCount = Integer.parseInt(chairsField.getText());

            //Agregar Acciones
            model.addTable(new Table(id));
            table_list.add(model.findTable(id));

            for (int i = 0; i < chairCount; i++) {
                model.findTable(id).addChair(new Chair(i+1));
            }

            updateTableList();
            popUpStage.close();
        });

        vbox.getChildren().addAll(idLabel, idField, chairsLabel, chairsField, saveButton);
        
        Scene popUpScene = new Scene(vbox, 200, 200);
        popUpStage.setScene(popUpScene);
        popUpStage.showAndWait();
    }

    /*
     * Metodo setScope
     * Establece el alcance de la mesa
     * @param Table table
     */
    @SuppressWarnings("exports")
    public void setScope(Table table){
        if (table == null) {
            infoTitleLabel.setText("Mesa");
            infoContainer.getChildren().clear();
            return;
        } else {
            infoContainer.getChildren().clear();
            infoTitleLabel.setText("Mesa " + table.getId());
            for (Chair chair : table.getChairList()) {
                Label chairLabel = new Label("Silla " + chair.getId());
                chairLabel.setStyle("-fx-padding: 10; -fx-font-size: 18;");
                infoContainer.getChildren().add(chairLabel);
            }
        }
    }
}

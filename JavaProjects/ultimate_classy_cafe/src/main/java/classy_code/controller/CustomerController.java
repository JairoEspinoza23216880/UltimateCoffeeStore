package classy_code.controller;

import java.util.ArrayList;
import java.util.List;

import classy_code.App;
import classy_code.model.customer.ContactInfo;
import classy_code.model.customer.Customer;
import classy_code.model.customer.CustomerWarehouse;
import classy_code.model.customer.SpoiledProgram;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CustomerController extends ListNInfoController{
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
    @FXML private VBox sprogramContainer;
    @FXML private VBox infoContainer;

    @FXML private ObservableList<SpoiledProgram> sprogram_list;

    private CustomerWarehouse model;
    
    public void initialize() {
        System.out.println("CustomerController initialized");
        model = new CustomerWarehouse();
        sprogram_list = FXCollections.observableArrayList();

        initializeGeneralSpoiledProgram();
        updateSProgramList();
    }

    public void toStart() throws Exception {
        App.setRoot("BasicView");
    }

    private void initializeGeneralSpoiledProgram(){
        SpoiledProgram generalprogram = new SpoiledProgram("General", "Descuento general", 0.0);
        generalprogram.setCustomerList(model.getCustomerList());
        model.addSpoiledProgram(generalprogram);
        sprogram_list.add(generalprogram);
    }

    public void updateSProgramList() {
        sprogramContainer.getChildren().clear();
        for (SpoiledProgram sprogram : sprogram_list) {
            HBox hbox = new HBox();
            hbox.setPrefWidth(sprogramContainer.getPrefWidth());
            hbox.setStyle("-fx-background-color: lightgrey; -fx-padding: 10;");

            Label nameLabel = new Label(sprogram.getProgramName());
            Label benefitLabel = new Label(sprogram.getBenefit());
            Label discountLabel = new Label(Double.toString(sprogram.getDiscount()));

            Button deleteButton = new Button("Eliminar");
                deleteButton.setOnAction(e -> {
                    model.removeSpoiledProgram(sprogram.getProgramName());
                    this.sprogram_list.remove(sprogram);
                    updateSProgramList();
                });

            Button editButton = new Button("Editar");
            editButton.setOnAction(event -> {
                Stage popUpStage = new Stage();
                popUpStage.initModality(Modality.APPLICATION_MODAL);
                popUpStage.initOwner(editButton.getScene().getWindow());
                popUpStage.setTitle("Editar Programa de Beneficios");

                VBox vbox = new VBox();
                vbox.setSpacing(10);
                vbox.setStyle("-fx-padding: 10;");

                Label inNameLabel = new Label("Nombre del Programa:");
                TextField nameField = new TextField(sprogram.getProgramName());

                Label inBenefitLabel = new Label("Beneficio:");
                TextField benefitField = new TextField(sprogram.getBenefit());

                Label inDiscountLabel = new Label("Descuento:");
                TextField discountField = new TextField(Double.toString(sprogram.getDiscount()));

                Button saveButton = new Button("Guardar");
                saveButton.setOnAction(e -> {
                    model.findSpoiledProgram(sprogram.getProgramName()).setProgramName(nameField.getText());
                    model.findSpoiledProgram(sprogram.getProgramName()).setBenefit(benefitField.getText());
                    model.findSpoiledProgram(sprogram.getProgramName()).setDiscount(Double.parseDouble(discountField.getText()));
                    sprogram.setProgramName(nameField.getText());
                    sprogram.setBenefit(benefitField.getText());
                    sprogram.setDiscount(Double.parseDouble(discountField.getText()));
                    updateSProgramList();
                    popUpStage.close();
                });

                vbox.getChildren().addAll(inNameLabel, nameField, inBenefitLabel, benefitField, inDiscountLabel, discountField, saveButton);
                
                Scene scene = new Scene(vbox);
                popUpStage.setScene(scene);
                popUpStage.showAndWait();
            });

            

            hbox.getChildren().addAll(nameLabel, benefitLabel, discountLabel);

            if (sprogram.getProgramName().equals("General")) {
                nameLabel.setStyle("-fx-font-weight: bold;");
                benefitLabel.setStyle("-fx-font-weight: bold;");
                discountLabel.setStyle("-fx-font-weight: bold;");

                hbox.getChildren().removeAll(benefitLabel);
                hbox.getChildren().removeAll(discountLabel);
                hbox.getChildren().removeAll(editButton);
                hbox.getChildren().removeAll(deleteButton);
                //Seleccionador
                hbox.setOnMouseEntered(e -> {
                    clearCustomerList();
                    hbox.setStyle("-fx-background-color: lightgrey; -fx-padding: 10; -fx-border-color: blue;");
                    showCustomerList(sprogram);
                });

                hbox.setOnMouseExited(e -> {
                    hbox.setStyle("-fx-background-color: lightgrey; -fx-padding: 10;");
                });
            } else {
                //Seleccionador
                hbox.setOnMouseEntered(e -> {
                    clearCustomerList();
                    hbox.setStyle("-fx-background-color: lightgrey; -fx-padding: 10; -fx-border-color: blue;");
                    hbox.getChildren().addAll(deleteButton, editButton);
                    showCustomerList(sprogram);
                });

                hbox.setOnMouseExited(e -> {
                    hbox.setStyle("-fx-background-color: lightgrey; -fx-padding: 10;");
                    hbox.getChildren().removeAll(deleteButton, editButton);
                });
            }

            
            sprogramContainer.getChildren().add(hbox);
        }
    }

    public void openAddPopUp() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(addButton.getScene().getWindow());
        stage.setTitle("Agregar Programa de Beneficios");

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setStyle("-fx-padding: 10;");

        Label nameLabel = new Label("Nombre del Programa:");
        TextField nameField = new TextField();

        Label benefitLabel = new Label("Beneficio:");
        TextField benefitField = new TextField();

        Label discountLabel = new Label("Descuento:");
        TextField discountField = new TextField();

        Button addButton = new Button("Agregar");
        addButton.setOnAction(e -> {
            double discount = Double.parseDouble(discountField.getText());
            model.addSpoiledProgram(new SpoiledProgram(nameField.getText(), benefitField.getText(), discount));
            this.sprogram_list.add(model.findSpoiledProgram(nameField.getText()));
            updateSProgramList();
            stage.close();
        });

        vbox.getChildren().addAll(nameLabel, nameField, benefitLabel, benefitField, discountLabel, discountField, addButton);

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.showAndWait();
    }

    private void showCustomerList(SpoiledProgram sprogram) {
        infoContainer.getChildren().clear();

        Button addCustomerButton = new Button("Agregar Cliente");
        addCustomerButton.setOnAction(e -> openAddCustomerPopUp(sprogram));
        infoContainer.getChildren().add(addCustomerButton);

        if (sprogram.getCustomerList() != null) {
            for (Customer customer : sprogram.getCustomerList()) {
                VBox customerBox = new VBox();
                customerBox.setSpacing(5);
                customerBox.setStyle("-fx-padding: 10; -fx-background-color: #f0f0f0; -fx-border-color: #d0d0d0;");

                Label nameLabel = new Label("Nombre: " + customer.getCustomer_name());
                nameLabel.setStyle("-fx-font-weight: bold;");
                Label addressLabel = new Label("Dirección: " + customer.getContact_info().getAdress());
                addressLabel.setStyle("-fx-font-style: italic;");
                Label phoneLabel = new Label("Número de Teléfono: " + customer.getContact_info().getPhone_num());
                phoneLabel.setStyle("-fx-font-style: italic;");
                Label emailLabel = new Label("Email: " + customer.getContact_info().getEmail());
                emailLabel.setStyle("-fx-font-style: italic;");

                Button deleteButton = new Button("Eliminar");
                    deleteButton.setOnAction(e -> {
                    sprogram.getCustomerList().remove(customer);
                    showCustomerList(sprogram);
                });

                Button editButton = new Button("Editar");
                editButton.setOnAction(e -> openEditCustomerPopUp(sprogram, customer));

                HBox buttonBox = new HBox(10, editButton, deleteButton);
                buttonBox.setAlignment(Pos.CENTER_RIGHT);

                customerBox.getChildren().addAll(nameLabel, addressLabel, phoneLabel, emailLabel, buttonBox);
                infoContainer.getChildren().add(customerBox);
            }
        } else {
            Label noCustomersLabel = new Label("No Customers available");
            infoContainer.getChildren().add(noCustomersLabel);
        }
    }
    
    private void clearCustomerList() {
        infoContainer.getChildren().clear();
        }

    @SuppressWarnings("exports")
    public void openAddCustomerPopUp(SpoiledProgram sprogram) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(addButton.getScene().getWindow());
        stage.setTitle("Agregar Cliente");
        
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setStyle("-fx-padding: 10;");
        
        Label nameLabel = new Label("Nombre del Cliente:");
        TextField nameField = new TextField();
        
        Label addressLabel = new Label("Dirección:");
        TextField addressField = new TextField();
        
        Label phoneLabel = new Label("Número de Teléfono:");
        TextField phoneField = new TextField();
        
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        
        ComboBox<String> customerComboBox = new ComboBox<>();
        customerComboBox.setPromptText("Seleccionar Cliente");
        
        // Solo mostrar el ComboBox si la lista no es la general
        if (!sprogram.getProgramName().equals("General")) {
            List<Customer> customerList = model.getCustomerList();
            if (customerList != null && !customerList.isEmpty()) {
                for (Customer customer : customerList) {
                    customerComboBox.getItems().add(customer.getCustomer_name());
                }
        
                customerComboBox.setOnAction(e -> {
                    String selectedCustomerName = customerComboBox.getValue();
                    Customer selectedCustomer = customerList.stream()
                        .filter(c -> c.getCustomer_name().equals(selectedCustomerName))
                        .findFirst()
                        .orElse(null);
        
                    if (selectedCustomer != null) {
                        nameField.setText(selectedCustomer.getCustomer_name());
                        addressField.setText(selectedCustomer.getContact_info().getAdress());
                        phoneField.setText(String.valueOf(selectedCustomer.getContact_info().getPhone_num()));
                        emailField.setText(selectedCustomer.getContact_info().getEmail());
                    }
                });
        
                vbox.getChildren().add(customerComboBox);
            } else {
                Label noCustomersLabel = new Label("No hay clientes disponibles");
                vbox.getChildren().add(noCustomersLabel);
            }
        }
        
        Button addButton = new Button("Agregar");
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String address = addressField.getText();
            int phone = Integer.parseInt(phoneField.getText());
            String email = emailField.getText();
        
            ContactInfo contactInfo = new ContactInfo(address, phone, email);
            Customer newCustomer = new Customer(name, contactInfo);
        
            if (sprogram.getCustomerList() == null) {
                sprogram.setCustomerList(new ArrayList<>());
            }
            sprogram.getCustomerList().add(newCustomer);
            showCustomerList(sprogram); // Actualiza la lista de clientes mostrada
            stage.close();
        });
        
        vbox.getChildren().addAll(nameLabel, nameField, addressLabel, addressField, phoneLabel, phoneField, emailLabel, emailField, addButton);
        
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.showAndWait();
    }
        
    @SuppressWarnings("exports")
    public void openEditCustomerPopUp(SpoiledProgram sprogram, Customer customer) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(addButton.getScene().getWindow());
        stage.setTitle("Editar Cliente");
    
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setStyle("-fx-padding: 10;");
    
        Label nameLabel = new Label("Nombre del Cliente:");
        TextField nameField = new TextField(customer.getCustomer_name());
    
        Label addressLabel = new Label("Dirección:");
        TextField addressField = new TextField(customer.getContact_info().getAdress());
    
        Label phoneLabel = new Label("Número de Teléfono:");
        TextField phoneField = new TextField(String.valueOf(customer.getContact_info().getPhone_num()));
    
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField(customer.getContact_info().getEmail());
    
        Button saveButton = new Button("Guardar");
        saveButton.setOnAction(e -> {
            customer.setCustomer_name(nameField.getText());
            customer.getContact_info().setAdress(addressField.getText());
            customer.getContact_info().setPhone_num(Integer.parseInt(phoneField.getText()));
            customer.getContact_info().setEmail(emailField.getText());
            showCustomerList(sprogram);
            stage.close();
        });
    
        vbox.getChildren().addAll(nameLabel, nameField, addressLabel, addressField, phoneLabel, phoneField, emailLabel, emailField, saveButton);
    
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.showAndWait();
    }
}

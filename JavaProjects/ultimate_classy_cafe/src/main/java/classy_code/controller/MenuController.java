package classy_code.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import classy_code.model.menu.Menu;
import classy_code.model.menu.FoodCategory;

import java.util.stream.Collectors;

import classy_code.App;
import classy_code.model.menu.Drink;
import classy_code.model.menu.DrinkCategory;
import classy_code.model.menu.Food;

public class MenuController extends ListNInfoController {
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
    
    @FXML private VBox foodContainer;
    @FXML private VBox drinkContainer;

    private Menu menu;

    //Constructor
    /*
     * Constructor de la clase MenuController
     * Inicializa el modelo de menu
     * @param void
     */
    public MenuController() {
        System.out.println("MenuController created");
        menu = new Menu("Menu");
    }

    //Metodos
    /*
     * Metodo initialize
     * Inicializa el controlador
     */
    @Override
    public void initialize() {
        System.out.println("MenuController initialized");
        refreshCategoryViews();
    }

    /*
     * Metodo handleMenuButton
     * Maneja el evento de presionar el boton de menu
     */
    @FXML
    private void handleAddButton() {
        Stage stage = new Stage();
        VBox vbox = new VBox(10);
        vbox.setAlignment(javafx.geometry.Pos.CENTER);

        Label nameLabel = new Label("Nombre de la Categoría");
        TextField categoryNameField = new TextField();
        HBox checkBoxContainer = new HBox(10);
        CheckBox foodCheckBox = new CheckBox("Comida");
        CheckBox drinkCheckBox = new CheckBox("Bebida");
        checkBoxContainer.getChildren().addAll(drinkCheckBox, foodCheckBox);
        Button addCategoryButton = new Button("Agregar");

        addCategoryButton.setOnAction(e -> {
            String categoryName = categoryNameField.getText();
            if (foodCheckBox.isSelected() && !drinkCheckBox.isSelected()) {
                FoodCategory foodCategory = new FoodCategory(categoryName);
                menu.addFoodCategory(foodCategory);
                addFoodCategoryToView(foodCategory);
            } else if (drinkCheckBox.isSelected() && !foodCheckBox.isSelected()) {
                DrinkCategory drinkCategory = new DrinkCategory(categoryName);
                menu.addDrinkCategory(drinkCategory);
                addDrinkCategoryToView(drinkCategory);
            }
            stage.close();
        });

        vbox.getChildren().addAll(nameLabel, categoryNameField, checkBoxContainer, addCategoryButton);
        Scene scene = new Scene(vbox, 300, 200);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    /*
     * Metodo addFoodCategoryToView
     * Agrega una categoria de comida a la vista
     * @param FoodCategory category
     */
    @SuppressWarnings("exports")
    public void addFoodCategoryToView(FoodCategory category) {
        HBox hbox = createCategoryHBox(category.getTitle(), category);
        foodContainer.getChildren().add(hbox);
    }

    /*
     * Metodo addDrinkCategoryToView
     * Agrega una categoria de bebida a la vista
     * @param DrinkCategory category
     */
    @SuppressWarnings("exports")
    public void addDrinkCategoryToView(DrinkCategory category) {
        HBox hbox = createCategoryHBox(category.getTitle(), category);
        drinkContainer.getChildren().add(hbox);
    }

    /*
     * Metodo createCategoryHBox
     * Crea un HBox con los botones de ver, editar y eliminar una categoria
     * @param String title
     * @param Object category
     * @return HBox
     */
    private HBox createCategoryHBox(String title, Object category) {
        HBox hbox = new HBox(10);
        Label label = new Label(title);
        label.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Button viewButton = new Button("Ver");
        Button editButton = new Button("Editar");
        Button deleteButton = new Button("Eliminar");

        viewButton.setOnAction(e -> handleViewCategory(category));
        editButton.setOnAction(e -> handleEditCategory(title));
        deleteButton.setOnAction(e -> handleDeleteCategory(hbox, title));

        hbox.getChildren().addAll(label, viewButton, editButton, deleteButton);
        return hbox;
    }

    /*
     * Metodo handleViewCategory
     * Maneja el evento de ver una categoria
     * @param Object category
     */
    private void handleViewCategory(Object category) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/classy_code/CategoryView.fxml"));
            loader.setController(App.categoryController);
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);
            CategoryController controller = loader.getController();
            controller.setCategory(category);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Metodo handleEditCategory
     * Maneja el evento de editar una categoria
     * @param String title
     */
    private void handleEditCategory(String title) {
        Stage stage = new Stage();
        VBox vbox = new VBox(10);
        vbox.setAlignment(javafx.geometry.Pos.CENTER);

        Label nameLabel = new Label("Editar Nombre de la Categoría");
        TextField categoryNameField = new TextField(title);
        Button editCategoryButton = new Button("Guardar");

        editCategoryButton.setOnAction(e -> {
            String newCategoryName = categoryNameField.getText();
            FoodCategory foodCategory = menu.findFoodCategory(title);
            if (foodCategory != null) {
                foodCategory.setTitle(newCategoryName);
            } else {
                DrinkCategory drinkCategory = menu.findDrinkCategory(title);
                if (drinkCategory != null) {
                    drinkCategory.setTitle(newCategoryName);
                }
            }
            stage.close();
            refreshCategoryViews();
        });

        vbox.getChildren().addAll(nameLabel, categoryNameField, editCategoryButton);
        Scene scene = new Scene(vbox, 300, 200);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    /*
     * Metodo handleDeleteCategory
     * Maneja el evento de eliminar una categoria
     * @param HBox hbox
     * @param String title
     */
    private void handleDeleteCategory(HBox hbox, String title) {
        foodContainer.getChildren().remove(hbox);
        drinkContainer.getChildren().remove(hbox);
        menu.removeFoodCategory(title);
        menu.removeDrinkCategory(title);
    }

    /*
     * Metodo refreshCategoryViews
     * Refresca las vistas de las categorias
     */
    private void refreshCategoryViews() {
        foodContainer.getChildren().clear();
        drinkContainer.getChildren().clear();
        for (FoodCategory foodCategory : menu.getFoodCategory_list()) {
            addFoodCategoryToView(foodCategory);
        }
        for (DrinkCategory drinkCategory : menu.getDrinkCategory_list()) {
            addDrinkCategoryToView(drinkCategory);
        }
    }

    /*
     * Metodo getFoodsByCategory
     * Obtiene la lista de comidas por categoria
     * @param String category
     * @return ObservableList<String>
     */
    public ObservableList<String> getFoodsByCategory(String category) {
        FoodCategory foodCategory = menu.findFoodCategory(category);
        if (foodCategory != null) {
            return FXCollections.observableArrayList(
                foodCategory.getFood_list().stream()
                    .map(food -> food.getName())
                    .collect(Collectors.toList())
            );
        }
        return FXCollections.observableArrayList();
    }

    /*
     * Metodo getDrinksByCategory
     * Obtiene la lista de bebidas por categoria
     * @param String category
     * @return ObservableList<String>
     */
    public ObservableList<String> getDrinksByCategory(String category) {
        DrinkCategory drinkCategory = menu.findDrinkCategory(category);
        if (drinkCategory != null) {
            return FXCollections.observableArrayList(
                drinkCategory.getDrink_list().stream()
                    .map(drink -> drink.getName())
                    .collect(Collectors.toList())
            );
        }
        return FXCollections.observableArrayList();
    }

    /*
     * Metodo getFoodByName
     * Obtiene la comida por nombre
     * @param String selectedFood
     * @return Food
     */
    @SuppressWarnings("exports")
    public Food getFoodByName(String selectedFood) {
        for (FoodCategory foodCategory : menu.getFoodCategory_list()) {
            for (Food food : foodCategory.getFood_list()) {
                if (food.getName().equals(selectedFood)) {
                    return food;
                }
            }
        }
        return null;
    }

    /*
     * Metodo getDrinkByName
     * Obtiene la bebida por nombre
     * @param String selectedDrink
     * @return Drink
     */
    @SuppressWarnings("exports")
    public Drink getDrinkByName(String selectedDrink) {
        for (DrinkCategory drinkCategory : menu.getDrinkCategory_list()) {
            for (Drink drink : drinkCategory.getDrink_list()) {
                if (drink.getName().equals(selectedDrink)) {
                    return drink;
                }
            }
        }
        return null;
    }   
}

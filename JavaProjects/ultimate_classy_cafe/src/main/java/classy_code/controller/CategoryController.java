package classy_code.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import classy_code.model.menu.FoodCategory;
import classy_code.model.menu.DrinkCategory;
import classy_code.model.menu.Food;
import classy_code.model.menu.Drink;
import classy_code.model.ingredient.Ingredient;
import classy_code.model.ingredient.IngredientWarehouse;
import classy_code.model.menu.Recipe;
import java.util.List;

public class CategoryController extends SearchNInfoController {
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
    @FXML private VBox productContainer;
    @FXML private VBox infoContainer;

    private Object category;

    @Override
    public void initialize() {
        System.out.println("CategoryController");
    }

    public void setCategory(Object category) {
        this.category = category;
        if (category instanceof FoodCategory) {
            titleLabel.setText(((FoodCategory) category).getTitle());
            loadProducts(((FoodCategory) category).getFood_list());
        } else if (category instanceof DrinkCategory) {
            titleLabel.setText(((DrinkCategory) category).getTitle());
            loadProducts(((DrinkCategory) category).getDrink_list());
        }
    }

    private void loadProducts(List<?> products) {
        productContainer.getChildren().clear();
        if (products.isEmpty()) {
            Label noProductsLabel = new Label("No hay Productos");
            productContainer.getChildren().add(noProductsLabel);
        } else {
            for (Object product : products) {
                HBox hbox = createProductHBox(product);
                productContainer.getChildren().add(hbox);
            }
        }
    }

    private HBox createProductHBox(Object product) {
        HBox hbox = new HBox(10);
        Label label = new Label(product instanceof Food ? ((Food) product).getName() : ((Drink) product).getName());
        Button editButton = new Button("Editar");
        Button deleteButton = new Button("Eliminar");

        hbox.setOnMouseEntered(e -> {
            showProductInfo(product);
            hbox.setStyle("-fx-border-color: blue; -fx-border-width: 1px;");
        });
        hbox.setOnMouseExited(e -> hbox.setStyle("-fx-border-color: transparent;"));
        editButton.setOnAction(e -> handleEditProduct(product));
        deleteButton.setOnAction(e -> handleDeleteProduct(hbox, product));

        hbox.getChildren().addAll(label, editButton, deleteButton);
        return hbox;
    }

    private void showProductInfo(Object product) {
        infoContainer.getChildren().clear();
        Label titleLabel = new Label("Información del Producto");
        Label nameLabel = new Label("Nombre: " + (product instanceof Food ? ((Food) product).getName() : ((Drink) product).getName()));
        Label priceLabel = new Label("Precio: " + (product instanceof Food ? ((Food) product).getPrice() : ((Drink) product).getPrice()));
        Label descriptionLabel = new Label("Descripción: " + (product instanceof Food ? ((Food) product).getDescription() : ((Drink) product).getDescription()));
        Label sizeLabel = new Label("Tamaño: " + (product instanceof Food ? ((Food) product).getWeight() : ((Drink) product).getVolume()));
        Label recipeLabel = new Label("Receta");
        recipeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Button addIngredientButton = new Button("Añadir Ingrediente");

        addIngredientButton.setOnAction(e -> handleAddIngredient(product));

        infoContainer.getChildren().addAll(titleLabel, nameLabel, priceLabel, descriptionLabel, sizeLabel, recipeLabel, addIngredientButton);

        Recipe recipe = product instanceof Food ? ((Food) product).getRecipe() : ((Drink) product).getRecipe();
        if (recipe != null) {
            for (int i = 0; i < recipe.getIngredients().size(); i++) {
                Ingredient ingredient = recipe.getIngredients().get(i);
                double quantity = recipe.getQuantities().get(i);
                HBox ingredientHBox = createIngredientHBox(ingredient, quantity, recipe);
                infoContainer.getChildren().add(ingredientHBox);
            }
        } else {
            Label noRecipeLabel = new Label("No hay Receta");
            infoContainer.getChildren().add(noRecipeLabel);
        }
    }

    private HBox createIngredientHBox(Ingredient ingredient, double quantity, Recipe recipe) {
        HBox hbox = new HBox(10);
        Label label = new Label(ingredient.getName() + ": " + quantity);
        Button editButton = new Button("Editar");
        Button deleteButton = new Button("Eliminar");

        editButton.setOnAction(e -> handleEditIngredient(ingredient, recipe));
        deleteButton.setOnAction(e -> handleDeleteIngredient(hbox, ingredient, recipe));

        hbox.getChildren().addAll(label, editButton, deleteButton);
        return hbox;
    }

    private void handleEditIngredient(Ingredient ingredient, Recipe recipe) {
        Stage stage = new Stage();
        VBox vbox = new VBox(10);
        vbox.setAlignment(javafx.geometry.Pos.CENTER);

        Label quantityLabel = new Label("Cantidad");
        TextField quantityField = new TextField(String.valueOf(recipe.getQuantities().get(recipe.getIngredients().indexOf(ingredient))));
        Button saveButton = new Button("Guardar");

        saveButton.setOnAction(e -> {
            double newQuantity = Double.parseDouble(quantityField.getText());
            recipe.getQuantities().set(recipe.getIngredients().indexOf(ingredient), newQuantity);
            stage.close();
            showProductInfo(recipe.getProduct());
        });

        vbox.getChildren().addAll(quantityLabel, quantityField, saveButton);
        Scene scene = new Scene(vbox, 300, 200);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private void handleDeleteIngredient(HBox hbox, Ingredient ingredient, Recipe recipe) {
        infoContainer.getChildren().remove(hbox);
        int index = recipe.getIngredients().indexOf(ingredient);
        recipe.getIngredients().remove(index);
        recipe.getQuantities().remove(index);
    }

    private void handleAddIngredient(Object product) {
        Stage stage = new Stage();
        VBox vbox = new VBox(10);
        vbox.setAlignment(javafx.geometry.Pos.CENTER);

        Label ingredientLabel = new Label("Ingrediente");
        ComboBox<Ingredient> ingredientComboBox = new ComboBox<>();
        ingredientComboBox.getItems().addAll(IngredientWarehouse.getInstance().getIngredients_list());
        Label quantityLabel = new Label("Cantidad");
        TextField quantityField = new TextField();
        Button saveButton = new Button("Guardar");

        saveButton.setOnAction(e -> {
            Ingredient selectedIngredient = ingredientComboBox.getValue();
            double quantity = Double.parseDouble(quantityField.getText());
            Recipe recipe = product instanceof Food ? ((Food) product).getRecipe() : ((Drink) product).getRecipe();
            if (recipe == null) {
                recipe = new Recipe(null);
                if (product instanceof Food) {
                    ((Food) product).setRecipe(recipe);
                } else if (product instanceof Drink) {
                    ((Drink) product).setRecipe(recipe);
                }
            }
            recipe.getIngredients().add(selectedIngredient);
            recipe.getQuantities().add(quantity);
            HBox ingredientHBox = createIngredientHBox(selectedIngredient, quantity, recipe);
            infoContainer.getChildren().add(ingredientHBox);
            stage.close();
        });

        vbox.getChildren().addAll(ingredientLabel, ingredientComboBox, quantityLabel, quantityField, saveButton);
        Scene scene = new Scene(vbox, 300, 200);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private void handleEditProduct(Object product) {
        Stage stage = new Stage();
        VBox vbox = new VBox(10);
        vbox.setAlignment(javafx.geometry.Pos.CENTER);

        Label nameLabel = new Label("Nombre");
        TextField nameField = new TextField(product instanceof Food ? ((Food) product).getName() : ((Drink) product).getName());
        Label priceLabel = new Label("Precio");
        TextField priceField = new TextField(String.valueOf(product instanceof Food ? ((Food) product).getPrice() : ((Drink) product).getPrice()));
        Label descriptionLabel = new Label("Descripción");
        TextField descriptionField = new TextField(product instanceof Food ? ((Food) product).getDescription() : ((Drink) product).getDescription());
        Label sizeLabel = new Label("Tamaño");
        TextField sizeField = new TextField(product instanceof Food ? String.valueOf(((Food) product).getWeight()) : String.valueOf(((Drink) product).getVolume()));
        Button saveButton = new Button("Guardar");

        saveButton.setOnAction(e -> {
            if (product instanceof Food) {
                ((Food) product).setName(nameField.getText());
                ((Food) product).setPrice(Double.parseDouble(priceField.getText()));
                ((Food) product).setDescription(descriptionField.getText());
                ((Food) product).setWeight(Double.parseDouble(sizeField.getText()));
            } else if (product instanceof Drink) {
                ((Drink) product).setName(nameField.getText());
                ((Drink) product).setPrice(Double.parseDouble(priceField.getText()));
                ((Drink) product).setDescription(descriptionField.getText());
                ((Drink) product).setVolume(Double.parseDouble(sizeField.getText()));
            }
            stage.close();
            loadProducts(category instanceof FoodCategory ? ((FoodCategory) category).getFood_list() : ((DrinkCategory) category).getDrink_list());
        });

        vbox.getChildren().addAll(nameLabel, nameField, priceLabel, priceField, descriptionLabel, descriptionField, sizeLabel, sizeField, saveButton);
        Scene scene = new Scene(vbox, 300, 400);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private void handleDeleteProduct(HBox hbox, Object product) {
        productContainer.getChildren().remove(hbox);
        if (category instanceof FoodCategory) {
            ((FoodCategory) category).getFood_list().remove(product);
        } else if (category instanceof DrinkCategory) {
            ((DrinkCategory) category).getDrink_list().remove(product);
        }
    }

    @FXML
    private void handleAddButton() {
        Stage stage = new Stage();
        VBox vbox = new VBox(10);
        vbox.setAlignment(javafx.geometry.Pos.CENTER);

        Label nameLabel = new Label("Nombre");
        TextField nameField = new TextField();
        Label priceLabel = new Label("Precio");
        TextField priceField = new TextField();
        Label descriptionLabel = new Label("Descripción");
        TextField descriptionField = new TextField();
        Label sizeLabel = new Label("Tamaño");
        TextField sizeField = new TextField();
        Button addButton = new Button("Agregar");

        addButton.setOnAction(e -> {
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            String description = descriptionField.getText();
            String size = sizeField.getText();
            if (category instanceof FoodCategory) {
                Food food = new Food(name, price, description, Double.parseDouble(size));
                ((FoodCategory) category).getFood_list().add(food);
                productContainer.getChildren().add(createProductHBox(food));
            } else if (category instanceof DrinkCategory) {
                Drink drink = new Drink(name, price, description, Double.parseDouble(size));
                ((DrinkCategory) category).getDrink_list().add(drink);
                productContainer.getChildren().add(createProductHBox(drink));
            }
            stage.close();
        });

        vbox.getChildren().addAll(nameLabel, nameField, priceLabel, priceField, descriptionLabel, descriptionField, sizeLabel, sizeField, addButton);
        Scene scene = new Scene(vbox, 300, 400);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}

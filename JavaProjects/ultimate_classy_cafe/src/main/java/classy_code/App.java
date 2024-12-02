package classy_code;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import classy_code.controller.BasicController;
import classy_code.controller.CategoryController;
import classy_code.controller.CustomerController;
import classy_code.controller.IngredientController;
import classy_code.controller.MenuController;
import classy_code.controller.OrderController;
import classy_code.controller.ReportController;
import classy_code.controller.SaleController;
import classy_code.controller.TableController;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    public static BasicController basicController = new BasicController();
    public static CategoryController categoryController = new CategoryController();
    public static CustomerController customerController = new CustomerController();
    public static IngredientController ingredientController = new IngredientController();
    public static MenuController menuController = new MenuController();
    public static OrderController orderController = new OrderController();
    public static ReportController reportController = new ReportController();
    public static SaleController saleController = new SaleController();
    public static TableController tableController = new TableController();

    @SuppressWarnings("exports")
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("BasicView", basicController), 1280, 720);
        stage.setScene(scene);
        stage.setTitle("Classy Cafe");
        stage.show();

        // Evento de cierre de la ventana
        stage.setOnCloseRequest(e -> {
            System.out.println("Cerrando la aplicación...");
        });
    }

    // Carga el archivo FXML
    /*
     * Metodo loadFXML
     * Carga el archivo FXML
     * @param fxml: nombre del archivo FXML
     * @param controlador: controlador de la vista
     */
    private static Parent loadFXML(String fxml, Object controlador) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setController(controlador); // Establece el controlador instanciado
        return fxmlLoader.load();
    }

    // Lo usa el Controlador para cambiar de escena
    /*
     * Metodo setRoot
     * Cambia la escena
     * @param fxml: nombre del archivo FXML
     * @param controlador: controlador de la vista
     */
    public static void setRoot(String fxml, Object controlador) throws IOException {
        scene.setRoot(loadFXML(fxml, controlador));
    }

    public static void main(String[] args) {
        launch();
    }
}
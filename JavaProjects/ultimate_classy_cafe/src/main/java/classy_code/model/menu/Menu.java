//Revision 1.0 (Pendiente Revision Final)
package classy_code.model.menu;

//Library Imports
import java.util.ArrayList;

/*
 * Clase Menu
 */
public class Menu {
    private String title;
    private ArrayList<FoodCategory> foodCategory_list;
    private ArrayList<DrinkCategory> drinkCategory_list;

    //Constructor
    /*
     * Constructor Menu
     * Inicializa el menu con un titulo
     * @param title: titulo del menu
     */
    public Menu(String title) {
        this.title = title;
        foodCategory_list = new ArrayList<>();
        drinkCategory_list = new ArrayList<>();
    }

    //Getters y Setters
    /*
     * metodo getTitle
     * Devuelve el titulo del menu
     * @return: titulo del menu
     */
    public String getTitle() {return title;}
    /*
     * metodo setTitle
     * Cambia el titulo del menu
     * @param title: nuevo titulo del menu
     */
    public void setTitle(String title) {this.title = title;}

    /*
     * metodo getFoodCategory_list
     * Devuelve la lista de categorias de comida
     * @return: lista de categorias de comida
     */
    public ArrayList<FoodCategory> getFoodCategory_list() {return foodCategory_list;}

    /*
     * metodo getDrinkCategory_list
     * Devuelve la lista de categorias de bebida
     * @return: lista de categorias de bebida
     */
    public ArrayList<DrinkCategory> getDrinkCategory_list() {return drinkCategory_list;}

    //Metodos
    /*
     * metodo addCategory
     * Agrega una categoria al menu
     * @param category: categoria a agregar
     */
    public void addFoodCategory(FoodCategory category) {
        foodCategory_list.add((FoodCategory) category);
    }

    /*
     * metodo removeCategory
     * Elimina una categoria del menu
     * @param category: categoria a eliminar
     */
    public void removeFoodCategory(String title) {
        foodCategory_list.remove(findFoodCategory(title));
    }

    /*
     * metodo findCategory
     * Busca una categoria en el menu
     * @param name: nombre de la categoria
     * @return: la categoria si la encuentra, null si no
     */
    public FoodCategory findFoodCategory(String title) {
        for (FoodCategory category : foodCategory_list) {
            if (category.getTitle().equals(title)) {
                return category;
            }
        }
        return null;
    }

    /*
     * metodo addDrinkCategory
     * Agrega una categoria al menu
     * @param category: categoria a agregar
     */
    public void addDrinkCategory(DrinkCategory category) {
        drinkCategory_list.add((DrinkCategory) category);
    }

    /*
     * metodo removeDrinkCategory
     * Elimina una categoria del menu
     * @param category: categoria a eliminar
     */
    public void removeDrinkCategory(String title) {
        drinkCategory_list.remove(findDrinkCategory(title));
    }

    /*
     * metodo findDrinkCategory
     * Busca una categoria en el menu
     * @param name: nombre de la categoria
     * @return: la categoria si la encuentra, null si no
     */
    public DrinkCategory findDrinkCategory(String title) {
        for (DrinkCategory category : drinkCategory_list) {
            if (category.getTitle().equals(title)) {
                return category;
            }
        }
        return null;
    }

    /*
     * metodo removeCategory
     * Elimina una categoria del menu
     * @param category: categoria a eliminar
     */

    /*
     * metodo stock_refresh
     * Actualiza el stock de todos los productos del menu
     * @Deprecated
     */
    public void stock_refresh() {
        for (Category category : foodCategory_list) {
            category.stock_refresh();
        }
        for (Category category : drinkCategory_list) {
            category.stock_refresh();
        }
    }
}

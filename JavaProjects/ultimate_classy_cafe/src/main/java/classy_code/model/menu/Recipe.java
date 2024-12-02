//Packages
package classy_code.model.menu;

//Library Imports
import java.util.ArrayList;

//Local Imports
import classy_code.model.ingredient.Ingredient;

/*
 * Clase Recipe
 */
public class Recipe {
    ArrayList<Ingredient> ingredients_list;
    ArrayList<Double> amount_list;
    private Product product;

    //Constructor
    /*
     * Constructor Recipe
     * Inicializa la receta con una lista de ingredientes y una lista de cantidades
     */
    public Recipe(Product product) {
        this.ingredients_list = new ArrayList<>();
        this.amount_list = new ArrayList<>();
        this.product = product;
    }

    //Getters y Setters
    /*
     * metodo getProduct
     * Obtiene el producto de la receta
     * @return: producto de la receta
     */
    public Product getProduct() {
        return product;
    }

    /*
     * metodo getIngredients_list
     * Obtiene la lista de ingredientes de la receta
     * @return: lista de ingredientes
     */
    public ArrayList<Ingredient> getIngredients() {
        return ingredients_list;
    }
    /*
     * metodo getAmount_list
     * Obtiene la lista de cantidades de la receta
     * @return: lista de cantidades
     */
    public ArrayList<Double> getQuantities() {
        return amount_list;
    }
    /*
     * metodo addIngredient
     * Agrega un ingrediente a la receta
     * @param ingredient: ingrediente a agregar
     * @param amount: cantidad de ingrediente a agregar
     */
    public void addIngredient(Ingredient ingredient, Double amount) {
        ingredients_list.add(ingredient);
        amount_list.add(amount);
    }
    /*
     * metodo removeIngredient
     * Remueve un ingrediente de la receta
     * @param name: nombre del ingrediente a remover
     */
    public void removeIngredient(String name) {
        for (int i = 0; i < ingredients_list.size(); i++) {
            if (ingredients_list.get(i).getName().equals(name)) {
                ingredients_list.remove(i);
                amount_list.remove(i);
            }
        }
    }
    /*
     * metodo findIngredient
     * Busca un ingrediente en la receta
     * @param name: nombre del ingrediente
     * @return: el ingrediente si lo encuentra, null si no
     */
    public Ingredient findIngredient(String name) {
        for (Ingredient ingredient : ingredients_list) {
            if (ingredient.getName().equals(name)) {
                return ingredient;
            }
        }
        return null;
    }
    /*
     * metodo findIngredientAmount
     * Busca la cantidad de un ingrediente en la receta
     * @param name: nombre del ingrediente
     * @return: la cantidad del ingrediente si lo encuentra, null si no
     */
    public Double findIngredientAmount(String name) {
        for (int i = 0; i < ingredients_list.size(); i++) {
            if (ingredients_list.get(i).getName().equals(name)) {
                return amount_list.get(i);
            }
        }
        return null;
    }
}

//Revision 1.0 (Pendiente Revision Final)
package com.example.model.ingredients;

//Library Imports
import java.util.ArrayList;

/*
 * Clase IngredientController
 */
public class IngredientWarehouse {
    public ArrayList<Ingredient> ingredients_list;

    //Constructor
    /*
     * Constructor IngredientController
     * Inicializa la lista de ingredientes
     */
    public IngredientWarehouse() {
        this.ingredients_list = new ArrayList<>();
    }

    //Getters y Setters
    /*
     * Metodo getIngredients_list
     * Devuelve la lista de ingredientes
     * @return ArrayList<Ingredient> lista de ingredientes
     */
    public ArrayList<Ingredient> getIngredients_list() {
        return ingredients_list;
    }
    
    //Metodos
    /*
     * Metodo addIngredient
     * Agrega un ingrediente a la lista de ingredientes
     * @param name: nombre del ingrediente
     * @param stock: cantidad de stock del ingrediente
     */
    public void addIngredient(String name, Double stock) {
        this.ingredients_list.add(new Ingredient(name, stock));
    }
    /*
     * Metodo removeIngredient
     * Elimina un ingrediente de la lista de ingredientes
     * @param name: nombre del ingrediente
     */
    public void removeIngredient(String name) {
        ingredients_list.remove(findIngredient(name));
    }
    /*
     * Metodo findIngredient
     * Busca un ingrediente en la lista de ingredientes
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
}

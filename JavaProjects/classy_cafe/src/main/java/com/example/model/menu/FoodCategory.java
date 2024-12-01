//Revision 1.0 (Pendiente Revision Final)
package com.example.model.menu;

//Library Imports
import java.util.ArrayList;

/*
 * Clase FoodCategory
 * Implementa la interfaz Category
 */
public class FoodCategory implements Category {
    //Atributos 
    private String title;
    private ArrayList<Food> food_list;

    //Constructor
    /*
     * Constructor de FoodCategory
     * @param title Titulo de la categoria
     */
    public FoodCategory(String title) {
        this.title = title;
        this.food_list = new ArrayList<Food>();
    }

    //Getters y Setters
    /*
     * Getter de title
     * @return title
     * Implementada de la interfaz Category
     */
    public String getTitle() {return title;}
    /*
     * Setter de title
     * @param title Titulo de la categoria
     * Implementada de la interfaz Category
     */
    public void setTitle(String title) {this.title = title;}

    //Metodos
    /*
     * Metodo addProduct
     * @param product Producto a agregar
     * Implementada de la interfaz Category
     */
    public void addProduct(Product product) {
        this.food_list.add((Food) product);
    }
    /*
     * Metodo removeProduct
     * @param product Producto a remover
     * Implementada de la interfaz Category
     */
    public void removeProduct(String name) {
        for (Food food : this.food_list) {
            if (food.getName().equals(name)) {
                this.food_list.remove(food);
                break;
            }
        }
    }
    /*
     * Metodo findProduct
     * @param name Nombre del producto a buscar
     * Implementada de la interfaz Category
     */
    public Product findProduct(String name) {
        for (Food food : this.food_list) {
            if (food.getName().equals(name)) {
                return food;
            }
        }
        return null;
    }
    /*
     * Metodo stock_refresh
     * Actualiza el stock de los productos
     * Implementada de la interfaz Category
     */
    public void stock_refresh() {
        for (Food food : this.food_list) {
            food.stock_refresh();
        }
    }
}

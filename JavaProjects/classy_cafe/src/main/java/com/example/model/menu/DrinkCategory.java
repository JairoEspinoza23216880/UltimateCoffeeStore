//Revision 1.0 (Pendiente Revision Final)
package com.example.model.menu;

//Library Imports
import java.util.ArrayList;

/*
 * Clase DrinkCategory
 * Implementa la interfaz Category
 */
public class DrinkCategory implements Category {
    //Atributos
    private String title;
    private ArrayList<Drink> drink_list;

    //Constructor
    /*
     * Constructor de DrinkCategory
     * @param title Titulo de la categoria
     * Implementada de la interfaz Category
     */
    public DrinkCategory(String title) {
        this.title = title;
        this.drink_list = new ArrayList<Drink>();    
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
        this.drink_list.add((Drink) product);
    }

    /*
     * Metodo removeProduct
     * @param product Producto a remover
     * Implementada de la interfaz Category
     */
    public void removeProduct(String name) {
        for (Drink drink : this.drink_list) {
            if (drink.getName().equals(name)) {
                this.drink_list.remove(drink);
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
        for (Drink drink : this.drink_list) {
            if (drink.getName().equals(name)) {
                return drink;
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
        for (Drink drink : this.drink_list) {
            drink.stock_refresh();
        }
    }
    

    
}

//Revision 1.0 (Pendiente Revision Final)
package com.example.model.menu;

import com.example.model.ingredients.Ingredient;

public class Product {
    //Atributos
    private String name;
    private double price;
    private String description;
    private Recipe recipe;
    private boolean available;

    //Constructor
    /*
     * Constructor Product
     * Inicializa el producto con un nombre, precio y receta
     * @param name: nombre del producto
     * @param price: precio del producto
     * @param description: descripcion del producto
     */
    public Product(String name, double price, String description, Recipe recipe) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.recipe = recipe;
    }

    //Getters y Setters
    /*
     * Constructor Product
     * Inicializa el producto con un nombre, precio y receta
     * @return: nombre del producto
     */
    public String getName() {return name;}
    /*
     * metodo setName
     * Cambia el nombre del producto
     * @param name: nuevo nombre del producto
     */
    public void setName(String name) {this.name = name;}
    /*
     * metodo getPrice
     * Devuelve el precio del producto
     * @return: precio del producto
     */
    public double getPrice() {return price;}
    /*
     * metodo setPrice
     * Cambia el precio del producto
     * @param price: nuevo precio del producto
     */
    public void setPrice(double price) {this.price = price;}
    /*
     * metodo getAvailable
     * Devuelve si el producto esta disponible
     * @return: true si esta disponible, false si no
     */
    public boolean getAvailable() {return available;}
    /*
     * metodo setAvailable
     * Cambia la disponibilidad del producto
     * @param available: true si esta disponible, false si no
     */
    public void setAvailable(boolean available) {this.available = available;}
    /*
     * metodo getRecipe
     * Devuelve la receta del producto
     * @return: receta del producto
     */
    public Recipe getRecipe() {return recipe;}
    /*
     * metodo setRecipe
     * Cambia la receta del producto
     * @param recipe: nueva receta del producto
     */
    public String getDescription() {return description;}
    /*
     * metodo setDescription
     * Cambia la descripcion del producto
     * @param description: nueva descripcion del producto
     */
    public void setDescription(String description) {this.description = description;}

    //Metodos
    /*
     * metodo stock_refresh
     * Actualiza la disponibilidad del producto
     */
    public void stock_refresh() {
        for (Ingredient ingredient : recipe.ingredients_list) {
            if (ingredient.getStock() == 0) {
                this.setAvailable(false);
            }
        }
    }
}
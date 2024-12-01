//Revision 1.0 (Pendiente Revision Final)
package com.example.model.menu;

/*
 * Clase Drink
 */
public class Drink extends Product {
    //Atributos
    private Double volume;

    //Constructor
    /*
     * Constructor Drink
     * Inicializa la bebida con un nombre, precio, descripcion y volumen
     * @param name: nombre de la bebida
     * @param price: precio de la bebida
     * @param description: descripcion de la bebida
     * @param volume: volumen de la bebida
     */
    public Drink(String name, double price, String description, Recipe recipe, Double volume) {
        super(name, price, description, recipe);
        this.volume = volume;
    }

    /*
     * metodo getVolume
     * Devuelve el volumen de la bebida
     * @return: volumen de la bebida
     */
    public Double getVolume() {
        return volume;
    }

    /*
     * metodo setVolume
     * Cambia el volumen de la bebida
     * @param volume: nuevo volumen de la bebida
     */
    public void setVolume(Double volume) {
        this.volume = volume;
    }
}
//Revision 1.0 (Pendiente Revisión Final)
package classy_code.model.ingredient;

import java.io.Serializable;

/*
 * Clase Ingredient
 */
public class Ingredient implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public String name;
    public Double stock;
    
    //Constructor
    /*
     * Constructor Ingredient
     * Inicializa el ingrediente con un nombre
     * @param name: nombre del ingrediente
     */
    public Ingredient(String name, Double stock) {
        this.name = name;
        this.stock = stock;
    }

    //Getters y Setters
    /*
     * metodo getName
     * Devuelve el nombre del ingrediente
     * @return: nombre del ingrediente
     */
    public String getName() {return name;}
    /*
     * metodo setName
     * Cambia el nombre del ingrediente
     * @param name: nuevo nombre del ingrediente
     */
    public void setName(String name) {this.name = name;}
    /*
     * metodo getStock
     * Devuelve la cantidad de stock del ingrediente
     * @return: cantidad de stock del ingrediente
     */
    public Double getStock() {return stock;}
    /*
     * metodo setStock
     * Cambia la cantidad de stock del ingrediente
     * @param stock: nueva cantidad de stock del ingrediente
     */
    public void setStock(Double stock) {this.stock = stock;}
}

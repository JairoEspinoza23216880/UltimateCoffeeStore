//Revision 1.0 (Pendiente Revision Final)
package classy_code.model.menu;

/*
 * Clase Food
 */
public class Food extends Product {
    //Atributos
    private Double weight;

    //Constructor
    /*
     * Constructor Food
     * Inicializa la comida con un nombre, precio, descripcion y peso
     * @param name: nombre de la comida
     * @param price: precio de la comida
     * @param description: descripcion de la comida
     * @param weight: peso de la comida
     */
    public Food(String name, double price, String description, Double weight) {
        super(name, price, description);
        this.weight = weight;
    }

    //Getters y Setters
    /*
     * metodo getWeight
     * Devuelve el peso de la comida
     * @return: peso de la comida
     */
    public Double getWeight() {return weight;}
    /*
     * metodo setWeight
     * Cambia el peso de la comida
     * @param weight: nuevo peso de la comida
     */
    public void setWeight(Double weight) {this.weight = weight;}
}
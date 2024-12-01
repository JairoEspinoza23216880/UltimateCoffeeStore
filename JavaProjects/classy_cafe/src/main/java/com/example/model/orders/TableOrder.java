//Revision 1.0 (Pendiente Revision Final)
package com.example.model.orders;

//Library Imports
import java.util.ArrayList;

//Local Imports
import com.example.model.customers.Customer;

/*
 * Clase TableOrder
 */
public class TableOrder {
    //Atributos
    private Table table;
    private double price_total;
    private Customer customer;
    private ArrayList<ChairOrder> order_list;

    //Constructor
    /*
     * Constructor de la clase TableOrder
     * @param table
     */
    public TableOrder(Table table) {
        this.table = table;
        this.price_total = 0;
        this.order_list = new ArrayList<ChairOrder>();
    }

    //Getters y Setters
    /*
     * Metodo GetTable
     * Método que retorna la mesa
     * @return table
     */
    public Table getTable() {return table;}
    /*
     * Metodo SetTable
     * Método que establece la mesa
     * @param table
     */
    public void setTable(Table table) {this.table = table;}
    /*
     * Metodo GetPrice_total
     * Método que retorna el precio total
     * @return price_total
     */
    public double getPrice_total() {return price_total;}
    /*
     * Metodo getCustomer
     * Método que retorna el cliente
     * @return customer
     */
    public Customer getCustomer() {return customer;}
    /*
     * Metodo setCustomer
     * Método que establece el cliente
     * @param customer
     */
    public void setCustomer(Customer customer) {this.customer = customer;}
    /*
     * Metodo getOrder_list
     * Método que retorna la lista de ordenes
     * @return order_list
     */
    public ArrayList<ChairOrder> getOrder_list() {return order_list;}

    //Métodos
    /*
     * Metodo updatePrice_total
     * Método que actualiza el precio total
     */
    public void updatePrice_total() {
        this.price_total = 0;
        for (ChairOrder chairOrder : this.order_list) {
            this.price_total += chairOrder.getPrice();
        }
    }

    /*
     * Metodo addChairOrder
     * Método que agrega una orden de silla
     * @param chairOrder
     */
    public void addChairOrder(ChairOrder chairOrder) {
        this.order_list.add(chairOrder);
        this.updatePrice_total();
    }

    /*
     * Metodo removeChairOrder
     * Método que remueve una orden de silla
     * @param chairOrder
     */
    public void removeChairOrder(Chair chair) {
        this.order_list.remove(findChairOrder(chair));
        this.updatePrice_total();
    }

    /*
     * Metodo findChairOrder
     * Método que busca una orden de silla
     * @param chairOrder
     * @return chairOrder
     */
    public ChairOrder findChairOrder(Chair chair) {
        for (ChairOrder chairOrder : this.order_list) {
            if (chairOrder.getChair().equals(chair)) {
                return chairOrder;
            }
        }
        return null;
    }
}

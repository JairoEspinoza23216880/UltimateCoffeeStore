//Revision 1.0 (Pendiente Revision Final)
package com.example.model.customers;

//Libray Imports
import java.util.ArrayList;

/*
 * Clase SpoiledProgram
 */
public class SpoiledProgram {
    //Atributos
    private String program_name;
    private ArrayList<Customer> customer_list;
    private String benefit;
    private double discount;

    //Constructor
    /*
     * Constructor de la clase SpoiledProgram
     * @param customer_list
     * @param benefit
     * @param discount
     */
    public SpoiledProgram(String benefit, double discount) {
        this.customer_list = new ArrayList<Customer>();
        this.benefit = benefit;
        this.discount = discount;
    }

    //Getters y Setters
    /*
     * Metodo getProgramName
     * Metodo que retorna el nombre del programa
     * @return program_name Nombre del programa
     */
    public String getProgramName() {return program_name;}
    /*
     * Metodo setProgramName
     * Metodo que establece el nombre del programa
     * @param program_name Nombre del programa
     */
    public void setProgramName(String program_name) {this.program_name = program_name;}
    /*
     * Metodo getCustomerList
     * Metodo que retorna la lista de clientes
     * @return customer_list Lista de clientes
     */
    public ArrayList<Customer> getCustomerList() {return customer_list;}
    /*
     * Metodo getBenefit
     * Metodo que retorna el beneficio
     * @return benefit Beneficio
     */
    public String getBenefit() {return benefit;}
    /*
     * Metodo setBenefit
     * Metodo que establece el beneficio
     * @param benefit Beneficio
     */
    public void setBenefit(String benefit) {this.benefit = benefit;}
    /*
     * Metodo getDiscount
     * Metodo que retorna el descuento
     * @return discount Descuento
     */
    public double getDiscount() {return discount;}
    /*
     * Metodo setDiscount
     * Metodo que establece el descuento
     * @param discount Descuento
     */
    public void setDiscount(double discount) {this.discount = discount;}

    //Metodos
    /*
     * Metodo addCustomer
     * Metodo que agrega un cliente a la lista de clientes
     * @param customer Cliente
     */
    public void addCustomer(Customer customer) {
        customer_list.add(customer);
    }

    /*
     * Metodo removeCustomer
     * Metodo que remueve un cliente de la lista de clientes
     * @param customer Cliente
     */
    public void removeCustomer(String name) {
        customer_list.remove(findCustomer(name));
    }
    
    /*
     * Metodo findCustomer
     * Metodo que busca un cliente en la lista de clientes
     * @param name Nombre del cliente
     * @return customer Cliente
     */
    public Customer findCustomer(String name) {
        for (Customer customer : customer_list) {
            if (customer.getCustomer_name().equals(name)) {
                return customer;
            }
        }
        return null;
    }
}

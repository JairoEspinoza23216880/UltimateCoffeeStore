//Revision 1.0 (Pendiente Revision Final)
package classy_code.model.customer;

//Local Imports
import classy_code.model.order.TableOrder;

/*
 * Clase Customer
 */
public class Customer {
    //Atributos
    private String customer_name;
    private TableOrder table_order;
    private ContactInfo contact_info;
    private Preferences preferences;

    //Constructor
    /*
     * Constructor de la clase Customer
     * @param customer_name
     * @param table_order
     * @param contact_info
     */
    public Customer(String customer_name, ContactInfo contact_info) {
        this.customer_name = customer_name;
        this.contact_info = contact_info;
        this.preferences = new Preferences();
    }

    //Getters y Setters
    /*
     * Metodo getCustomer_name
     * @return customer_name
     */
    public String getCustomer_name() {return customer_name;}
    /*
     * Metodo setCustomer_name
     * @param customer_name
     */
    public void setCustomer_name(String customer_name) {this.customer_name = customer_name;}
    /*
     * Metodo getTable_order
     * @return table_order
     */
    public TableOrder getTable_order() {return table_order;}
    /*
     * Metodo setTable_order
     * @param table_order
     */
    public void setTable_order(TableOrder table_order) {this.table_order = table_order;}
    /*
     * Metodo getContact_info
     * @return contact_info
     */
    public ContactInfo getContact_info() {return contact_info;}
    /*
     * Metodo setContact_info
     * @param contact_info
     */
    public void setContact_info(ContactInfo contact_info) {this.contact_info = contact_info;}
    /*
     * Metodo getPreferences
     * @return preferences
     */
    public Preferences getPreferences() {return preferences;}
    /*
     * Metodo setPreferences
     * @param preferences
     */
    public void setPreferences(Preferences preferences) {this.preferences = preferences;}
}

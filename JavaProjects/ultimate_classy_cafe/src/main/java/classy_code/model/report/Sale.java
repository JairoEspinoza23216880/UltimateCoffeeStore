//Revision 1.0 (Pendiente Revision Final)
package classy_code.model.report;

// Library Imports
import java.util.GregorianCalendar;

// Local Imports
import classy_code.model.order.TableOrder;

/*
 * Clase Sale
 */
public class Sale {
    // Atributos
    private int id;
    private GregorianCalendar date;
    private TableOrder order;

    // Constructor
    /*
     * Constructor de la clase Sale
     * @param order
     */
    public Sale(TableOrder order) {
        this.order = order;
        this.date = new GregorianCalendar();
    }

    // Getters y Setters
    /*
     * Metodo getId
     * Devuelve el id de la venta
     * @return id
     */
    public int getId() {
        return id;
    }
    /*
     * Metodo setId
     * Establece el id de la venta
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    /*
     * Metodo getDate
     * Devuelve la fecha de la venta
     * @return date
     */
    public GregorianCalendar getDate() {
        return date;
    }
    /*
     * Metodo setDate
     * Establece la fecha de la venta
     * @param date
     */
    public TableOrder getOrder() {
        return order;
    }
    /*
     * Metodo setOrder
     * Establece la orden de la venta
     * @param order
     */
    public void setOrder(TableOrder order) {
        this.order = order;
    }
}

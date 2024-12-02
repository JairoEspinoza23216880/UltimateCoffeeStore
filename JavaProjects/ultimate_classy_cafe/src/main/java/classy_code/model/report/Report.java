//Revision 1.0 (Pendiente Revision Final)
package classy_code.model.report;

// Library Imports
import java.util.ArrayList;
import java.util.GregorianCalendar;

// Local Imports
import classy_code.model.menu.Product;

/*
 * clase Report
 */
public class Report {
    // Atributos
    private int id;
    private GregorianCalendar date;
    private ArrayList<Product> product_list;

    // Constructor
    /*
     * Constructor de la clase Report
     * @param id
     * @param product_list
     */
    public Report(int id, ArrayList<Product> product_list) {
        this.id = id;
        this.date = new GregorianCalendar();
        this.product_list = new ArrayList<Product>();
    }

    // Getters y Setters
    /*
     * Metodo getId
     * Devuelve el id del reporte
     * @return id
     */
    public int getId() {return id;}
    /*
     * Metodo setId
     * Establece el id del reporte
     * @param id
     */
    public void setId(int id) {this.id = id;}
    /*
     * Metodo getDate
     * Devuelve la fecha del reporte
     * @return date
     */
    public GregorianCalendar getDate() {return date;}
    /*
     * Metodo setDate
     * Establece la fecha del reporte
     * @param date
     */
    public ArrayList<Product> getProduct_list() {return product_list;}
}

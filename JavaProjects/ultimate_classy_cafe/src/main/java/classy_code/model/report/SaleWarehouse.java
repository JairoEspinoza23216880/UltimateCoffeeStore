//Revision 1.0 (Pendiente Revision Final)
package classy_code.model.report;

// Library Imports
import java.util.ArrayList;

// Local Imports
public class SaleWarehouse {
    // Atributos
    private ArrayList<Sale> sale_list;

    // Constructor
    /*
     * Constructor de la clase SaleWarehouse
     */
    public SaleWarehouse() {
        this.sale_list = new ArrayList<Sale>();
    }

    // Getters y Setters
    /*
     * Metodo getSale_list
     * Devuelve la lista de ventas
     * @return sale_list
     */
    public ArrayList<Sale> getSale_list() {
        return sale_list;
    }

    //Metodos
    /*
     * Metodo addSale
     * Agrega una venta a la lista de ventas
     * @param sale
     */
    public void addSale(Sale sale) {
        sale_list.add(sale);
    }
    /*
     * Metodo removeSale
     * Elimina una venta de la lista de ventas
     * @param id
     */
    public void removeSale(int id) {
        this.sale_list.remove(this.findSale(id));
    }
    /*
     * Metodo findSale
     * Busca una venta en la lista de ventas
     * @param id
     */
    public Sale findSale(int id) {
        for (Sale sale : sale_list) {
            if (sale.getId() == id) {
                return sale;
            }
        }
        return null;
    }
}

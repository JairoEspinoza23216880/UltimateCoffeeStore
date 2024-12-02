//Revision 1.0 (Pendiente Revision Final)
package classy_code.model.customer;

//Library Imports
import java.util.ArrayList;

//Local Imports
import classy_code.model.menu.Product;
import classy_code.model.table.Table;

/*
 * Clase Preferences
 */
public class Preferences {
    //Atributos
    private ArrayList<Product> favProduct_list;
    private Table favTable;

    //Constructor
    /*
     * Constructor de la clase Preferences
     * @param favProduct_list
     * @param favTable
     */
    public Preferences() {
        this.favProduct_list = new ArrayList<Product>();
        this.favTable = null;
    }

    //Getters y Setters
    /*
     * Metodo getFavTable
     * @return favTable
     */
    public Table getFavTable() {return favTable;}
    /*
     * Metodo setFavTable
     * @param favTable
     */
    public void setFavTable(Table favTable) {this.favTable = favTable;}
    /*
     * Metodo getFavProduct_list
     * @return favProduct_list
     */
    public ArrayList<Product> getFavProduct_list() {return favProduct_list;}


    //Metodos
    /*
     * Metodo addFavProduct
     * @param product
     */
    public void addFavProduct(Product product) {
        favProduct_list.add(product);
    }

    /*
     * Metodo removeFavProduct
     * @param product
     */
    public void removeFavProduct(String name) {
        favProduct_list.remove(findFavProduct(name));
    }

    /*
     * Metodo findFavProduct
     * @param name
     * @return product
     */
    public Product findFavProduct(String name) {
        for (Product product : favProduct_list) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }
}

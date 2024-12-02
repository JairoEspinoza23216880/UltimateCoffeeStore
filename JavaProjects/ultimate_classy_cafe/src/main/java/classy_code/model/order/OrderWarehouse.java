//Revision 1.0 (Pendiente Revision Final)
package classy_code.model.order;

//Library Imports
import java.util.ArrayList;

/*
 * Clase OrderWarehouse
 */
public class OrderWarehouse {
    //Atributos
    private ArrayList<TableOrder> order_list;

    //Constructor
    /*
     * Constructor OrderWarehouse
     * Inicializa la lista de ordenes
     */
    public OrderWarehouse() {
        order_list = new ArrayList<TableOrder>();
    }

    //Metodos
    /*
     * metodo getOrderList
     * Devuelve la lista de ordenes
     * @return: lista de ordenes
     */
    public void addOrder(TableOrder order) {
        order_list.add(order);
    }
    /*
     * metodo addOrder
     * Agrega una orden a la lista
     * @param order: orden a agregar
     */
    public void removeOrder(int table_id) {
        this.order_list.remove(findOrder(table_id));
    }
    /*
     * metodo removeOrder
     * Remueve una orden de la lista
     * @param order: orden a remover
     */
    public TableOrder findOrder(int table_id) {
        for (TableOrder order : order_list) {
            if (order.getTable().getId() == table_id) {
                return order;
            }
        }
        return null;
    }

}

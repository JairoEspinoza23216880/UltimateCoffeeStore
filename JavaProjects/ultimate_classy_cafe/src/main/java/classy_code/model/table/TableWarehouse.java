//Revision 1.0 (Pendiente Revision Final)
package classy_code.model.table;

import java.util.ArrayList;

/*
 * Clase TableWarehouse
 */
public class TableWarehouse {
    //Atributos
    private ArrayList<Table> table_list;

    //Constructor
    /*
     * Constructor de la clase TableWarehouse
     */
    public TableWarehouse() {
        table_list = new ArrayList<Table>();
    }

    //Metodos
    /*
     * Metodo getTableList
     * Método que retorna la lista de mesas
     * @return table_list
     */
    public ArrayList<Table> getTableList() {
        return table_list;
    }

    /*
     * Metodo addTable
     * Método que agrega una mesa
     * @param table
     */
    public void addTable(Table table) {
        table_list.add(table);
    }

    /*
     * Metodo removeTable
     * Método que remueve una mesa
     * @param id
     */
    public void removeTable(int id) {
        table_list.remove(findTable(id));
    }

    /*
     * Metodo findTable
     * Método que busca una mesa
     * @param id
     * @return table
     */
    public Table findTable(int id){
        for(Table table : table_list){
            if(table.getId() == id){
                return table;
            }
        }
        return null;
    }
}

//Revision 1.0 (Pendiente Revision Final)
package classy_code.model.table;

//Library Imports
import java.util.ArrayList;

/*
 * Clase Table
 */
public class Table {
    //Atributos
    private int id;
    private ArrayList<Chair> chair_list;

    //Constructor
    /*
     * Constructor de la clase Table
     * @param id
     */
    public Table(int id) {
        this.id = id;
        this.chair_list = new ArrayList<Chair>();
    }

    //Getters y Setters

    /*
     * Metodo getChairList
     * Método que retorna la lista de sillas
     * @return chair_list
     */
    public ArrayList<Chair> getChairList() {
        return chair_list;
    }

    /*
     * Metodo getId
     * Método que retorna el id
     * @return id
     */
    public int getId() {return id;}
    /*
     * Metodo setId
     * Método que establece el id
     * @param id
     */
    public void setId(int id) {this.id = id;}

    //Metodos
    /*
     * Metodo getChairList
     * Método que retorna la lista de sillas
     * @return chair_list
     */
    public void addChair(Chair chair) {
        chair_list.add(chair);
    }

    /*
     * Metodo removeChair
     * Método que remueve una silla
     * @param id
     */
    public void removeChair(int id) {
        chair_list.remove(findChair(id));
    }

    /*
     * Metodo findChair
     * Método que busca una silla
     * @param id
     * @return chair
     */
    public Chair findChair(int id) {
        for(Chair chair : chair_list) {
            if(chair.getId() == id) {
                return chair;
            }
        }
        return null;
    }
}

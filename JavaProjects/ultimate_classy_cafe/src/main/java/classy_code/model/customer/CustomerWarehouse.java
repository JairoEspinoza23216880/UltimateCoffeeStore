//Revision 1.0 (Pendiente Revision Final)
package classy_code.model.customer;

import java.util.ArrayList;

/*
 * Clase CustomerWarehouse
 */
public class CustomerWarehouse {
    //Atributos
    private ArrayList<Customer> customer_list;
    private ArrayList<Customer> habitual_costumer_list;
    private ArrayList<SpoiledProgram> spoiled_program_list;

    //Constructor
    /*
     * Constructor de la clase CustomerWarehouse
     */
    public CustomerWarehouse() {
        this.customer_list = new ArrayList<Customer>();
        this.habitual_costumer_list = new ArrayList<Customer>();
        this.spoiled_program_list = new ArrayList<SpoiledProgram>();
    }

    //Getters y Setters
    /*
     * Metodo getCustomerList
     * Metodo que retorna la lista de clientes
     * @return customer_list Lista de clientes
     */
    public ArrayList<Customer> getCustomerList() {
        return customer_list;
    }

    /*
     * Metodo getHabitualCostumerList
     * Metodo que retorna la lista de clientes habituales
     * @return habitual_costumer_list Lista de clientes habituales
     */
    public ArrayList<Customer> getHabitualCostumerList() {
        return habitual_costumer_list;
    }

    /*
     * Metodo getSpoiledProgramList
     * Metodo que retorna la lista de programas de beneficios
     * @return spoiled_program_list Lista de programas de beneficios
     */
    public ArrayList<SpoiledProgram> getSpoiledProgramList() {
        return spoiled_program_list;
    }

    //Metodos
    /*
     * Metodo addHabitualCostumer
     * Metodo que agrega un cliente habitual a la lista de clientes habituales
     * @param habitual_costumer Cliente habitual
     */
    public void addHabitualCostumer(Customer habitual_costumer) {
        habitual_costumer_list.add(habitual_costumer);
    }

    /*
     * Metodo removeHabitualCostumer
     * Metodo que remueve un cliente habitual de la lista de clientes habituales
     * @param name Nombre del cliente habitual
     */
    public void removeHabitualCostumer(String name) {
        habitual_costumer_list.remove(findHabitualCustomer(name));
    }

    /*
     * Metodo findHabitualCustomer
     * Metodo que busca un cliente habitual en la lista de clientes habituales
     * @param name Nombre del cliente habitual
     * @return Cliente habitual
     */
    public Customer findHabitualCustomer(String name) {
        for (int i = 0; i < habitual_costumer_list.size(); i++) {
            if (habitual_costumer_list.get(i).getCustomer_name().equals(name)) {
                return habitual_costumer_list.get(i);
            }
        }
        return null;
    }

    /*
     * Metodo addSpoiledProgram
     * Metodo que agrega un programa de beneficios a la lista de programas de beneficios
     * @param spoiled_program Programa de beneficios
     */
    public void addSpoiledProgram(SpoiledProgram spoiled_program) {
        spoiled_program_list.add(spoiled_program);
    }

    /*
     * Metodo removeSpoiledProgram
     * Metodo que remueve un programa de beneficios de la lista de programas de beneficios
     * @param name Nombre del programa de beneficios
     */
    public void removeSpoiledProgram(String name) {
        spoiled_program_list.remove(findSpoiledProgram(name));
    }

    /*
     * Metodo findSpoiledProgram
     * Metodo que busca un programa de beneficios en la lista de programas de beneficios
     * @param name Nombre del programa de beneficios
     * @return Programa de beneficios
     */
    public SpoiledProgram findSpoiledProgram(String name) {
        for (int i = 0; i < spoiled_program_list.size(); i++) {
            if (spoiled_program_list.get(i).getProgramName().equals(name)) {
                return spoiled_program_list.get(i);
            }
        }
        return null;
    }
}

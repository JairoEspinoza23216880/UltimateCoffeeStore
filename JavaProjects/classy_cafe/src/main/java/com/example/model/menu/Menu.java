//Revision 1.0 (Pendiente Revision Final)
package com.example.model.menu;

//Library Imports
import java.util.ArrayList;

/*
 * Clase Menu
 */
public class Menu {
    private String title;
    private ArrayList<Category> category_list;

    //Constructor
    /*
     * Constructor Menu
     * Inicializa el menu con un titulo
     * @param title: titulo del menu
     */
    public Menu(String title) {
        this.title = title;
        category_list = new ArrayList<Category>();
    }

    //Getters y Setters
    /*
     * metodo getTitle
     * Devuelve el titulo del menu
     * @return: titulo del menu
     */
    public String getTitle() {return title;}
    /*
     * metodo setTitle
     * Cambia el titulo del menu
     * @param title: nuevo titulo del menu
     */
    public void setTitle(String title) {this.title = title;}

    //Metodos
    /*
     * metodo addCategory
     * Agrega una categoria al menu
     * @param category: categoria a agregar
     */
    public void addCategory(Category category) {
        category_list.add(category);
    }

    /*
     * metodo removeCategory
     * Elimina una categoria del menu
     * @param category: categoria a eliminar
     */
    public void removeCategory(String title) {
        category_list.remove(findCategory(title));
    }

    /*
     * metodo findCategory
     * Busca una categoria en el menu
     * @param name: nombre de la categoria
     * @return: la categoria si la encuentra, null si no
     */
    public Category findCategory(String title) {
        for (Category category : category_list) {
            if (category.getTitle().equals(title)) {
                return category;
            }
        }
        return null;
    }
    /*
     * metodo stock_refresh
     * Actualiza el stock de todos los productos del menu
     * @Deprecated
     */
    public void stock_refresh() {
        for (Category category : category_list) {
            category.stock_refresh();
        }
    }
}

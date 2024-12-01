//Revision 1.0 (Pendiente Revision Final)
package com.example.model.menu;

/*
 * Interfaz Category
 */
public interface Category {
    //Getters y Setters
    /*
     * Getter de title
     * @return title
     */
    public String getTitle();
    /*
     * Setter de title
     * @param title Titulo de la categoria
     */
    public void setTitle(String title);

    //Metodos
    /*
     * Metodo addProduct
     * @param product Producto a agregar
     */
    public void addProduct(Product product);
    /*
     * Metodo removeProduct
     * @param product Producto a remover
     */
    public void removeProduct(String name);
    /*
     * Metodo findProduct
     * @param name Nombre del producto a buscar
     */
    public Product findProduct(String name);
    /*
     * Metodo stock_refresh
     * Actualiza el stock de los productos
     */
    public void stock_refresh();
}

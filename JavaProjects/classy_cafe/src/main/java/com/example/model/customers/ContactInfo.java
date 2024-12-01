//Revision 1.0 (Pendiente Revision Final)
package com.example.model.customers;

/*
 * Clase ContactInfo
 */
public class ContactInfo {
    //Atributos
    private String adress;
    private int phone_num;
    private String email;

    //Constructor
    /*
     * Constructor de la clase ContactInfo
     * @param adress
     * @param phone_num
     * @param email
     */
    public ContactInfo(String adress, int phone_num, String email) {
        this.adress = adress;
        this.phone_num = phone_num;
        this.email = email;
    }

    //Getters y Setters
    /*
     * Metodo getAdress
     * @return adress
     */
    public String getAdress() {return adress;}
    /*
     * Metodo setAdress
     * @param adress
     */
    public void setAdress(String adress) {this.adress = adress;}
    /*
     * Metodo getPhone_num
     * @return phone_num
     */
    public int getPhone_num() {return phone_num;}
    /*
     * Metodo setPhone_num
     * @param phone_num
     */
    public void setPhone_num(int phone_num) {this.phone_num = phone_num;}
    /*
     * Metodo getEmail
     * @return email
     */
    public String getEmail() {return email;}
    /*
     * Metodo setEmail
     * @param email
     */
    public void setEmail(String email) {this.email = email;}
}

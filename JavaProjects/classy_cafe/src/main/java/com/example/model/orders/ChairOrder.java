//Revision 1.0 (Pendiente Revision Final)
package com.example.model.orders;

import java.util.ArrayList;

//Local Imports
import com.example.model.menu.Drink;
import com.example.model.menu.Food;

/*
 * Clase ChairOrder
 */
public class ChairOrder{
    //Atributos
    private ArrayList<Food> foodList;
    private ArrayList<Drink> drinkList;
    private Chair chair;

    //Constructor
    /*
     * Constructor de la clase ChairOrder
     * @param chair
     */
    public ChairOrder(Chair chair){
        this.chair = chair;
        this.foodList = new ArrayList<Food>();
        this.drinkList = new ArrayList<Drink>();
    }

    //Getters y Setters
    /*
     * Metodo getChair
     * Método que retorna la silla
     * @return chair
     */
    public Chair getChair() {return chair;}
    /*
     * Metodo setChair
     * Método que establece la silla
     * @param chair
     */
    public void setChair(Chair chair) {this.chair = chair;}
    /*
     * Metodo getFoodList
     * Método que retorna la lista de comidas
     * @return foodList
     */
    public ArrayList<Food> getFoodList() {return foodList;}
    /*
     * Metodo getDrinkList
     * Método que retorna la lista de bebidas
     * @return drinkList
     */
    public ArrayList<Drink> getDrinkList() {return drinkList;}


    //Metodos
    /*
     * Metodo getPrice
     * Método que retorna el precio total de la orden
     * @return price
     */
    public double getPrice(){
        double price = 0;
        for(Food food : foodList){
            price += food.getPrice();
        }
        for(Drink drink : drinkList){
            price += drink.getPrice();
        }
        return price;
    }
    /*
     * Metodo addFood
     * Método que agrega una comida a la lista de comidas
     * @param food
     */
    public void addFood(Food food){
        foodList.add(food);
    }
    /*
     * Metodo addDrink
     * Método que agrega una bebida a la lista de bebidas
     * @param drink
     */
    public void addDrink(Drink drink){
        drinkList.add(drink);
    }
    /*
     * Metodo removeFood
     * Método que remueve una comida de la lista de comidas
     * @param food
     */
    public void removeFood(String name){
        this.foodList.remove(findFood(name));
    }
    /*
     * Metodo removeDrink
     * Método que remueve una bebida de la lista de bebidas
     * @param drink
     */
    public void removeDrink(String name){
        this.drinkList.remove(findDrink(name));
    }
    /*
     * Metodo findFood
     * Método que busca una comida en la lista de comidas
     * @param name
     */
    public Food findFood(String name){
        for(Food food : foodList){
            if(food.getName().equals(name)){
                return food;
            }
        }
        return null;
    }
    /* 
     * Metodo findDrink
     * Método que busca una bebida en la lista de bebidas
     * @param name
     */
    public Drink findDrink(String name){
        for(Drink drink : drinkList){
            if(drink.getName().equals(name)){
                return drink;
            }
        }
        return null;
    }
}
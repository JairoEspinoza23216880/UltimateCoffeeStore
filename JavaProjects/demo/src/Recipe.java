import java.util.ArrayList;

public class Recipe {
    ArrayList<Ingredient> ingredients_list;
    ArrayList<Double> amount_list;

    /*
     * Constructor Recipe
     * Inicializa la receta con una lista de ingredientes y una lista de cantidades
     */
    public Recipe() {
        this.ingredients_list = new ArrayList<>();
        this.amount_list = new ArrayList<>();
    }

    /*
     * metodo addIngredient
     * Agrega un ingrediente a la receta
     * @param ingredient: ingrediente a agregar
     * @param amount: cantidad de ingrediente a agregar
     */
    public void addIngredient(Ingredient ingredient, Double amount) {
        ingredients_list.add(ingredient);
        amount_list.add(amount);
    }

    /*
     * metodo findIngredient
     * Busca un ingrediente en la receta
     * @param name: nombre del ingrediente
     * @return: el ingrediente si lo encuentra, null si no
     */
    public Ingredient findIngredient(String name) {
        for (Ingredient ingredient : ingredients_list) {
            if (ingredient.getName().equals(name)) {
                return ingredient;
            }
        }
        return null;
    }

    /*
     * metodo findIngredientAmount
     * Busca la cantidad de un ingrediente en la receta
     * @param name: nombre del ingrediente
     * @return: la cantidad del ingrediente si lo encuentra, null si no
     */
    public Double findIngredientAmount(String name) {
        for (int i = 0; i < ingredients_list.size(); i++) {
            if (ingredients_list.get(i).getName().equals(name)) {
                return amount_list.get(i);
            }
        }
        return null;
    }
}

import java.util.ArrayList;

/*
 * Clase IngredientController
 */
public class IngredientController {
    public ArrayList<Ingredient> ingredients_list;
    /*
     * Constructor IngredientController
     * Inicializa la lista de ingredientes
     */
    public IngredientController() {
        this.ingredients_list = new ArrayList<>();
    }
    
    /*
     * Metodo addIngredient
     * Agrega un ingrediente a la lista de ingredientes
     * @param name: nombre del ingrediente
     * @param stock: cantidad de stock del ingrediente
     */
    public void addIngredient(String name, Double stock) {
        try {
            this.ingredients_list.add(new Ingredient(name, stock));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /*
     * Metodo findIngredient
     * Busca un ingrediente en la lista de ingredientes
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
}







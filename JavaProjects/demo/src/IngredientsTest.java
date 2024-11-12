import org.junit.*;


/**
 * Esta clase es la encargada de probar la clase IngredientController
 */
public class IngredientsTest {
    
    
    @Test
    /*
     * Este metodo se encarga de probar el metodo addIngredient de la clase IngredientController
     */
    public void test() {
        IngredientController ig = new IngredientController();
        ig.addIngredient("Azafran", 400.0);
        Assert.assertEquals("Azafran", ig.findIngredient("Azafran").getName());
    }

    
    @Test
    /*
     * Este metodo se encarga de probar el guardado del stock del método addIngredient de la clase IngredientController
     */
    public void test2() {
        IngredientController ig = new IngredientController();
        ig.addIngredient("Salsa Bolognesa", 400.0);
        Assert.assertEquals(400.0, ig.findIngredient("Salsa Bolognesa").getStock(), 0.0);
    }
}

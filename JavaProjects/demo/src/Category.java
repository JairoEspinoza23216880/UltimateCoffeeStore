import java.util.ArrayList;

public class Category {
    //Atributos
    private String title;
    private ArrayList<Product> product_list = new ArrayList<>();

    //Constructores
    public Category() {}
    public Category(String title) throws IllegalArgumentException {
        this.title = title;
    }

    //GetySet
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    //Metodos
    public void addProduct(Product product) {
        product_list.add(product);
    }
    public Product findProduct(String name) {
        for (Product product : product_list) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }
    public void stock_refresh() {
        for (Product product : product_list) {
            product.stock_refresh();
        }
    }
}
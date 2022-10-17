package StProject;



import java.util.List;


public class UserCart {
    private int id;
    private int userId;
    private List<ProductsCart> cartProductsList;

    public List<ProductsCart> getCartProductsList() {
        return cartProductsList;
    }

    public UserCart setCartProductsList(List<ProductsCart> cartProductsList) {
        this.cartProductsList = cartProductsList;
        return this;
    }

    public int getId() {
        return id;
    }

    public UserCart setId(int id) {
        this.id = id;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public UserCart setUserId(int userId) {
        this.userId = userId;
        return this;
    }




}

package StProject;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.NonUniqueResultException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
public class UserController {

    @GetMapping("/underconstruction")
    ModelAndView underConstructionPage() {
        return new ModelAndView("underConstruction.html");
    }

    @GetMapping("/login")
    ModelAndView loginPage() {
        return new ModelAndView("loginPage.html");

    }

    @GetMapping("/register")
    ModelAndView registerPage() {
        return new ModelAndView("registerPage.html");
    }


    @Autowired
    private UserDataAccesObject userDataAccesObject;


    @PostMapping("/register-action")
    ModelAndView registerAction(@RequestParam("email") String email,
                                @RequestParam("password") String password,
                                @RequestParam("name") String name,
                                @RequestParam("address") String address) {
                   User user = new User();
                   user.setEmail(email);
                   user.setPassword(password);
                   user.setName(name);
                   user.setAddress(address);
                   userDataAccesObject.save(user);

                   return new ModelAndView(("redirect:/login"));
               }



    @PostMapping("/login-action")
    ModelAndView loginAction(@RequestParam("email") String email,
                             @RequestParam("password") String password) {
        User userByEmail = userDataAccesObject.findByEmail(email);


        try {
            if (userByEmail.getPassword().equals(password)) {
                return new ModelAndView(("redirect:/inStore"));
            } else {
                return new ModelAndView(("redirect:/login"));
            }
        }
     catch(NullPointerException e) {return new ModelAndView(("redirect:/login"));
        }
    }

    @GetMapping("/inStore")
    public ModelAndView inStore() {
        RestTemplate restTemplate = new RestTemplate();
        UserCart userCart = new UserCart();
        userCart.setUserId(1);
        userCart = restTemplate.postForObject("http://localhost:8084/cart", userCart, UserCart.class);

        int cartSize = userCart.getCartProductsList().size();


        ResponseEntity<Products[]> responseEntity = restTemplate.getForEntity("http://localhost:8082/productsList", Products[].class);
        Products[] allProducts = responseEntity.getBody();
        ModelAndView modelAndView = new ModelAndView("inStore.html");
        modelAndView.addObject("products", allProducts);
        modelAndView.addObject("cartSize", cartSize);
        return modelAndView;
    }

    //apel serviciu web si citit raspunsul
    @PostMapping("/buy")
    //comunicare cu apl. CartShop - info despre cos cumparaturi
    public ModelAndView buy(@RequestParam("id") Integer id) {
        //1. sa aflam cosul de cumparaturi pt user-ul curent
        RestTemplate restTemplate = new RestTemplate();
        UserCart userCart = new UserCart();
        userCart.setUserId(1);
        userCart  = restTemplate.postForObject("http://localhost:8084/cart", userCart, UserCart.class);

        //2. sa adaugam noul produs in cosul de cumparaturi
//
        Products product = restTemplate.getForObject("http://localhost:8082/productsList/" +id, Products.class);
        ProductsCart productsCart= new ProductsCart();
        productsCart.setName(product.getProductName());
        productsCart.setDescription(product.getProductDescription());
        productsCart.setPrice(product.getProductPrice());
        restTemplate.postForObject("http://localhost:8084/cart/" + userCart.getId() + "/products", productsCart, UserCart.class);


        return new ModelAndView(("redirect:/inStore"));


    }
}
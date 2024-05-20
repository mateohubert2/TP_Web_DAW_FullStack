import java.sql.SQLException;
import java.util.ArrayList;

import controllers.ProductsController;
import dao.ProductsDAO;
import models.Product;
import webserver.WebServer;
import webserver.WebServerContext;

public class App {
    public static void main(String[] args) throws Exception {
       ProductsDAO productDAO = new ProductsDAO();
       ArrayList<Product> allProduct = productDAO.findAll();
       System.out.println("Voici tous les produits de la BDD:");
       for(int i = 0; i < allProduct.size(); i++){
            System.out.println("Le produit ayant pour id: " + allProduct.get(i).id() + " est: " + allProduct.get(i).name() + " possédé par: "
            + allProduct.get(i).owner() + " et coute: " + allProduct.get(i).bid() + "€");
       }
       WebServer webserver = new WebServer();
       webserver.listen(8080);
       webserver.getRouter().get("/products", (WebServerContext context) -> { ProductsController.findAll(context);});
    }
}

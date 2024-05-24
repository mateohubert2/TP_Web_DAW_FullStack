package controllers;

import java.util.ArrayList;

import dao.ProductsDAO;
import models.Product;
import webserver.WebServerContext;
import webserver.WebServerRequest;
import webserver.WebServerResponse;

public class ProductsController {
    public static ArrayList<Product> findAll(WebServerContext context){
        WebServerResponse response = context.getResponse();
        ProductsDAO productDAO = new ProductsDAO();
        response.json(productDAO.findAll());
        return productDAO.findAll();
    }
    public static boolean bid(WebServerContext context){
        ProductsDAO productDAO = new ProductsDAO();
        String result;
        WebServerRequest request = context.getRequest();
        String parametre = request.getParam("productId");
        try {
            result = productDAO.bid(Integer.parseInt(parametre));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            result = null;
        }
        WebServerResponse response = context.getResponse();
        if(result != "null"){
            //response.ok("Le prix à bien été incrémenté");
            String jsonString = "{\"bid\":\""+result+"\"}";
            //System.out.println(jsonString);
            response.json(jsonString);
            return true;
        }
        else{
            response.serverError("Impossible de modifier le prix");
            return false;
        }
    }
}

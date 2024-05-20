package controllers;

import java.util.ArrayList;

import dao.ProductsDAO;
import models.Product;
import webserver.WebServerContext;
import webserver.WebServerResponse;

public class ProductsController {
    public static ArrayList<Product> findAll(WebServerContext context){
        WebServerResponse response = context.getResponse();
        ProductsDAO productDAO = new ProductsDAO();
        response.json(productDAO.findAll());
        return null;
    }
}

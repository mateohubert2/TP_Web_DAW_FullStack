package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.PolyBayDatabase;
import models.Product;

public class ProductsDAO {
    public ProductsDAO(){

    }
    public ArrayList<Product> findAll(){
        PolyBayDatabase poly_bay;
        try {
            poly_bay = new PolyBayDatabase();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            poly_bay = null;
        }
        ArrayList<Product> products = new ArrayList<Product>();
        String sqlquery = "SELECT * FROM `product` ORDER BY `name` ASC;";
        PreparedStatement myPreparedStatement;
        try {
            myPreparedStatement = poly_bay.prepareStatement(sqlquery);
        } catch (SQLException e) {
            System.err.println("Impossible de préparer la requête:");
            System.err.println(e.getMessage());
            myPreparedStatement = null;
        }
        try {
            ResultSet myResults = myPreparedStatement.executeQuery();
            while(myResults.next()){
                int id = myResults.getInt("id");
                String name = myResults.getString("name");
                String owner = myResults.getString("owner");
                float bid = myResults.getFloat("bid");
                Product product = new Product(id, name, owner, bid);
                products.add(product);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return products;
    }
}

package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public boolean bid(int id){
        PolyBayDatabase poly_bay;
        boolean result;
        try {
            poly_bay = new PolyBayDatabase();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            poly_bay = null;
        }
        String query = "SELECT `bid` FROM `product` WHERE `id` = ?;";
        PreparedStatement effectue;
        try {
            effectue = poly_bay.prepareStatement(query);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            effectue = null;
        }
        try {
            effectue.setInt(1, id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        ResultSet myResult;
        try {
            myResult = effectue.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            myResult = null;
        }
        float bid_precedent = 0;
        try {
            while(myResult.next()){
                bid_precedent = myResult.getFloat("bid");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            bid_precedent = 0;
        }

        String sqlquery = "UPDATE `product` SET `bid` = bid + 50 WHERE `id` = ?;";
        
        PreparedStatement myPreparedStatement;
        try {
            myPreparedStatement = poly_bay.prepareStatement(sqlquery);
        } catch (SQLException e) {
            System.err.println("Impossible de préparer la requête:");
            System.err.println(e.getMessage());
            myPreparedStatement = null;
        }
        try {
            myPreparedStatement.setInt(1, id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        try {
            result = myPreparedStatement.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        String query_after = "SELECT `bid` FROM `product` WHERE `id` = ?;";
        PreparedStatement effectue_after;
        try {
            effectue_after = poly_bay.prepareStatement(query_after);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            effectue_after = null;
        }
        try {
            effectue_after.setInt(1, id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        ResultSet myResult_after;
        try {
            myResult_after = effectue_after.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            myResult_after = null;
        }
        float bid_after = 0;
        try {
            while(myResult_after.next()){
                bid_after = myResult_after.getFloat("bid");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            bid_after = 0;
        }
        float requete_valide = bid_after - bid_precedent;
        if(requete_valide == 50){
            result = true;
        }
        else{
            result = false;
        }
        return result;
    }
}

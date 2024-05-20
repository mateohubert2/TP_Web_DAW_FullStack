import java.sql.SQLException;

import database.PolyBayDatabase;

public class App {
    public static void main(String[] args) throws Exception {
       try {
        PolyBayDatabase poly_bay = new PolyBayDatabase();
    } catch (SQLException e) {
        System.err.println(e.getMessage());
    }
    }
}

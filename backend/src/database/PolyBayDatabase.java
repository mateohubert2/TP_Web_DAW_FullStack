package database;

import java.sql.SQLException;

public class PolyBayDatabase extends MySQLDatabase{
    public PolyBayDatabase() throws SQLException{
        super("localhost", 3307, "poly_bay", "mateo", "esirem");
    }
}

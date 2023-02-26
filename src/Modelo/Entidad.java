package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Entidad {



    public Connection conectarBBDD(String user, String password) throws SQLException {

        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/lost", user, password);
        return c;
    }






}

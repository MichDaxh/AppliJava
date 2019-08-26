package DataAccessPackage;

import ExceptionPackage.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnexion {

    private static Connection uniqueConnection;

    public static Connection getInstance() throws SQLException{
            if (uniqueConnection == null){
                    uniqueConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java?serverTimezone=EST5EDT", "root", "mybest023");
            }
            return uniqueConnection;

    }
}

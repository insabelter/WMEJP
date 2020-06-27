package DataBase;

import javafx.scene.Scene;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainDBConnectTest {

    public static void main(String[] args) {
        Connection conn = null;
        DataManager dm = new DataManager();
        try{
            //Create DB Connection
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:C:/Users/neeli/Java/WMEJP/WirMachenEinJavaProjekt/src/main/resources/wmejpTest","sa","");

            dm.initializeAll(conn);

            conn.close();
        }catch(SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        } finally {
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            }
        }

    }
}

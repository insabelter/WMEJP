package test;

import classes.Fakultaet;
import classes.Studiengang;
import classes.Studienrichtung;

import java.sql.*;
import java.util.List;
import java.util.function.Function;

public class DBAcces_f {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./wmejpTest";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";


    public void fill(){
        List<Object> objektlist = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs =null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);


            stmt = conn.createStatement();
            String sql = "";
            rs = stmt.executeQuery(sql);

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();


        } catch(SQLException se) {

            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            }
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            }
        }

    };

}

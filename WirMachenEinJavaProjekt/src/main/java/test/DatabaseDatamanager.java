package test;

import classes.Fakultaet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDatamanager {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./wmejpTest";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";

    Connection conn = null;
    Statement stmt = null;

    public List<Fakultaet> getAll(){
        List<Fakultaet> lsFakultaet = new ArrayList<>();

        ResultSet rs =null;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //Fakultaeten list mit entsprehenedem Resultset füllen.
            rs = getAllX("fakultaet");
            //add objekts to List
            while(rs.next()){
                lsFakultaet.add(new Fakultaet(rs.getInt("FAKULTAET_ID"),rs.getString("name")));
            }



            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();

            //return list
            return lsFakultaet;
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
        return lsFakultaet;
    }
    private ResultSet getAllX(String klass) throws SQLException { //"SELECT * FROM "+klass+";"  -> gibt Resultset aud der DB zurück.'
        stmt = conn.createStatement();
        String sql =  "SELECT * FROM "+klass+";";
        return stmt.executeQuery(sql);
    }
}

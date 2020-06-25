package DataBase;

import java.sql.*;
import java.util.List;

public class DatabaseDatamanager {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:../wmejpTest";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";

    Connection conn = null;

    public void initializeAll(DataManager dm){

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 3: Fill Arrays

            //fill lsFirma
            dm.lsFirma.fillArray(conn);
            dm.lsFirma.printArray();
            //fill lsKurs
            dm.lsKurs.fillArray(conn);
            dm.lsKurs.printArray();
            //fill lsStudent
            dm.lsStudent.fillArray(conn);
            dm.lsStudent.printArray();
            //fill lsStudienrichtung
            dm.lsStudienrichtung.fillArray(conn);
            dm.lsStudienrichtung.printArray();
            //fill lsStudiengang
            dm.lsStudiengang.fillArray(conn);
            dm.lsStudiengang.printArray();
            //fill lsFakultaet
            dm.lsFakultaet.fillArray(conn);
            dm.lsFakultaet.printArray();

            // STEP 4: Clean-up environment
            conn.close();

        } catch(SQLException se) {

            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

    static public void printArray(List<?> list){
        for (Object x: list
             ) {
            System.out.println(x.toString());
        }
        System.out.println("");
    }
}
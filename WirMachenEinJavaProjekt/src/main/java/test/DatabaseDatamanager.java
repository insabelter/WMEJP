package test;

import classes.*;

import java.sql.*;
import java.util.ArrayList;
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

            //fill lsStudent
            Student.fillArray(dm.lsStudent, conn);
            printArray(dm.lsStudent);
            //fill lsFirma
            Firma.fillArray(dm.lsFirma, conn);
            printArray(dm.lsFirma);
            //fill lsKurs
            Kurs.fillArray(dm.lsKurs, conn);
            printArray(dm.lsKurs);
            //fill lsStudienrichtung
            Studienrichtung.fillArray(dm.lsStudienrichtung, conn);
            printArray(dm.lsStudienrichtung);
            //fill lsStudiengang
            Studiengang.fillArray(dm.lsStudiengang, conn);
            printArray(dm.lsStudiengang);
            //fill lsFakultaet
            Fakultaet.fillArray(dm.lsFakultaet, conn);
            printArray(dm.lsFakultaet);

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

package test;

import classes.*;

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

    List<Student> lsStudent = new ArrayList<>();
    List<Firma> lsFirma = new ArrayList<>();
    List<Kurs> lsKurs = new ArrayList<>();
    List<Studienrichtung> lsStudienrichtung = new ArrayList<>();
    List<Studiengang> lsStudiengang = new ArrayList<>();
    List<Fakultaet> lsFakultaet = new ArrayList<>();

    public List<Studiengang> getAll(){


        ResultSet rs =null;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 3: Fill Arrays

            //fill lsStudent
            Student.fillArray(lsStudent, conn);
            //fill lsFirma
            Firma.fillArray(lsFirma, conn);
            //fill lsKurs
            Kurs.fillArray(lsKurs, conn);
            //fill lsStudienrichtung
            Studienrichtung.fillArray(lsStudienrichtung, conn);
            //fill lsStudiengang
            Studiengang.fillArray(lsStudiengang, conn);
            //fill lsFakultaet
            Fakultaet.fillArray(lsFakultaet, conn);
            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();

            //return list
            return lsStudiengang;
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
        return lsStudiengang;
    }
    private ResultSet getAllX(String klasse) throws SQLException { //"SELECT * FROM "+klass+";"  -> gibt Resultset aud der DB zurück.'
        stmt = conn.createStatement();
        String sql =  "SELECT * FROM "+klasse+";";
        return stmt.executeQuery(sql);
    }
}

package DataBase;

import classes.Kurs;

import java.sql.*;
import java.util.List;

public class DatabaseDatamanager {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./wmejpTest";

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
            dm.lsFirma.fillArray(dm, conn);
            dm.lsKurs.fillArray(dm, conn);
            dm.lsStudent.fillArray(dm, conn);
            dm.lsStudienrichtung.fillArray(dm, conn);
            dm.lsStudiengang.fillArray(dm, conn);
            dm.lsFakultaet.fillArray(dm, conn);

//            for (Kurs kurs:dm.lsKurs.list
//                 ) {
//                kurs.createName();
//            }

            //Print
            dm.lsFirma.printArray();
            dm.lsKurs.printArray();
            dm.lsStudent.printArray();
            dm.lsStudienrichtung.printArray();
            dm.lsStudiengang.printArray();
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

    public void update(String TABLE,String SET, String WHERE){ //bsp:"UPDATE Fakultaet SET name='geaendert' WHERE Fakultaet_ID>20;"
        execStatement("UPDATE "+ TABLE + " SET "+ SET +" WHERE "+ WHERE+";");
    }
    public void insert(String TABLE,String COLUMNS, String VALUES){ //bsp:"INSERT INTO Fakultaet(name)VALUES('technik');"
        execStatement("INSERT INTO "+ TABLE + "("+ COLUMNS +")VALUES("+ VALUES+");");
    }
    public void delete(String TABLE, String WHERE){ //bsp:"DELETE FROM Fakultaet WHERE fakultaet_id>4"
        execStatement("DELETE FROM "+ TABLE +" WHERE "+WHERE+" ;");
    }

    //execStatement fürt ein beliebiges Statement ohne rückgabewert aus. Nutzung:savingfunction
    private void execStatement(String Statement){
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 3: Fill Arrays

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(Statement);

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
}

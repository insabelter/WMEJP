package DataBase;

import classes.*;

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
            dm.lsFakultaet.fillArray(dm, conn);
            dm.lsStudiengang.fillArray(dm, conn);
            dm.lsStudienrichtung.fillArray(dm, conn);
            dm.lsKurs.fillArray(dm, conn);
            dm.lsStudent.fillArray(dm, conn);

            for (Kurs kurs:dm.lsKurs.list
                 ) {
                kurs.createName();
            }

            //Print
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

    //wip
    public void delete(Object hasIDObjekt,DataManager dm){ //Übernimmt Objekt mit interface "hasID" un löscht es aus Übergebenen dm und DB

        String Table = null;

        if(hasIDObjekt instanceof HasID){

            int id = ((HasID) hasIDObjekt).getId();

            if(hasIDObjekt instanceof Student){
                Table = "STUDENT";
                dm.lsStudent.list.remove(hasIDObjekt);
                execStatement("DELETE FROM "+ Table +" WHERE MATRIKEL_NR="+id+";");
            }else{
                if(hasIDObjekt instanceof Kurs){
                    Table = "KURS";
                    dm.lsKurs.list.remove(hasIDObjekt);

                }else if(hasIDObjekt instanceof Studienrichtung){
                    Table = "STUDIENRICHTUNG";
                    dm.lsStudienrichtung.list.remove(hasIDObjekt);

                }else if(hasIDObjekt instanceof Studiengang){
                    Table = "STUDIENGANG";
                    dm.lsStudiengang.list.remove(hasIDObjekt);

                }else if(hasIDObjekt instanceof Fakultaet){
                    Table = "FAKULTAET";
                    dm.lsFakultaet.list.remove(hasIDObjekt);
                }
                execStatement("DELETE FROM "+ Table +" WHERE "+Table+"_ID ="+id+";");
            }

        }

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

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

    //legacy
    public void insert(String TABLE,String COLUMNS, String VALUES){ // legacy! bsp:"INSERT INTO Fakultaet(name)VALUES('technik');"
        execStatement("INSERT INTO "+ TABLE + "("+ COLUMNS +")VALUES("+ VALUES+");");
    }

    public void insert(Object hasIDObjekt,DataManager dm){ //inseriert das übergebene Objekt in DM und DB. Duplizierter code kann durch legacy-methode umgangen werden
        String Table = null;

        if(hasIDObjekt instanceof HasID){

            if(hasIDObjekt instanceof Student){
                Student castobj = ((Student) hasIDObjekt);
                dm.lsStudent.list.add(castobj);
                execStatement("INSERT INTO STUDENT(VORNAME,NACHNAME,JAVAKENNTNISSE,KURS_ID,FIRMA)VALUES('"+castobj.getVorname()+"','"+castobj.getNachname()+"',"+castobj.getJavakenntnisse()+","+castobj.getKurs().getId()+",'"+castobj.getFirma()+"');");

            }else if(hasIDObjekt instanceof Kurs){
                Kurs castobj = ((Kurs) hasIDObjekt);
                dm.lsKurs.list.add(castobj);
                execStatement("INSERT INTO KURS(JAHRGANG,NUMMER,RAUM,EMAILVERTEILER,STUDIENRICHTUNG_ID)VALUES("+castobj.getJahrgang()+","+castobj.getNummer()+",'"+castobj.getRaum()+"','"+castobj.getEmailVerteiler()+"',"+castobj.getStudienrichtung().getId()+");");

            }else if(hasIDObjekt instanceof Studienrichtung){
                Studienrichtung castobj = ((Studienrichtung) hasIDObjekt);
                dm.lsStudienrichtung.list.add(castobj);
                execStatement("INSERT INTO STUDIENRICHTUNG(NAME,KUERZEL,STUDIENGANG_ID)VALUES('"+castobj.getName()+"','"+castobj.getKuerzel()+"',"+castobj.getStudiengang().getId()+");");

            }else if(hasIDObjekt instanceof Studiengang){
                Studiengang castobj = ((Studiengang) hasIDObjekt);
                dm.lsStudiengang.list.add(castobj);
                execStatement("INSERT INTO STUDIENGANG(NAME,KUERZEL,STUDIENGANGSLEITER,FAKULTAET_ID)VALUES('"+castobj.getName()+"','"+castobj.getKuerzel()+"','"+castobj.getStudienGangsleiter()+"',"+castobj.getFakultaet().getId()+");");

            }else if(hasIDObjekt instanceof Fakultaet){
                Fakultaet castobj = ((Fakultaet) hasIDObjekt);
                dm.lsFakultaet.list.add(castobj);
                //execinsert("FAKULTAET","NAME","'"+ castobj.getName()+"'");
                execStatement("INSERT INTO FAKULTAET(NAME)VALUES('"+ castobj.getName()+"');");
            }
        }else{
            System.out.println("obj hat keine id");
        }
    }
    
    public void delete(Object hasIDObjekt,DataManager dm){ //Übernimmt Objekt mit interface "hasID" un löscht es aus übergebnene dm und DB

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

    //execStatement fürt ein beliebiges Statement ohne rückgabewert aus.
    private void execStatement(String Statement){

        //System.out.println(Statement);
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 3: ExexStatement

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

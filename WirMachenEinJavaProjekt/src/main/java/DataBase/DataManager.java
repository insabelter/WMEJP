package DataBase;

import classes.*;

import java.sql.*;
import java.util.List;

public class DataManager {

    public StudentList lsStudent = new StudentList();
    public KursList lsKurs = new KursList();
    public StudienrichtungList lsStudienrichtung = new StudienrichtungList();
    public StudiengangList lsStudiengang = new StudiengangList();
    public FakultaetList lsFakultaet = new FakultaetList();

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./wmejpTest";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";

    public Connection conn = null;

    public void createConnection(){

    }

    public void initializeAll(){

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 3: Fill Arrays
            lsFakultaet.fillArray(this);
            lsStudiengang.fillArray(this);
            lsStudienrichtung.fillArray(this);
            lsKurs.fillArray(this);
            lsStudent.fillArray(this);

            for (Kurs kurs:lsKurs.list
                 ) {
                kurs.createName();
            }

            //Print
            lsKurs.printArray();
            lsStudent.printArray();
            lsStudienrichtung.printArray();
            lsStudiengang.printArray();
            lsFakultaet.printArray();

            // STEP 4: Clean-up environment
            conn.close();

        } catch(SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        } finally {
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

//    public void update(String TABLE,String SET, String WHERE){ //bsp:"UPDATE Fakultaet SET name='geaendert' WHERE Fakultaet_ID>20;"
//        execStatement("UPDATE "+ TABLE + " SET "+ SET +" WHERE "+ WHERE+";");
//    }

//    //legacy
//    public void insert(String TABLE,String COLUMNS, String VALUES){ // legacy! bsp:"INSERT INTO Fakultaet(name)VALUES('technik');"
//        execStatement("INSERT INTO "+ TABLE + "("+ COLUMNS +")VALUES("+ VALUES+");");
//    }

    public void insert(HasID object){ //inseriert das übergebene Objekt in DM und DB. Duplizierter code kann durch legacy-methode umgangen werden
            if(object instanceof Student){
                Student castobj = ((Student) object);
                lsStudent.list.add(castobj);
                castobj.getKurs().addSlave(castobj);
                execStatement("INSERT INTO STUDENT(VORNAME,NACHNAME,JAVAKENNTNISSE,KURS_ID,FIRMA)VALUES('"+castobj.getVorname()+"','"+castobj.getNachname()+"',"+castobj.getJavakenntnisse()+","+castobj.getKurs().getId()+",'"+castobj.getFirma()+"');");
            }else if(object instanceof Kurs){
                Kurs castobj = ((Kurs) object);
                lsKurs.list.add(castobj);
                castobj.getStudienrichtung().addSlave(castobj);
                execStatement("INSERT INTO KURS(JAHRGANG,NUMMER,RAUM,EMAILVERTEILER,STUDIENRICHTUNG_ID)VALUES("+castobj.getJahrgang()+","+castobj.getNummer()+",'"+castobj.getRaum()+"','"+castobj.getEmailVerteiler()+"',"+castobj.getStudienrichtung().getId()+");");
        }
    }
    
    public void delete(HasID object){ //Übernimmt Objekt mit interface "hasID" un löscht es aus übergebnene dm und DB
            int id = object.getId();

            if(object instanceof Student){
                Student castobj = ((Student) object);
                lsStudent.list.remove(castobj);
                castobj.getKurs().getStudents().remove(castobj);
                execStatement("DELETE FROM STUDENT WHERE MATRIKEL_NR="+id+";");
            }else if(object instanceof Kurs){
                Kurs castobj = ((Kurs) object);
                lsKurs.list.remove(castobj);
                castobj.getStudienrichtung().getKurse().remove(castobj);
                execStatement("DELETE FROM KURS WHERE KURS_ID ="+id+";");
            }
    }

    //execStatement fürt ein beliebiges Statement ohne rückgabewert aus.
    public void execStatement(String Statement){

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

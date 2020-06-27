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

    public void initializeAll(Connection conn) throws SQLException{

        lsFakultaet.fillArray(this, conn);
        lsStudiengang.fillArray(this, conn);
        lsStudienrichtung.fillArray(this, conn);
        lsKurs.fillArray(this, conn);
        lsStudent.fillArray(this, conn);

        for (Kurs kurs:lsKurs.list
             ) {
            kurs.createName();
        }


        //Print
        /*
        lsKurs.printArray();
        lsStudent.printArray();
        lsStudienrichtung.printArray();
        lsStudiengang.printArray();
        lsFakultaet.printArray();
        */
    }

    public void insert(HasID object, Connection conn) throws SQLException{ //inseriert das übergebene Objekt in DM und DB. Duplizierter code kann durch legacy-methode umgangen werden
        Statement stmt = conn.createStatement();

        if(object instanceof Student){
            Student castobj = ((Student) object);
            lsStudent.list.add(castobj);
            castobj.getKurs().addSlave(castobj);
            stmt.executeUpdate("INSERT INTO STUDENT(VORNAME,NACHNAME,JAVAKENNTNISSE,KURS_ID,FIRMA)VALUES('"+castobj.getVorname()+"','"+castobj.getNachname()+"',"+castobj.getJavakenntnisse()+","+castobj.getKurs().getId()+",'"+castobj.getFirma()+"');");

        }else if(object instanceof Kurs){
            Kurs castobj = ((Kurs) object);
            lsKurs.list.add(castobj);
            castobj.getStudienrichtung().addSlave(castobj);
            stmt.executeUpdate("INSERT INTO KURS(JAHRGANG,NUMMER,RAUM,EMAILVERTEILER,STUDIENRICHTUNG_ID)VALUES("+castobj.getJahrgang()+","+castobj.getNummer()+",'"+castobj.getRaum()+"','"+castobj.getEmailVerteiler()+"',"+castobj.getStudienrichtung().getId()+");");
        }
    }
    
    public void delete(HasID object, Connection conn) throws SQLException{ //Übernimmt Objekt mit interface "hasID" un löscht es aus übergebnene dm und DB
        Statement stmt = conn.createStatement();
        int id = object.getId();


        if(object instanceof Student){
            Student castobj = ((Student) object);
            lsStudent.list.remove(castobj);
            castobj.getKurs().getStudents().remove(castobj);
            stmt.executeUpdate("DELETE FROM STUDENT WHERE MATRIKEL_NR="+id+";");
        }else if(object instanceof Kurs){
            Kurs castobj = ((Kurs) object);

            lsKurs.list.remove(castobj);
            castobj.getStudienrichtung().getKurse().remove(castobj);
            stmt.executeUpdate("DELETE FROM KURS WHERE KURS_ID ="+id+";");
        }
    }
    public void update(HasID object, Connection conn) throws SQLException{
        Statement stmt = conn.createStatement();
        int id = object.getId();

        if(object instanceof Student){
            Student castobj = ((Student) object);
            stmt.executeUpdate("UPDATE STUDENT SET vorname = '"+castobj.getVorname()+"', nachname = '"+castobj.getNachname()+"', javakenntnisse = "+castobj.getJavakenntnisse()+", kurs_id = "+castobj.getKurs().getId()+", firma = '"+castobj.getFirma() +"' WHERE MATRIKEL_NR="+id+";");
        }
    }

}

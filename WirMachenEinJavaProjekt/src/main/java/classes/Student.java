package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Student{
    //this is a very basic Student class just for testing purposes, WIP

    private String matrikelnummer;
    private String vorname;
    private String nachname;
    private int javakenntnisse;

    private Kurs kurs;
    private Firma firma;


    public Student(String matrikelnummer, String vorname, String nachname, int javakenntnisse, Kurs kurs, Firma firma){
        this.matrikelnummer = matrikelnummer;
        this.vorname = vorname;
        this.nachname = nachname;
        this.javakenntnisse = javakenntnisse;
        this.kurs = kurs;
        this.firma = firma;
    }

    public static void fillArray(List<Student> toFill, Connection conn) throws SQLException {

        Statement stmt = conn.createStatement();
        ResultSet rsBasicInformation = stmt.executeQuery("SELECT * FROM STUDENT");

        //add objects to List
        while(rsBasicInformation.next()){
            toFill.add(new Student(
                    String.valueOf(rsBasicInformation.getInt("MATRIKEL_NR")),
                    rsBasicInformation.getString("VORNAME"),
                    rsBasicInformation.getString("NACHNAME"),
                    rsBasicInformation.getInt("Javakenntnisse"),
                    null,
                    null));
        }

    }

    public String getMatrikelnummer() {
        return matrikelnummer;
    }

    public void setMatrikelnummer(String matrikelnummer) {
        this.matrikelnummer = matrikelnummer;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public int getJavakenntnisse() {
        return javakenntnisse;
    }

    public void setJavakenntnisse(int javakenntnisse) {
        this.javakenntnisse = javakenntnisse;
    }

    public Kurs getKurs() {
        return kurs;
    }

    public void setKurs(Kurs kurs) {
        this.kurs = kurs;
    }

    public Firma getFirma() {
        return firma;
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
    }

    @Override
    public String toString() {
        return "Student{" +
                "matrikelnummer='" + matrikelnummer + '\'' +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", javakenntnisse=" + javakenntnisse +
                ", kurs=" + kurs +
                ", firma=" + firma +
                '}';
    }
}

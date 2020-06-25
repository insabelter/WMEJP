package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Student implements HasID{
    //this is a very basic Student class just for testing purposes, WIP

    private int matrikelnummer;
    private String vorname;
    private String nachname;
    private int javakenntnisse;

    private Kurs kurs;
    private Firma firma;


    public Student(int matrikelnummer, String vorname, String nachname, int javakenntnisse, Kurs kurs, Firma firma){
        this.matrikelnummer = matrikelnummer;
        this.vorname = vorname;
        this.nachname = nachname;
        this.javakenntnisse = javakenntnisse;
        this.kurs = kurs;
        this.firma = firma;
    }

    public int getJavakenntnisse() {
        return javakenntnisse;
    }

    public void setJavakenntnisse(int javakenntnisse) {
        this.javakenntnisse = javakenntnisse;
    }

    public String getFirstname() {
        return vorname;
    }

    public void setFirstname(String firstname) {
        this.vorname = firstname;
    }

    public String getLastname() {
        return nachname;
    }

    public void setLastname(String lastname) {
        this.nachname = lastname;
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
    public int getId() {
        return matrikelnummer;
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

package classes;

import javafx.fxml.Initializable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

public class Student implements HasID{
    //this is a very basic Student class just for testing purposes, WIP

    private int matrikelnummer;
    private String vorname;
    private String nachname;
    private int javakenntnisse;

    private Kurs kurs;
    private String firma;


    public Student(int matrikelnummer, String vorname, String nachname, int javakenntnisse, Kurs kurs, String firma){
        this.matrikelnummer = matrikelnummer;
        this.vorname = vorname;
        this.nachname = nachname;
        this.javakenntnisse = javakenntnisse;
        this.kurs = kurs;
        this.firma = firma;
    }

    public Integer getMatrikelnummer() {
        return matrikelnummer;
    }

    public void setMatrikelnummer(Integer matrikelnummer) {
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

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
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
                ", kurs=" + kurs.getName() +
                ", firma=" + firma +
                '}';
    }

}

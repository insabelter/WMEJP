package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Firma implements HasID{
    private int id;
    private String name;
    private String ansprechpartner;
    private String adresse;
    private List<Student> studenten;

    public Firma(int id, String name, String ansprechpartner, String adresse, List<Student> studenten) {
        this.id = id;
        this.name = name;
        this.ansprechpartner = ansprechpartner;
        this.adresse = adresse;
        this.studenten = studenten;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return id;
    }

    public List<Student> getStudenten() {
        return studenten;
    }

    public void addSlave(Student student) {
        this.studenten.add(student);
        student.setFirma(this);
    }

    public void setStudenten(List<Student> studenten) {
        this.studenten = studenten;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String printUI() {
        return name;
    }

    @Override
    public String toString() {
        return "Firma{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ansprechpartner='" + ansprechpartner + '\'' +
                ", adresse='" + adresse + '\'' +
                ", studenten=" + studenten +
                '}';
    }
}

package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Firma {
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

    static public void fillArray(List<Firma> toFill, Connection conn) throws SQLException {

        Statement stmt = conn.createStatement();
        ResultSet rsBasicInformation = stmt.executeQuery("SELECT * FROM FIRMA");

        //add objects to List
        while(rsBasicInformation.next()){
            toFill.add(new Firma(
                    rsBasicInformation.getInt("FIRMA_ID"),
                    rsBasicInformation.getString("NAME"),
                    rsBasicInformation.getString("ANSPRECHPARTNER"),
                    rsBasicInformation.getString("ANSPRECHPARTNER"),
                    null));
        }

    }

    public String getName() {
        return name;
    }

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

    @Override
    public String toString() {
        return name;
    }

}

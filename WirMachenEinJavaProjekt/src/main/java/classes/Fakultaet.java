package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Fakultaet {
    private int id;
    private String name;
    private String verantwortlicher; //muss hinzugefügt werden in DB
    private List<Studiengang> studiengaenge;


    public Fakultaet(int id,String name,String verantwortlicher,List<Studiengang> studiengaenge){
        this.id = id;
        this.name=name;
        this.verantwortlicher = verantwortlicher;
        this.studiengaenge = studiengaenge;
    }

    static public void fillArray(List<Fakultaet> toFill, Connection conn) throws SQLException {

        Statement stmt = conn.createStatement();
        ResultSet rsBasicInformation = stmt.executeQuery("SELECT * FROM FAKULTAET");

        //add objects to List
        while(rsBasicInformation.next()){
            toFill.add(new Fakultaet(
                    rsBasicInformation.getInt("FAKULTAET_ID"),
                    rsBasicInformation.getString("NAME"),
                    rsBasicInformation.getString("VERANTWORTLICHER"),//ToDo!! Add to DB
                    null));
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Studiengang> getStudiengang() {
        return studiengaenge;
    }

    public void setStudiengang(List<Studiengang> studiengangs) {
        this.studiengaenge = studiengaenge;
    }

    public void addSlave(Studiengang studiengang) {
        System.out.println(studiengang.getName());
        this.studiengaenge.add(studiengang);
    }

    public String getVerantwortlicher() {
        return verantwortlicher;
    }

    public void setVerantwortlicher(String verantwortlicher) {
        this.verantwortlicher = verantwortlicher;
    }

    @Override
    public String toString() {
        return String.valueOf(id)+" "+name;
    }

}

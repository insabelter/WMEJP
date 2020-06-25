package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Fakultaet implements HasID{
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

    @Override
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
        studiengang.setFakultaet(this);
        this.studiengaenge.add(studiengang);
    }

    public String getVerantwortlicher() {
        return verantwortlicher;
    }

    public void setVerantwortlicher(String verantwortlicher) {
        this.verantwortlicher = verantwortlicher;
    }

    public String printUI() {
        return String.valueOf(id)+" "+name;
    }

    @Override
    public String toString() {
        return "Fakultaet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", verantwortlicher='" + verantwortlicher + '\'' +
                ", studiengaenge=" + studiengaenge +
                '}';
    }
}

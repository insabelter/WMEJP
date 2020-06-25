package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Studienrichtung implements HasID{
    private int id;
    private String name;
    private String kuerzel;
    private Studiengang studiengang;

    private List<Kurs> kurse;

    public Studienrichtung(int id, String name, String kuerzel, Studiengang studiengang, List<Kurs> kurse) {
        this.id = id;
        this.name = name;
        this.kuerzel = kuerzel;
        this.studiengang = studiengang;
        this.kurse = kurse;
    }

    public void addSlave(Kurs kurs) {
        this.kurse.add(kurs);
        kurs.setStudienrichtung(this);
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

    public String getKuerzel() {
        return kuerzel;
    }

    public void setKuerzel(String kuerzel) {
        this.kuerzel = kuerzel;
    }

    public Studiengang getStudiengang() {
        return studiengang;
    }

    public void setStudiengang(Studiengang studiengang) {
        this.studiengang = studiengang;
    }

    public List<Kurs> getKurse() {
        return kurse;
    }

    public void setKurse(List<Kurs> kurse) {
        this.kurse = kurse;
    }

    public String printUI() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Studienrichtung{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", kuerzel='" + kuerzel + '\'' +
                ", studiengang=" + studiengang +
                ", kurse=" + kurse +
                '}';
    }
}

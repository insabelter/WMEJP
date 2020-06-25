package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Studiengang implements HasID{
    private int id;
    private String name;
    private String kuerzel;
    private String studienGangsleiter;
    private Fakultaet fakultaet;
    private List<Studienrichtung>studienrichtungen;

    public Studiengang(int id, String name, String kuerzel, String studienGangsleiter, Fakultaet fakultaet, List<Studienrichtung> studienrichtungen) {
        this.id = id;
        this.name = name;
        this.kuerzel = kuerzel;
        this.studienGangsleiter = studienGangsleiter;
        this.fakultaet = fakultaet;
        this.studienrichtungen = studienrichtungen;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return id;
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

    public String getStudienGangsleiter() {
        return studienGangsleiter;
    }

    public void setStudienGangsleiter(String studienGangsleiter) {
        this.studienGangsleiter = studienGangsleiter;
    }

    public Fakultaet getFakultaet() {
        return fakultaet;
    }

    public void setFakultaet(Fakultaet fakultaet) {
        this.fakultaet = fakultaet;
    }

    public List<Studienrichtung>getStudienrichtungs() {
        return studienrichtungen;
    }


    public void setStudienrichtungs(List<Studienrichtung>studienrichtungs) {
        this.studienrichtungen = studienrichtungs;
    }

    public void addSlave(Studienrichtung studienrichtung) {
        this.studienrichtungen.add(studienrichtung);
        studienrichtung.setStudiengang(this);
    }

    public String printUI() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Studiengang{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", kuerzel='" + kuerzel + '\'' +
                ", studienGangsleiter='" + studienGangsleiter + '\'' +
                ", fakultaet=" + fakultaet.getName() +
                ", studienrichtungs=" + studienrichtungen +
                '}';
    }
}

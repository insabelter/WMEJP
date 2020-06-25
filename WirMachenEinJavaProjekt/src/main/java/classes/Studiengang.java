package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Studiengang {
    private int id;
    private String name;
    private String kuerzel;
    private String studienGangsleiter;
    private Fakultaet fakultaet;
    private List<Studienrichtung>studienrichtungs;

    public Studiengang(int id, String name, String kuerzel, String studienGangsleiter, Fakultaet fakultaet, List<Studienrichtung> studienrichtungs) {
        this.id = id;
        this.name = name;
        this.kuerzel = kuerzel;
        this.studienGangsleiter = studienGangsleiter;
        this.fakultaet = fakultaet;
        this.studienrichtungs = studienrichtungs;
    }

    static public void fillArray(List<Studiengang> toFill, Connection conn) throws SQLException {

        Statement stmt = conn.createStatement();
        ResultSet rsBasicInformation = stmt.executeQuery("SELECT * FROM STUDIENGANG");

        //add objects to List
        while (rsBasicInformation.next()) {
            toFill.add(new Studiengang(
                    rsBasicInformation.getInt("STUDIENGANG_ID"),
                    rsBasicInformation.getString("NAME"),
                    rsBasicInformation.getString("KUERZEL"),
                    rsBasicInformation.getString("STUDIENGANGSLEITER"),
                    null,
                    null));
        }
    }


    public String getName() {
        return name;
    }

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
        return studienrichtungs;
    }


    public void setStudienrichtungs(List<Studienrichtung>studienrichtungs) {
        this.studienrichtungs = studienrichtungs;
    }

    public void addSlave(Studienrichtung studienrichtung) {
        this.studienrichtungs.add(studienrichtung);
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
                ", fakultaet=" + fakultaet +
                ", studienrichtungs=" + studienrichtungs +
                '}';
    }
}

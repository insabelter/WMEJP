package classes;

import java.util.List;

public class Studiengang {
    private int id;
    private String name;
    private String kuerzel;
    private String studienGangsleiter;
    private Fakultaet fakultaet;
    private List<Studienrichtung>studienrichtungs;


    public Studiengang(String abkuerzung, List<Studienrichtung> studienrichtungs){
        this.name = abkuerzung;
        this.studienrichtungs = studienrichtungs;
        this.studienrichtungs = studienrichtungs;
    }
    public Studiengang(int id,String name,String kuerzel,String studienGangsleiter){
        this.id=id;
        this.name = name;
        this.kuerzel = kuerzel;
        this.studienGangsleiter =studienGangsleiter;

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

    @Override
    public String toString() {
        return this.name;
    }
}

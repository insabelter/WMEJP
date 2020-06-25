package classes;

import java.util.Arrays;
import java.util.List;

public class Studienrichtung {
    private int id;
    private String name;
    private String kuerzel;
    private Studiengang studiengang;
    private List<Kurs> kurse;

    public Studienrichtung(String name, List<Kurs> kurse){
        this.name = name;
        this.kurse = kurse;

        for (Kurs k:this.kurse) {
            k.setStudienrichtung(this);
        }
    }

    public int getId() {
        return id;
    }

    public Studienrichtung(int id, String name){
        this.id = id;
        this.name = name;

    }
    public void addSlave(Kurs kurs) {
        this.kurse.add(kurs);
        kurs.setStudienrichtung(this);
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

    @Override
    public String toString() {
        return this.name;
    }
}

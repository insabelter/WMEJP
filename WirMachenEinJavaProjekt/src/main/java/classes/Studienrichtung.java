package classes;

import java.util.Arrays;

public class Studienrichtung {
    private String name;
    private String kuerzel;
    private Studiengang studiengang;
    private Kurs kurse[];

    public Studienrichtung(String name,Kurs[] kurse){
        this.name = name;
        this.kurse = kurse;

        for (Kurs k:this.kurse) {
            k.setStudienrichtung(this);
        }
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

    public Kurs[] getKurse() {
        return kurse;
    }

    public void setKurse(Kurs[] kurse) {
        this.kurse = kurse;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

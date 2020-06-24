package classes;

public class Studienrichtung {
    private String name;
    private String kuerzel;
    private Studiengang studiengang;
    private Kurs kurse[]= new Kurs[100];

    public Studienrichtung(String name){
        this(name,new Studiengang("-"));
    }
    public Studienrichtung(String name, Studiengang s){
        this.name = name;
        this.studiengang = s;
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
}

package classes;

public class Studiengang {
    private String name;
    private String kuerzel;
    private String studienGangsleiter;
    private Fakultaet fakultaet;

    public Studiengang(String name){
        this(name,new Fakultaet("-"));
    }
    public Studiengang(String name,Fakultaet f){
        this.name = name;
        this.fakultaet = f;
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
}

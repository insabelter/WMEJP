package classes;

public class Studiengang {
    private int id;
    private String name;
    private String kuerzel;
    private String studienGangsleiter;
    private Fakultaet fakultaet;
    private Studienrichtung[] studienrichtungs;


    public Studiengang(String abkuerzung,Studienrichtung[] studienrichtungs){
        this.name = abkuerzung;
        this.studienrichtungs = studienrichtungs;

        for (Studienrichtung s:this.studienrichtungs) {
            s.setStudiengang(this);
        }
    }
    public Studiengang(int id,String abkuerzung,Studienrichtung[] studienrichtungs){
        this.id=id;
        this.name = abkuerzung;
        this.studienrichtungs = studienrichtungs;

        for (Studienrichtung s:this.studienrichtungs) {
            s.setStudiengang(this);
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

    public Studienrichtung[] getStudienrichtungs() {
        return studienrichtungs;
    }

    public void setStudienrichtungs(Studienrichtung[] studienrichtungs) {
        this.studienrichtungs = studienrichtungs;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

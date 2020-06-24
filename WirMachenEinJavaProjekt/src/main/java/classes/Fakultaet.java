package classes;

public class Fakultaet {
    private int id;
    private String name;
    private Studiengang studiengang[];
    private String verantwortlicher;

    public Fakultaet(String name,Studiengang[] studiengangs){
        this.name=name;
        this.studiengang = studiengangs;

        for (Studiengang s :this.studiengang) {
            s.setFakultaet(this);
        }
    }

    public Fakultaet(int id,String name){
        this.id = id;
        this.name=name;

        for (Studiengang s :this.studiengang) {
            s.setFakultaet(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Studiengang[] getStudiengang() {
        return studiengang;
    }

    public void setStudiengang(Studiengang[] studiengang) {
        this.studiengang = studiengang;
    }

    public String getVerantwortlicher() {
        return verantwortlicher;
    }

    public void setVerantwortlicher(String verantwortlicher) {
        this.verantwortlicher = verantwortlicher;
    }

    @Override
    public String toString() {
        return String.valueOf(id)+" "+name;
    }
}

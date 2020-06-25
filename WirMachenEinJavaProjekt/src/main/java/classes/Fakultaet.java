package classes;

import java.util.List;

public class Fakultaet {
    private int id;
    private String name;
    private List<Studiengang> studiengang;
    private String verantwortlicher;

    public int getId() {
        return id;
    }

    public Fakultaet(String name, List<Studiengang> studiengangs){
        this.name=name;
        this.studiengang = studiengangs;

        this.studiengang = studiengangs;
    }

    public Fakultaet(int id,String name){
        this.id = id;
        this.name=name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Studiengang> getStudiengang() {
        return studiengang;
    }

    public void setStudiengang(List<Studiengang> studiengangs) {
        this.studiengang = studiengang;
    }

    public void addSlave(Studiengang studiengang) {
        System.out.println(studiengang.getName());
        this.studiengang.add(studiengang);
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

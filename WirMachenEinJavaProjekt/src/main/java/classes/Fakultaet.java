package classes;

public class Fakultaet {
    private String name;
    private Studiengang studiengang[]= new Studiengang[100];
    private String verantwortlicher;

    public Fakultaet(String abkürzung){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

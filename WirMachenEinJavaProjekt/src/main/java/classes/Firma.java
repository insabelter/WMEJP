package classes;

import java.util.List;

public class Firma {
    private int id;
    private String name;
    private String ansprechpartner;
    private String adresse;
    private List<Student> studenten;

    public int getId() {
        return id;
    }

    public Firma(String name){
        this.name = name;
    }
    public Firma(int id,String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudenten() {
        return studenten;
    }
    public void addSlave(Student student) {
        this.studenten.add(student);
        student.setFirma(this);
    }

    public void setStudenten(List<Student> studenten) {
        this.studenten = studenten;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

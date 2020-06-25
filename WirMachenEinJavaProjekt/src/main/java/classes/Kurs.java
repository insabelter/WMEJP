package classes;

import java.util.List;

public class Kurs {
    private int id;
    private String name;
    private List<Student> students;
    private int jahrgang;
    private int nummer;
    private String raum;
    private String emailVerteiler;
    private Studienrichtung studienrichtung;

    public int getId() {
        return id;
    }

    public Kurs(String name){
        this.name = name;
    }
    public Kurs(int id,String name){
        this.id = id;
        this.name = name;
    }
    public void addSlave(Student student) {
        this.students.add(student);
        student.setKurs(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public int getJahrgang() {
        return jahrgang;
    }

    public void setJahrgang(int jahrgang) {
        this.jahrgang = jahrgang;
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public String getRaum() {
        return raum;
    }

    public void setRaum(String raum) {
        this.raum = raum;
    }

    public String getEmailVerteiler() {
        return emailVerteiler;
    }

    public void setEmailVerteiler(String emailVerteiler) {
        this.emailVerteiler = emailVerteiler;
    }

    public Studienrichtung getStudienrichtung() {
        return studienrichtung;
    }

    public void setStudienrichtung(Studienrichtung studienrichtung) {
        this.studienrichtung = studienrichtung;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getStudentenName(Student s){
        return s.getFirstname() +" "+s.getLastname();
    }
}


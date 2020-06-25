package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Kurs implements HasID{
    private int id;
    private int jahrgang;
    private int nummer;
    private String raum;
    private String emailVerteiler;
    private Studienrichtung studienrichtung;

    private List<Student> students;

    private String name;

    public Kurs(int id, int jahrgang, int nummer, String raum, String emailVerteiler, Studienrichtung studienrichtung, List<Student> students) {
        this.id = id;
        this.jahrgang = jahrgang;
        this.nummer = nummer;
        this.raum = raum;
        this.emailVerteiler = emailVerteiler;
        this.studienrichtung = studienrichtung;
        this.students = students;
        this.name = "defaultName";
    }

    public void createName(){
        this.name = studienrichtung.getStudiengang().getKuerzel()+jahrgang+studienrichtung.getKuerzel()+nummer;
    }

    public void addSlave(Student student) {
        this.students.add(student);
        student.setKurs(this);
    }

    @Override
    public int getId() {
        return id;
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


    public String printUI() {
        return name;
    }

    @Override
    public String toString() {
        return "Kurs{" +
                "id=" + id +
                ", jahrgang=" + jahrgang +
                ", nummer=" + nummer +
                ", raum='" + raum + '\'' +
                ", emailVerteiler='" + emailVerteiler + '\'' +
                ", studienrichtung=" + studienrichtung.getName() +
                ", students=" + students +
                ", name='" + name + '\'' +
                '}';
    }
}


package classes;

public class Student {
    //this is a very basic Student class just for testing purposes, WIP
    private int javakenntnisse;
    private String firstname;
    private String lastname;
    private Kurs kurs;
    private Firma firma;
    private String matrikelnummer;

    public Student(String firstname, String lastname, String matrikelnummer, int javakenntnisse){
        this(firstname,lastname, matrikelnummer,new Kurs("-"),new Firma("-"), javakenntnisse);
    }
    public Student(String firstname, String lastname, String matrikelnummer, Kurs kurs, Firma company, int javakenntnisse){
        this.firstname =firstname;
        this.lastname = lastname;
        this.kurs = kurs;
        this.matrikelnummer = matrikelnummer;
        this.firma=company;
        this.javakenntnisse = javakenntnisse;
    }

    public int getJavakenntnisse() {
        return javakenntnisse;
    }

    public void setJavakenntnisse(int javakenntnisse) {
        this.javakenntnisse = javakenntnisse;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Kurs getKurs() {
        return kurs;
    }

    public void setKurs(Kurs kurs) {
        this.kurs = kurs;
    }

    public Firma getFirma() {
        return firma;
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
    }

    public String getMatrikelnummer() {
        return matrikelnummer;
    }

}

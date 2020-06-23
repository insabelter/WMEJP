package classes;

public class Student {
    //this is a very basic Student class just for testing purposes, WIP
    private int javaxp;
    private String firstname;
    private String lastname;
    private Kurs kurs;
    private Firma firma;
    private String matnr;

    public Student(String firstname,String lastname,String matnr,int javaxp){
        this(firstname,lastname,matnr,null,null,javaxp);
    }
    public Student(String firstname,String lastname,String matnr,Kurs kurs,Firma company,int javaxp){
        this.firstname =firstname;
        this.lastname = lastname;
        this.kurs = kurs;
        this.matnr=matnr;
        this.firma=company;
        this.javaxp = javaxp;
    }

    public int getJavaxp() {
        return javaxp;
    }

    public void setJavaxp(int javaxp) {
        this.javaxp = javaxp;
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

    public String getMatnr() {
        return matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }
}

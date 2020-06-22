package classes;

public class Student {
    //this is a very basic Student class just for testing purposes, WIP
    private int javaxp;
    private String name;
    private String kurs;
    private String company;
    private String matnr;

    public Student(String name,String matnr,String kurs,String company,int javaxp){
        this.name=name;
        this.kurs = kurs;
        this.matnr=matnr;
        this.company=company;
        this.javaxp = javaxp;
    }

    public int getJavaxp() {
        return javaxp;
    }

    public void setJavaxp(int javaxp) {
        this.javaxp = javaxp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKurs() {
        return kurs;
    }

    public void setKurs(String kurs) {
        this.kurs = kurs;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getMatnr() {
        return matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }
}

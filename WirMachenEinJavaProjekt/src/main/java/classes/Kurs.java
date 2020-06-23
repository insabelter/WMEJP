package classes;

public class Kurs {
    private String jahrgang;
    private int nummer;
    private String raum;
    private String emailVerteiler;
    private Studienrichtung studienrichtung;


    public Kurs(String name){
        this.jahrgang = name;
    }

    public String getJahrgang() {
        return jahrgang;
    }

    public void setJahrgang(String jahrgang) {
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
        return jahrgang;
    }
}

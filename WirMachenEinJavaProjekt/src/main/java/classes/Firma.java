package classes;

public class Firma {
    private String name;
    private String ansprechpartner;
    private String adresse;

    public Firma(String name){
        this.name = name;
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

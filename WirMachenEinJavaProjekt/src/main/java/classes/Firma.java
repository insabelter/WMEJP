package classes;

public class Firma {
    private int id;
    private String name;
    private String ansprechpartner;
    private String adresse;

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

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

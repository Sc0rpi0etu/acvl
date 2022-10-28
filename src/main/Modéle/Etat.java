package Modéle;

public class Etat {
    private Nom_Etat etatActuel;
    private String motif;

    public Etat(Nom_Etat etatActuel, String motif) {
        this.etatActuel = etatActuel;
        this.motif = motif;
    }

    public Etat(Nom_Etat etatActuel) {
        this.etatActuel = etatActuel;
    }


}

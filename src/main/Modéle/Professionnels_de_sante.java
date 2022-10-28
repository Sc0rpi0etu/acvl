package Modéle;

public abstract class Professionnels_de_sante{
    String nom;
    String prenom;
    String adresseMail;
    String adresseResidence;

    private Centre centreActuel;
    private Etat etatActuel;


    public Professionnels_de_sante(String nom, String prenom, String adresseMail, String adresseResidence, Centre centreActuel, Etat etatActuel) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresseMail = adresseMail;
        this.adresseResidence = adresseResidence;
        this.centreActuel = centreActuel;
        this.etatActuel = etatActuel;
    }
}

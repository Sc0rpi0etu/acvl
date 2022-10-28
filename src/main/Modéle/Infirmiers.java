package Modéle;

public class Infirmiers extends Professionnels_de_sante{
    public Infirmiers(String nom, String prenom, String adresseMail, String adresseResidence, Centre centreActuel, Etat etatActuel) {
        super(nom, prenom, adresseMail, adresseResidence, centreActuel, etatActuel);
    }
}

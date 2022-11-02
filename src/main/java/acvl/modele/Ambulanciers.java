package Modéle;

public class Ambulanciers extends Secouristes {
    public Ambulanciers(String nom, String prenom, String adresseMail, String adresseResidence, Centre centreActuel, Etat etatActuel) throws Exception {
        super(nom, prenom, adresseMail, adresseResidence, centreActuel, etatActuel);
    }
}

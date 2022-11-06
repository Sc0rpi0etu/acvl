package acvl.modele;

public class Ambulanciers extends Secouristes {
    public Ambulanciers(String nom, String prenom, String adresseMail, String adresseResidence, Centre centreActuel, Etat etatActuel, String login, String motdepasse) throws Exception {
        super(nom, prenom, adresseMail, adresseResidence, centreActuel, etatActuel, login, motdepasse);
    }

    @Override
    public int getId() {
        return 2;
    }

    @Override
    public String getMetier() {
        return "Ambulancier";
    }
}

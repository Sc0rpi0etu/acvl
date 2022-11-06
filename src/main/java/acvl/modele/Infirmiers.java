package acvl.modele;

public class Infirmiers extends Professionnels_de_sante{
    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getMetier() {
        return "Infirmier";
    }

    public Infirmiers(String nom, String prenom, String adresseMail, String adresseResidence, Centre centreActuel, Etat etatActuel, String login, String motdepasse) {
        super(nom, prenom, adresseMail, adresseResidence, centreActuel, etatActuel, login, motdepasse);
    }
}

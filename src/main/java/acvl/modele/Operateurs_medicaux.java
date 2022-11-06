package acvl.modele;

public class Operateurs_medicaux extends Professionnels_de_sante{
    @Override
    public int getId() {
        return 6;
    }

    @Override
    public String getMetier() {
        return "Opérateur médical";
    }

    public Operateurs_medicaux(String nom, String prenom, String adresseMail, String adresseResidence, Centre centreActuel, Etat etatActuel, String login, String motdepasse) {
        super(nom, prenom, adresseMail, adresseResidence, centreActuel, etatActuel, login, motdepasse);
    }
}

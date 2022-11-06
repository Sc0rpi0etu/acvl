package acvl.modele;

public class Secouristes extends Professionnels_de_sante{
    @Override
    public int getId() {
        return 1;
    }

    @Override
    public String getMetier() {
        return "Secouriste";
    }

    public Secouristes(String nom, String prenom, String adresseMail, String adresseResidence, Centre centreActuel, Etat etatActuel, String login, String motdepasse) throws Exception {
        super(nom, prenom, adresseMail, adresseResidence, centreActuel, etatActuel, login, motdepasse);

        if(!(centreActuel instanceof Centre_de_secours)){
            throw new Exception("Impossible d'assigner ce centre à ce type de profesionnel de sante");
        }

    }
}

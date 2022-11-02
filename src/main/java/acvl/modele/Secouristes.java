package Modéle;

public class Secouristes extends Professionnels_de_sante{
    public Secouristes(String nom, String prenom, String adresseMail, String adresseResidence, Centre centreActuel, Etat etatActuel) throws Exception {
        super(nom, prenom, adresseMail, adresseResidence, centreActuel, etatActuel);

        if(!(centreActuel instanceof Centre_de_secours)){
            throw new Exception("Impossible d'assigner ce centre à ce type de profesionnel de sante");
        }

    }
}

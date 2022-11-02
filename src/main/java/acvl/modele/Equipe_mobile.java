package Modéle;

import java.util.ArrayList;

public class Equipe_mobile extends Equipe {
    private ArrayList<Vehicule> listeVehicules;
    private ArrayList<Professionnels_de_sante> listeMembre;
    private Medecins chefEquipe;

    public Equipe_mobile(ArrayList<Vehicule> listeVehicules, ArrayList<Professionnels_de_sante> membre, Medecins chefEquipe) throws Exception {

        this.listeVehicules = listeVehicules;
        this.listeMembre = membre;

        if(!(chefEquipe.getStatutActuel() instanceof Mobile)){
            throw new Exception("Impossible de nommer ce professionnel de santé en tant que chef d'équipe");
        }

        if(!(membre.stream().anyMatch(element -> element instanceof Infirmiers) || membre.stream().anyMatch(element -> element instanceof Secouristes)) && (membre.stream().anyMatch(element -> element instanceof Ambulanciers))){
            throw new Exception("Mauvaise composition d'équipe");
        }


        this.chefEquipe = chefEquipe;
    }
}

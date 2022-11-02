package Modéle;

public class Medecins extends Professionnels_de_sante {

    private Statut statutActuel;

    public Statut getStatutActuel() {
        return statutActuel;
    }

    public void setStatutActuel(Statut statutActuel) {
        this.statutActuel = statutActuel;
    }

    public Medecins(String nom, String prenom, String adresseMail, String adresseResidence, Centre centreActuel, Etat etatActuel, Statut statutActuel) throws Exception {
        super(nom, prenom, adresseMail, adresseResidence, centreActuel, etatActuel);
        this.statutActuel = statutActuel;

        if(centreActuel instanceof Centre_de_regulation && !((statutActuel instanceof Regulateur)||(statutActuel instanceof PARM))){
            throw new Exception("Impossible d'affecte ce centre à ce statut");
        }
        else if (centreActuel instanceof Centre_de_secours && !(statutActuel instanceof Mobile)){
            throw new Exception("Impossible d'affecte ce centre à ce statut");
        }
        else if (centreActuel instanceof Centre_de_soin && !(statutActuel instanceof Fixe)){
            throw new Exception("Impossible d'affecte ce centre à ce statut");
        }
    }
}

package acvl.modele;

import java.util.List;

public class Medecins extends Professionnels_de_sante {

    private Statut statutActuel;

    public Statut getStatutActuel() {
        return statutActuel;
    }

    public boolean setStatutActuel(Statut statutActuel) {
        this.statutActuel = statutActuel;
        return true;
    }
    public List<Patient> patientList;

    public Medecins(String nom, String prenom, String adresseMail, String adresseResidence, Centre centreActuel, Etat etatActuel, String login, String motdepasse, Statut statutActuel) throws Exception {
        super(nom, prenom, adresseMail, adresseResidence, centreActuel, etatActuel, login, motdepasse);
        this.statutActuel = statutActuel;

        if(centreActuel instanceof Centre_de_regulation && !statutActuel.equals(Statut.Régulateur)){
            throw new Exception("Impossible d'affecte ce centre à ce statut");
        }
        else if (centreActuel instanceof Centre_de_secours && !statutActuel.equals(Statut.Mobile)){
            throw new Exception("Impossible d'affecte ce centre à ce statut");
        }
        else if (centreActuel instanceof Centre_de_soin && !statutActuel.equals(Statut.Fixe)){
            throw new Exception("Impossible d'affecte ce centre à ce statut");
        }
    }

    @Override
    public int getId() {
        return statutActuel.equals(Statut.Régulateur) ? 3 :statutActuel.equals(Statut.Fixe) ? 4 : 5;
    }

    @Override
    public String getMetier() {
        return "Médecin " + this.getStatutActuel().toString();
    }
}

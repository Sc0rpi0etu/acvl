package acvl.modele;

import java.util.Optional;

public abstract class Professionnels_de_sante{
    String nom;
    String prenom;
    String adresseMail;
    String adresseResidence;
    String login;
    String motdepasse;

    private Centre centreActuel;
    private Etat etatActuel;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public String getAdresseResidence() {
        return adresseResidence;
    }

    public void setAdresseResidence(String adresseResidence) {
        this.adresseResidence = adresseResidence;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public Centre getCentreActuel() {
        return centreActuel;
    }

    public boolean setCentreActuel(Centre centreActuel) {
        this.centreActuel = centreActuel;
        return true;
    }

    public Etat getEtatActuel() {
        return etatActuel;
    }

    public void setEtatActuel(Etat etatActuel) {
        this.etatActuel = etatActuel;
    }

    @Override
    public String toString() {
        return this.nom + " " + this.prenom;
    }

    public abstract int getId();

    public abstract String getMetier();

    public Professionnels_de_sante(String nom, String prenom, String adresseMail, String adresseResidence, Centre centreActuel, Etat etatActuel, String login, String motdepasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresseMail = adresseMail;
        this.adresseResidence = adresseResidence;
        this.centreActuel = centreActuel;
        this.etatActuel = etatActuel;
        this.login = login;
        this.motdepasse = motdepasse;
    }
}

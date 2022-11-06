package acvl.modele;

public class Patient {
    String nom;
    String prenom;
    String avis;

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

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    public Patient(String nom, String prenom, String avis) {
        this.nom = nom;
        this.prenom = prenom;
        this.avis = avis;
    }

    public String getFullName() {
        return this.getNom() + " " + this.getPrenom();
    }
}

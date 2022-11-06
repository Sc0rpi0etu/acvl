package acvl.modele;

import org.json.simple.JSONObject;

import java.util.Objects;

public abstract class Centre {
    String nom;
    String adresse;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Centre centre = (Centre) o;
        return Objects.equals(nom, centre.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("nom", this.nom);
        json.put("adresse", this.adresse);
        return  json;
    }
}

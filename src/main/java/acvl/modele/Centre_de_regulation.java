package acvl.modele;

import org.json.simple.JSONObject;

public class Centre_de_regulation extends Centre {
    int capacite;

    public Centre_de_regulation(String nom, String adresse, int capacite) {
        this.adresse = adresse;
        this.nom = nom;
        this.capacite = capacite;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("nom", this.nom);
        json.put("adresse", this.adresse);
        json.put("capacite", this.capacite);
        return  json;
    }
}

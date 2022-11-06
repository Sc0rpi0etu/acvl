package acvl.modele;

import org.json.simple.JSONObject;

public class Centre_de_secours extends Centre {
    int nbvehicules;

    public Centre_de_secours(String nom, String adresse, int nbvehicules) {
        this.adresse = adresse;
        this.nom = nom;
        this.nbvehicules = nbvehicules;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("nom", this.nom);
        json.put("adresse", this.adresse);
        json.put("nbvehicules", this.nbvehicules);
        return  json;
    }
}

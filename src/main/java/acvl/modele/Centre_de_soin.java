
package acvl.modele;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class Centre_de_soin extends Centre {
    int capacite;
    List<Domaine_Activite> activites;
    public Centre_de_soin(String nom, String adresse, int capacite, List<Domaine_Activite> activites) {
        this.adresse = adresse;
        this.nom = nom;
        this.capacite = capacite;
        this.activites = activites;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("nom", this.nom);
        json.put("adresse", this.adresse);
        json.put("capacite", this.capacite);

        JSONArray jsonArray = new JSONArray();
        for (Domaine_Activite activite : activites) {
            jsonArray.add(activite.name());
        }
        json.put("activites", jsonArray);

        return  json;
    }
}

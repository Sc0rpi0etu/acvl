package acvl.modele;

public enum Nom_Etat {
    disponible_base,
    en_attente_intervention,
    disponible_hors_base,
    intervention,
    indisponible;

    public String motif;

    Nom_Etat(String motif) {
        this.motif = motif;
    }

    Nom_Etat() {

    }
}

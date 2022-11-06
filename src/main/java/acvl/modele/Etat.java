package acvl.modele;

public enum Etat {
    disponible_base,
    en_attente_intervention,
    disponible_hors_base,
    intervention,
    indisponible;

    String motif;

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    Etat(String motif) {
        this.motif = motif;
    }

    Etat() {

    }
}

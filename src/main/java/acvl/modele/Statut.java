package acvl.modele;

public enum Statut {
    Régulateur,
    Fixe,
    Mobile;
    @Override
    public String toString() {
        return this.name();
    }
}

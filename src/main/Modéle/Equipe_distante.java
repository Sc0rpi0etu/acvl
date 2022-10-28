package Modéle;

public class Equipe_distante extends Equipe {
    private PARM membre;

    public Equipe_distante(PARM membre) {
        this.membre = membre;
    }

    public PARM getMembre() {
        return membre;
    }

    public void setMembre(PARM membre) {
        this.membre = membre;
    }
}

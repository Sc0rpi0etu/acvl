package acvl.modele;

public class Agent_administratif {
    String nom;
    String login;
    String prenom;
    String adresseMail;
    String pwd;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Agent_administratif(String nom, String prenom, String adresseMail, String login, String pwd) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresseMail = adresseMail;
        this.pwd = pwd;
        this.login = login;
    }
}

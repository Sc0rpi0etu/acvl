package acvl.controller;

import acvl.modele.*;
import acvl.tools.Crypto;
import acvl.tools.DatabaseLoader;
import acvl.tools.TextVerifier;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class AdminController {
    Agent_administratif admin;
    @FXML
    TextField name;
    @FXML
    TextField firstname;
    @FXML
    TextField address;
    @FXML
    TextField email;
    @FXML
    ComboBox centre;
    @FXML
    ComboBox job;
    @FXML
    TextField login;
    @FXML
    PasswordField password;
    @FXML
    ComboBox prosList;
    @FXML
    Button addBtn;
    @FXML
    Button modifyBtn;
    @FXML
    Button deleteBtn;
    @FXML
    Label nameFalse;
    @FXML
    Label firstnameFalse;
    @FXML
    Label emailFalse;
    @FXML
    Label addressFalse;
    @FXML
    Label loginFalse;
    @FXML
    Label passwordFalse;
    @FXML
    Label jobFalse;
    @FXML
    Label centreFalse;

    Professionnels_de_sante pro;

    public void setPro(Professionnels_de_sante pro) {
        this.pro = DatabaseLoader.prosList.get(DatabaseLoader.prosList.indexOf(pro));
    }

    public void load() {
        this.job.setItems(FXCollections.observableArrayList(DatabaseLoader.jobList));
        this.prosList.visibleProperty().set(false);
        this.addBtn.setDisable(true);
        this.deleteBtn.setVisible(false);
    }

    public void setAdmin(Agent_administratif admin) {
        this.admin = DatabaseLoader.agentsList.get(DatabaseLoader.agentsList.indexOf(admin));;
    }

    @FXML
    public void add(ActionEvent event) throws IOException {
        this.addBtn.setDisable(true);
        this.modifyBtn.setDisable(false);
        this.deleteBtn.setVisible(false);
        this.prosList.visibleProperty().set(false);
        this.prosList.setItems(null);
        this.job.setItems(FXCollections.observableArrayList(DatabaseLoader.jobList));
        this.job.valueProperty().set(null);
        this.name.setText("");
        this.firstname.setText("");
        this.login.setText("");
        this.password.setText("");
        this.email.setText("");
        this.address.setText("");
        this.centre.valueProperty().set(null);
        this.centre.setItems(null);
        setFalseField();
    }

    private void setFalseField() {
        this.nameFalse.setVisible(false);
        this.firstnameFalse.setVisible(false);
        this.emailFalse.setVisible(false);
        this.addressFalse.setVisible(false);
        this.loginFalse.setVisible(false);
        this.passwordFalse.setVisible(false);
        this.centreFalse.setVisible(false);
        this.jobFalse.setVisible(false);
    }

    @FXML
    public void modify(ActionEvent event) throws IOException {
        this.modifyBtn.setDisable(true);
        this.deleteBtn.setVisible(true);
        this.deleteBtn.setDisable(true);
        this.addBtn.setDisable(false);
        this.prosList.visibleProperty().set(true);
        this.prosList.setItems(FXCollections.observableArrayList(DatabaseLoader.prosList));
        this.job.valueProperty().set(null);
        this.name.setText("");
        this.firstname.setText("");
        this.login.setText("");
        this.password.setText("");
        this.email.setText("");
        this.address.setText("");
        this.centre.valueProperty().set(null);
        this.centre.setItems(null);
    }

    @FXML
    public void save(ActionEvent event) throws Exception {
        boolean isVerified = true;

        if (this.job.valueProperty().isNull().get()) {
            isVerified = false;
            this.jobFalse.setVisible(true);
        }

        if (this.centre.valueProperty().isNull().get()) {
            isVerified = false;
            this.centreFalse.setVisible(true);
        }

        if (!TextVerifier.isName(this.name.getText())) {
            isVerified = false;
            this.nameFalse.setVisible(true);
        }

        if (!TextVerifier.isName(this.firstname.getText())) {
            isVerified = false;
            this.firstnameFalse.setVisible(true);
        }

        if (!TextVerifier.isAdress(this.address.getText())) {
            isVerified = false;
            this.addressFalse.setVisible(true);
        }

        if (!TextVerifier.isEmail(this.email.getText())) {
            isVerified = false;
            this.emailFalse.setVisible(true);
        }

        if (!TextVerifier.hasSpace(this.login.getText()) || (DatabaseLoader.prosList.stream().anyMatch(p -> !pro.getLogin().equals(this.login.getText()) && p.getLogin().equals(this.login.getText())) || DatabaseLoader.agentsList.stream().anyMatch(p -> p.getLogin().equals(this.login.getText())))) {
            isVerified = false;
            this.loginFalse.setVisible(true);
        }

        if (!TextVerifier.hasSpace(this.password.getText())) {
            isVerified = false;
            this.passwordFalse.setVisible(true);
        }

        if (isVerified) {
            // si modif
            if (this.modifyBtn.isDisabled()) {
                saveProfile();
                switch (this.job.valueProperty().get().toString()) {
                    case "Médecin Fixe":
                    case "Médecin Régulateur":
                    case "Médecin Mobile":
                        Medecins proMedecin = (Medecins) pro;

                        switch (this.job.valueProperty().get().toString()) {
                            case "Médecin Fixe":
                                proMedecin.setStatutActuel(Statut.Fixe);
                                break;

                            case "Médecin Régulateur":
                                proMedecin.setStatutActuel(Statut.Régulateur);
                                break;

                            case "Médecin Mobile":
                                proMedecin.setStatutActuel(Statut.Mobile);
                                break;
                        }
                        break;

                    default:
                        break;
                }

                this.prosList.setItems(FXCollections.observableArrayList(DatabaseLoader.prosList));
                this.prosList.valueProperty().set(this.pro);

            } else {
                Etat e = Etat.indisponible;
                e.setMotif("nouveau");

                String password = Crypto.crypt(this.password.getText());

                switch (this.job.valueProperty().get().toString()) {
                    case "Médecin Fixe":
                    case "Médecin Régulateur":
                    case "Médecin Mobile":
                        DatabaseLoader.prosList.add(new Medecins(
                                this.name.getText(),
                                this.firstname.getText(),
                                this.email.getText(),
                                this.address.getText(),
                                DatabaseLoader.centresList.stream().filter(c -> c.getNom().equals(this.centre.valueProperty().get().toString())).findFirst().get(),
                                e,
                                this.login.getText(),
                                password,
                                this.job.valueProperty().get().toString().contains("Régulateur") ? Statut.Régulateur : this.job.valueProperty().get().toString().contains("Fixe") ? Statut.Fixe : Statut.Mobile
                        ));
                        break;

                    case "Infirmier":
                        DatabaseLoader.prosList.add(new Infirmiers(
                                this.name.getText(),
                                this.firstname.getText(),
                                this.email.getText(),
                                this.address.getText(),
                                DatabaseLoader.centresList.stream().filter(c -> c.getNom().equals(this.centre.valueProperty().get().toString())).findFirst().get(),
                                e,
                                this.login.getText(),
                                password
                        ));
                        break;

                    case "Secouriste":
                        DatabaseLoader.prosList.add(new Secouristes(
                                this.name.getText(),
                                this.firstname.getText(),
                                this.email.getText(),
                                this.address.getText(),
                                DatabaseLoader.centresList.stream().filter(c -> c.getNom().equals(this.centre.valueProperty().get().toString())).findFirst().get(),
                                e,
                                this.login.getText(),
                                password
                        ));
                        break;

                    case "Ambulancier":
                        DatabaseLoader.prosList.add(new Ambulanciers(
                                this.name.getText(),
                                this.firstname.getText(),
                                this.email.getText(),
                                this.address.getText(),
                                DatabaseLoader.centresList.stream().filter(c -> c.getNom().equals(this.centre.valueProperty().get().toString())).findFirst().get(),
                                e,
                                this.login.getText(),
                                password
                        ));
                        break;

                    case "Opérateur médical":
                        DatabaseLoader.prosList.add(new Operateurs_medicaux(
                                this.name.getText(),
                                this.firstname.getText(),
                                this.email.getText(),
                                this.address.getText(),
                                DatabaseLoader.centresList.stream().filter(c -> c.getNom().equals(this.centre.valueProperty().get().toString())).findFirst().get(),
                                e,
                                this.login.getText(),
                                password
                        ));
                        break;
                }

                this.add(null);
            }
        }
    }

    @FXML
    public void delete(ActionEvent event) throws Exception {
        DatabaseLoader.prosList.remove(this.pro);
        this.prosList.setItems(FXCollections.observableArrayList(DatabaseLoader.prosList));
        this.add(null);
    }

    public void saveProfile() throws NoSuchAlgorithmException {
        pro.setNom(this.name.getText());
        pro.setPrenom(this.firstname.getText());
        pro.setAdresseMail(this.email.getText());
        pro.setAdresseResidence(this.address.getText());
        pro.setLogin(this.login.getText());
        pro.setMotdepasse(Crypto.crypt(this.password.getText()));
        pro.setCentreActuel(DatabaseLoader.centresList.stream().filter(c -> c.getNom().equals(this.centre.valueProperty().get().toString())).findFirst().get());
        pro.setEtatActuel(Etat.indisponible);
        pro.getEtatActuel().setMotif("nouveau");
    }

    public void initialize() {
        this.prosList.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                setFalseField();
                this.deleteBtn.setDisable(false);
                this.pro = (Professionnels_de_sante) newValue;

                switch (pro.getMetier()) {
                    case "Médecin Fixe":
                    case "Médecin Régulateur":
                    case "Médecin Mobile":
                        this.job.setItems(FXCollections.observableArrayList(DatabaseLoader.jobList.stream().filter(c -> c.contains("Médecin")).toList()));
                        break;

                    case "Infirmier":
                    case "Ambulancier":
                    case "Secouriste":
                    case "Opérateur médical":
                        this.job.setItems(FXCollections.observableArrayList(pro.getMetier()));
                        break;
                }

                this.job.valueProperty().set(pro.getMetier());
                this.name.setText(pro.getNom());
                this.firstname.setText(pro.getPrenom());
                this.login.setText(pro.getLogin());
                this.password.setText(pro.getMotdepasse());
                this.email.setText(pro.getAdresseMail());
                this.address.setText(pro.getAdresseResidence());
                this.centre.valueProperty().set(pro.getCentreActuel().getNom());
            } else {
                this.deleteBtn.setDisable(true);
            }
        });

        this.job.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                switch (newValue.toString()) {
                    case "Infirmier":
                    case "Secouriste":
                    case "Ambulancier":
                    case "Médecin Mobile":
                        this.centre.setItems(FXCollections.observableArrayList(DatabaseLoader.centresList.stream().filter(c -> c.getClass().equals(Centre_de_secours.class)).map(Centre::getNom).toList()));
                        break;

                    case "Médecin Fixe":
                        this.centre.setItems(FXCollections.observableArrayList(DatabaseLoader.centresList.stream().filter(c -> c.getClass().equals(Centre_de_soin.class)).map(Centre::getNom).toList()));
                        break;

                    case "Médecin Régulateur":
                    case "Opérateur médical":
                        this.centre.setItems(FXCollections.observableArrayList(DatabaseLoader.centresList.stream().filter(c -> c.getClass().equals(Centre_de_regulation.class)).map(Centre::getNom).toList()));
                        break;
                }
            }
        });
    }
}

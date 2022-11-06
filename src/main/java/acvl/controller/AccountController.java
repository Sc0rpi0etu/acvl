package acvl.controller;

import acvl.modele.*;
import acvl.tools.DatabaseLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AccountController {
    @FXML
    Label name;
    @FXML
    Label firstname;
    @FXML
    Label address;
    @FXML
    Label email;
    @FXML
    Label centre;
    @FXML
    ComboBox state;
    @FXML
    Label job;
    @FXML
    Label patientLabel;
    @FXML
    ComboBox patientComboBox;
    @FXML
    Label avisLabel;
    @FXML
    TextArea avisText;
    @FXML
    Button saveButton;
    @FXML
    Button saveButtonEtat;

    @FXML
    Label motifLabel;
    @FXML
    TextArea motifText;

    Professionnels_de_sante pro;

    public void setPro(Professionnels_de_sante pro) {
        this.pro = DatabaseLoader.prosList.get(DatabaseLoader.prosList.indexOf(pro));
    }

    public void load() {
        this.job.setText(pro.getMetier());
        this.name.setText(pro.getNom());
        this.firstname.setText(pro.getPrenom());
        this.address.setText(pro.getAdresseResidence());
        this.email.setText(pro.getAdresseMail());
        this.centre.setText(pro.getCentreActuel().getNom());
        state.getSelectionModel().select(DatabaseLoader.stateList.stream().filter(e -> e.equals(pro.getEtatActuel().name())).findFirst().get());

        this.patientLabel.setVisible(false);
        this.patientComboBox.setVisible(false);
        this.avisLabel.setVisible(false);
        this.avisText.setVisible(false);
        this.saveButton.setVisible(false);

        if (pro instanceof Medecins) {
            this.patientLabel.setVisible(true);
            this.patientComboBox.setVisible(true);
            this.patientComboBox.setItems(FXCollections.observableArrayList(((Medecins) pro).patientList.stream().map(Patient::getFullName).toList()));
        }
    }

    public void initialize() {
        state.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.equals("indisponible")) {
                pro.getEtatActuel().setMotif(null);
                this.motifText.setVisible(false);
                this.motifLabel.setVisible(false);
                this.saveButtonEtat.setVisible(false);
            }
            switch (newValue.toString()) {
                case "intervention":
                    pro.setEtatActuel(Etat.intervention);
                    state.setItems(FXCollections.observableArrayList("intervention", "disponible_hors_base"));
                    break;

                case "disponible_hors_base":
                    pro.setEtatActuel(Etat.disponible_hors_base);
                    state.setItems(FXCollections.observableArrayList("intervention", "disponible_hors_base", "disponible_base"));
                    break;

                case "disponible_base":
                    pro.setEtatActuel(Etat.disponible_base);
                    state.setItems(FXCollections.observableArrayList("intervention", "en_attente_intervention", "disponible_base", "indisponible"));
                    break;

                case "en_attente_intervention":
                    pro.setEtatActuel(Etat.en_attente_intervention);
                    state.setItems(FXCollections.observableArrayList("intervention", "en_attente_intervention", "disponible_base", "indisponible"));
                    break;

                case "indisponible":
                    pro.setEtatActuel(Etat.indisponible);
                    state.setItems(FXCollections.observableArrayList("indisponible", "disponible_base"));
                    this.motifLabel.setVisible(true);
                    this.motifText.setVisible(true);
                    this.saveButtonEtat.setVisible(true);
                    if (pro.getEtatActuel().getMotif() == null) {
                        pro.getEtatActuel().setMotif("Motif à renseigner");
                    }
                    break;
            }
            this.motifText.setText(pro.getEtatActuel().getMotif());
        });

        this.patientComboBox.valueProperty().addListener((obs, oldValue, newValue) -> {
            Medecins medecin = (Medecins) pro;
            if (medecin.getStatutActuel() != Statut.Régulateur) {
                Patient p = medecin.patientList.stream().filter(patient -> (patient.getNom() + " " + patient.getPrenom()).equals(patientComboBox.valueProperty().get())).findFirst().get();
                this.avisText.setText(p.getAvis());
            }
        });
    }

    @FXML
    public void select(ActionEvent event) {
        this.avisText.setVisible(true);
        this.avisLabel.setVisible(true);
        this.saveButton.setVisible(true);
    }

    @FXML
    public void save(ActionEvent event) {
        Medecins medecin = (Medecins) pro;
        Patient p = medecin.patientList.stream().filter(patient -> (patient.getNom() + " " + patient.getPrenom()).equals(patientComboBox.valueProperty().get())).findFirst().get();
        p.setAvis(this.avisText.getText());
    }

    @FXML
    public void saveEtat(ActionEvent event) {
        if (this.motifText.getText().isEmpty()) {
            this.motifText.setText("Motif à renseigner");
        } else {
            pro.getEtatActuel().setMotif(this.motifText.getText());
        }
    }
}
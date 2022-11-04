package acvl.controller;

import acvl.modele.Professionnels_de_sante;
import acvl.tools.DatabaseLoader;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AccountController {
    @FXML
    Button btn_msg;

    @FXML
    TextField name;
    @FXML
    TextField firstname;
    @FXML
    TextField address;
    @FXML
    TextField email;
    @FXML
    TextField centre;
    @FXML
    ComboBox state;

    Professionnels_de_sante pro;

    public void setPro(Professionnels_de_sante pro) {
        this.pro = pro;
    }

    public void load() {
        this.name.setText(pro.getNom());
        this.firstname.setText(pro.getPrenom());
        this.address.setText(pro.getAdresseResidence());
        this.email.setText(pro.getAdresseMail());
        this.centre.setText(pro.getCentreActuel().getNom());
        state.getItems().addAll(DatabaseLoader.stateList);
        state.getSelectionModel().select(DatabaseLoader.stateList.stream().filter(e -> e.equals(pro.getEtatActuel().getEtatActuel().toString())).findFirst().get());
    }
}
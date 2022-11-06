package acvl.controller;

import acvl.modele.Agent_administratif;
import acvl.modele.Professionnels_de_sante;
import acvl.tools.Crypto;
import acvl.tools.DatabaseLoader;
import acvl.vue.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Optional;

public class ConnectController {
    Crypto aesCryptoUtil = new Crypto();
    @FXML
    TextField loginField;
    @FXML
    PasswordField passwordField;
    @FXML
    Text error;

    @FXML
    Label label;

    private Stage stage;
    private Scene scene;

    @FXML
    public void onConnectClick(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        if (DatabaseLoader.prosList.stream().anyMatch(p -> {
            try {
                return p.getLogin().equals(loginField.getText()) && Crypto.crypt(passwordField.getText()).equals(p.getMotdepasse());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        })) {
            Optional<Professionnels_de_sante> pro = DatabaseLoader.prosList.stream().filter(p -> {
                try {
                    return p.getLogin().equals(loginField.getText()) && Crypto.crypt(passwordField.getText()).equals(p.getMotdepasse());
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
            }).findFirst();

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            FXMLLoader root = new FXMLLoader(Main.class.getResource("AccountView.fxml"));

            AccountController admin = new AccountController();

            admin.setPro(pro.get());
            root.setController(admin);
            Scene scene = new Scene(root.load());
            stage.setOnCloseRequest(e -> {
                DatabaseLoader.save();
            });
            stage.setScene(scene);
            stage.show();
            admin.load();

        } else if (DatabaseLoader.agentsList.stream().anyMatch(p -> {
            try {
                return p.getLogin().equals(loginField.getText()) && Crypto.crypt(passwordField.getText()).equals(p.getPwd());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        })) {
            Optional<Agent_administratif> agent = DatabaseLoader.agentsList.stream().filter(p -> {
                try {
                    return p.getLogin().equals(loginField.getText()) && Crypto.crypt(passwordField.getText()).equals(p.getPwd());
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
            }).findFirst();

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            FXMLLoader root = new FXMLLoader(Main.class.getResource("AdminView.fxml"));

            AdminController admin = new AdminController();

            admin.setAdmin(agent.get());
            root.setController(admin);
            Scene scene = new Scene(root.load());
            stage.setOnCloseRequest(e -> {
                DatabaseLoader.save();
            });
            stage.setScene(scene);
            stage.show();
            admin.load();

        } else {
            label.setVisible(true);
            loginField.setText("");
            passwordField.setText("");
        }
    }
}
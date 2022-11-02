package acvl.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ConnectController {
    @FXML
    Button btn_msg;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        btn_msg.setText("aled");
    }
}
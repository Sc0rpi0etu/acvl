package acvl.vue;

import acvl.tools.DatabaseLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("ConnectView.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("CandACVL");
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> DatabaseLoader.save());
        stage.show();
    }

    public static void main(String[] args) throws IOException, ParseException {
        DatabaseLoader.load();
        launch();
    }
}
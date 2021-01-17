package client.Controllers;

import client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainController {

    @FXML
    public Button registration;

    @FXML
    public Button authorization;

    @FXML
    public Button exit;

    public void registration(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("client/FXML/Registration.fxml")));
            stage.setTitle("Регистрация");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            client.Client.getInstance().send("registration");
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void authorization(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("client/FXML/Authorization.fxml")));
            stage.setTitle("Авторизация");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            client.Client.getInstance().send("authorization");
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void exit(ActionEvent actionEvent) {
       Client.getInstance().send("exit");
       Client.getInstance().close();
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
}

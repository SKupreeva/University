package client.Controllers;

import client.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class AuthorizationController {

    @FXML
    public TextField setLogin;

    @FXML
    public PasswordField setPass;

    @FXML
    public Button exit;

    @FXML
    public Button sendAuthorization;

    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

    public void sendAuthorization(ActionEvent actionEvent) {
      try {
          JSONObject userJson = new JSONObject();
          userJson.put("login", setLogin.getText().trim());
          userJson.put("password", setPass.getText().trim());

          client.Client.getInstance().send(userJson.toString());
          String status = client.Client.getInstance().get();
          exit(actionEvent);
          if(!status.equals( "nobody" ) )
          { openMenu(status);}else {
            System.out.println("Повторите ввод");
              Alert alert = new Alert(  Alert.AlertType.ERROR);
              alert.setTitle( "Ошибка" );
              alert.setHeaderText( "Такой пользователь не зарегистрирован!" );
              alert.showAndWait();
        }
       } catch (JSONException | IOException e) {
          e.printStackTrace();
        }
    }

    public void openMenu(String status) {
        if (status.equals("admin")) {
            try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("client/FXML/MenuAdmin.fxml"));
                Parent root = loader.load();
                stage.setTitle("Меню администратора");
                stage.setResizable(false);
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (status.equals("user")) {
            try {
                User user = User.getInstance();
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("client/FXML/MenuUser.fxml"));
                Parent root = loader.load();
                stage.setTitle("Меню пользователя");
                stage.setResizable(false);
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException  e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Повторите ввод");
        }
    }
}

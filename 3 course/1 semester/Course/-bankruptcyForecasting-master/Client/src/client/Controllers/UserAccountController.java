package client.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class UserAccountController {
    public Label name;
    public Label login;
    public Label password;
    public Label surname;
    public Label phone;
    public Label email;
    public Button exit;

    @FXML
    private void initialize() {
            name.setText(client.User.getInstance().getName());
            login.setText(client.User.getInstance().getLogin());
            password.setText(client.User.getInstance().getPassword());
            surname.setText(client.User.getInstance().getSurname());
            phone.setText(client.User.getInstance().getPhone());
            email.setText(client.User.getInstance().getEmail());
    }

    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
}

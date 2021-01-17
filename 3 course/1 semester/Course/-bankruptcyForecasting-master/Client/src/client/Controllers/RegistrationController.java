package client.Controllers;

import client.Client;
import client.Exceptions.NullException;
import client.Exceptions.PhoneException;
import client.User;
import javafx.event.ActionEvent;
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
import java.util.Objects;

public class RegistrationController {
    public Button exit;
    public Button sendRegistration;
    public TextField setName;
    public TextField setLogin;
    public PasswordField setPass;
    public TextField setAddress;
    public TextField setPhone;
    public TextField setSurname;

    public void sendRegistration(ActionEvent actionEvent) throws JSONException {
        boolean key = true;
        boolean flag = true;
        JSONObject userJson = new JSONObject();
        if( setSurname.getText().isEmpty() || setSurname.getText()==null ){key = false;}
        else {userJson.put("surname", setSurname.getText().trim());}
        if( setName.getText().isEmpty() || setName.getText()==null){key = false;}
        else{userJson.put("name", setName.getText().trim());}
        if( setLogin.getText().isEmpty() || setLogin.getText()==null ){key = false;}
        else{userJson.put("login", setLogin.getText().trim());}
        if( setPass.getText().isEmpty() || setPass.getText()==null ){key = false;}
        else{userJson.put("password", setPass.getText().trim());}
        try {
            Integer num = Integer.valueOf(setPhone.getText().toString());
            if(num < 0 || num >= 2147483647) flag = false;
        }catch (NumberFormatException e){
            flag = false;
        }
//        try{
//            if(!setPhone.getText().isEmpty() || setPhone.getText() != null) throw new NullException();
//        } catch (NullException e) {
//            System.out.println("Повторите ввод");
//            Alert alert = new Alert(  Alert.AlertType.ERROR);
//            alert.setTitle( "Ошибка" );
//            alert.setHeaderText(e.getMessage());
//            alert.showAndWait();
//        }
//        try{
//
//            if(num < 0 || num >= 2147483647) throw new PhoneException();
//        } catch (PhoneException e) {
//            System.out.println("Повторите ввод");
//            Alert alert = new Alert(  Alert.AlertType.ERROR);
//            alert.setTitle( "Ошибка" );
//            alert.setHeaderText(e.getMessage());
//            alert.showAndWait();
//        }
        if( setAddress.getText().isEmpty() || setAddress.getText()==null ){key = false;}
        else{userJson.put("email", setAddress.getText().trim());}
        if( setPhone.getText().isEmpty() || setPhone.getText()==null ){key = false;}
        else{userJson.put("phone", setPhone.getText().trim());}
        if(key = flag = true) {
            client.Client.getInstance().send( userJson.toString() );
            User user = User.getInstance();
            exit( actionEvent );
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load( Objects.requireNonNull( getClass().getClassLoader().getResource( "client/FXML/MenuUser.fxml" ) ) );
                stage.setTitle( "Меню пользователя" );
                stage.setResizable( false );
                stage.setScene( new Scene( root ) );
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            if(key = false) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Заполните все поля!");
                alert.showAndWait();
            }
            if(flag = false) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Введите номер корректно!");
                alert.showAndWait();
            }
        }
    }

    public void exit(ActionEvent actionEvent) {
        Client.getInstance().send( "exit" );
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
}

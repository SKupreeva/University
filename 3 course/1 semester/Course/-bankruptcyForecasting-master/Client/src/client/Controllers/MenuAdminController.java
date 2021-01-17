package client.Controllers;

import client.*;
import client.Exceptions.NullException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MenuAdminController {
    public Button delete;
    public Button companiesUser;
    public Button reports;
    public TextField eur;
    public TextField byn;
    public TextField rub;
    public Button addUSD;
    public TableView companies;
    public TableColumn<Company, String> nameComp;
    public TableColumn<Company, String> addressComp;
    public Button exit;
    public TableView user;
    public TableColumn<UserS, String>  login;
    public TableColumn<UserS, String>  name;
    public TableColumn<UserS, String>  surname;
    public TableColumn<UserS, String>  phone;
    public TableColumn<UserS, String>  email;
    public Button statistics;

    private ReportsController reportsController;

    public void exit(ActionEvent actionEvent) {
        client.Client.getInstance().send("exit");
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

    public void deleteUser(ActionEvent actionEvent) {
        UserS selectedUser = (UserS) user.getSelectionModel().getSelectedItem();
        CollectionUsers.getInstance().delete( selectedUser );
        Client.getInstance().send( "deleteUser" );
        Client.getInstance().send( selectedUser.getID() );
    }

    public void getCompanies(ActionEvent actionEvent) throws IOException {
        UserS selectedUser = (UserS) user.getSelectionModel().getSelectedItem();
        Client.getInstance().send( "getCompanies" );
        Client.getInstance().send( String.valueOf( selectedUser.getID() ) );
        CollectionCompanies.getInstance().fillData();
        companies.getItems().clear();
        companies.refresh();
        nameComp.setCellValueFactory(new PropertyValueFactory<Company, String>("name"));
        addressComp.setCellValueFactory(new PropertyValueFactory<Company, String>("address"));
        companies.setItems( CollectionCompanies.getInstance().getCompanies());
    }

    public void getReports(ActionEvent actionEvent){

        UserS selectedUser = (UserS) user.getSelectionModel().getSelectedItem();
       // Window parentWindow = ((Node) actionEvent.getSource()).getScene().getWindow();
        Client.getInstance().send( "getReports" );
        Client.getInstance().send( String.valueOf( selectedUser.getID() ) );

        System.out.println( selectedUser.getName() );
       // reportsController.fillUser(selectedUser );
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("client/FXML/Reports.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle("Управление отчетами");
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addUSD(ActionEvent actionEvent) throws JSONException {
        JSONObject newRate = new JSONObject(  );
        if(!byn.getText().isEmpty()) {
            newRate.put("byn", Double.valueOf(byn.getText()));
        }else {
            System.out.println("Повторите ввод");
            Alert alert = new Alert(  Alert.AlertType.ERROR);
            alert.setTitle( "Ошибка" );
            alert.setHeaderText("Ошибка! Введите число!");
            alert.showAndWait();
        }
        if(!eur.getText().isEmpty()) {
            newRate.put( "eur",Double.valueOf( eur.getText() ) );
        }else {
            System.out.println("Повторите ввод");
            Alert alert = new Alert(  Alert.AlertType.ERROR);
            alert.setTitle( "Ошибка" );
            alert.setHeaderText("Ошибка! Введите число!");
            alert.showAndWait();
        }
        if(!rub.getText().isEmpty()) {
            newRate.put( "rub", Double.valueOf( rub.getText()));
        }else {
            System.out.println("Повторите ввод");
            Alert alert = new Alert(  Alert.AlertType.ERROR);
            alert.setTitle( "Ошибка" );
            alert.setHeaderText("Ошибка! Введите число!");
            alert.showAndWait();
        }
        Client.getInstance().send( "addUSD" );
        Client.getInstance().send( newRate.toString() );
    }

    public void initialize(){
        CollectionUsers.getInstance().fillData();
        login.setCellValueFactory(new PropertyValueFactory<UserS, String>("login"));
        name.setCellValueFactory(new PropertyValueFactory<UserS, String>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<UserS, String>("surname"));
        phone.setCellValueFactory(new PropertyValueFactory<UserS, String>("phone"));
        email.setCellValueFactory(new PropertyValueFactory<UserS, String>("email"));
        user.setItems( CollectionUsers.getInstance().getUsers());

    }

    public void saveStatistics(ActionEvent actionEvent) {
        Client.getInstance().send( "statistics" );
    }
}

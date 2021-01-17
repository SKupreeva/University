package client.Controllers;

import client.Client;
import client.CollectionCompanies;
import client.Company;
import client.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class MenuUserController {
    public Button displayAccount;
    public Button calculate;
    public Button exit;
    public Button addCompany;
    public TableColumn<Company, String> name;
    public TableColumn<Company, String> address;
    public TableView companies;
    public Button getReports;

    private Parent calculattti;
    private Parent reports;

    private FXMLLoader loader = new FXMLLoader();
    private FXMLLoader loader1 = new FXMLLoader();

    private Stage calculationDialogStage = new Stage();
    private Stage reportsDialogStage = new Stage();

    private CalculationController calculationController;
    private ReportsController reportsController;

    public void displayAccount(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("client/FXML/UserAccount.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle("Личный кабинет");
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exit(ActionEvent actionEvent) {
        client.Client.getInstance().send("exit");
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

    public void calculate(Window parentWindow) {
       calculationDialogStage = new Stage(  );
       calculationDialogStage.setTitle( "Рассчет" );
       calculationDialogStage.setResizable( false );
       calculationDialogStage.setScene( new Scene( calculattti ) );
       calculationDialogStage.initModality( Modality.WINDOW_MODAL );
       calculationDialogStage.initOwner( parentWindow );
       calculationDialogStage.showAndWait();
    }

    public void reports(Window parentWindow) {
        reportsDialogStage = new Stage(  );
        reportsDialogStage.setTitle( "Управление отчетами" );
        reportsDialogStage.setResizable( false );
        reportsDialogStage.setScene( new Scene( reports ) );
        reportsDialogStage.initModality( Modality.WINDOW_MODAL );
        reportsDialogStage.initOwner( parentWindow );
        reportsDialogStage.showAndWait();
    }

    public void addCompany(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("client/FXML/AddCompany.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle("Добавление компании");
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        Client.getInstance().send( String.valueOf( User.getInstance().getID() ) );
        CollectionCompanies.getInstance().fillData();
        name.setCellValueFactory(new PropertyValueFactory<Company, String>("name"));
        address.setCellValueFactory(new PropertyValueFactory<Company, String>("address"));
        companies.setItems( CollectionCompanies.getInstance().getCompanies());

        try{
            loader = new FXMLLoader( getClass().getClassLoader().getResource( "client/FXML/Calculation.fxml" ) );
            calculattti = loader.load();
            calculationController = loader.getController();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

public void display(ActionEvent actionEvent) throws IOException {
        Object source = actionEvent.getSource();
        if(!(source instanceof Button)){return;}

        Button clickedButton = (Button) source;
        Company selectedCompany = (Company) companies.getSelectionModel().getSelectedItem();
        if(selectedCompany!=null){
            Window parentWindow = ((Node) actionEvent.getSource()).getScene().getWindow();
            calculationController.fillCompany( selectedCompany );
            switch (clickedButton.getId()){
                case "calculate":{
                    calculate( parentWindow );}
                case "getReports":{
                    Client.getInstance().send( "getReports" );
                    Client.getInstance().send( String.valueOf( selectedCompany.getId() ) );
                    loader1 = new FXMLLoader( getClass().getClassLoader().getResource( "client/FXML/Reports.fxml" ) );
                    reports = loader1.load();
                    reportsController = loader1.getController();
                    reportsController.fillCompany(selectedCompany  );

                    reports(parentWindow);}
            }
        }

    }
}

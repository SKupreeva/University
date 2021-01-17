package client.Controllers;

import client.*;
import client.Exceptions.NumberException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class CalculationController {
    public TextField x1;
    public TextField x2;
    public TextField x3;
    public TextField x4;
    public TextField x5;
    public TextField x6;
    public TextField x7;
    public TextField x8;
    public TextField x9;
    public Button calculate;
    public RadioButton eur;
    public RadioButton byn;
    public RadioButton rub;
    public RadioButton usd;

    public RadioButton selected;

    private Company company;
    ToggleGroup group = new ToggleGroup();
    Report report;

    private Parent result;

    private FXMLLoader loader = new FXMLLoader();

    private Stage resultDialogStage = new Stage();

    private ResultController resultController;

    public void getResult(Window parentWindow) {
        String res = null;
        try {
            res = Client.getInstance().get();
        resultController.initialize(res);
            resultDialogStage = new Stage(  );
            resultDialogStage.setTitle( "Результат" );
            resultDialogStage.setResizable( false );
            resultDialogStage.setScene( new Scene( result ) );
            resultDialogStage.initModality( Modality.WINDOW_MODAL );
            resultDialogStage.initOwner( parentWindow );
            resultDialogStage.showAndWait();
            Stage stage = (Stage) calculate.getScene().getWindow();
            stage.close();

    } catch (IOException ex) {
        ex.printStackTrace();
    }
    }

    public Company fillCompany(Company selectedCompany){
        company = selectedCompany;
        return company;
    }

    public void onCheck(ActionEvent actionEvent) {
        selected = (RadioButton) group.getSelectedToggle();
    }

    public  void initialize(){
        usd.setToggleGroup( group );
        byn.setToggleGroup( group );
        rub.setToggleGroup( group );
        eur.setToggleGroup( group );
        try{
            loader = new FXMLLoader( getClass().getClassLoader().getResource( "client/FXML/Result.fxml" ) );
            result = loader.load();
            resultController = loader.getController();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void calculate(ActionEvent actionEvent) {
    report = new Report();
        report.setDate();
        boolean key = true;
        boolean flag = true;
        if( x1.getText().isEmpty() ){ key = false;}
        if( x2.getText().isEmpty() ){ key = false;}
        if( x3.getText().isEmpty() ){ key = false;}
        if( x4.getText().isEmpty() ){ key = false;}
        if( x5.getText().isEmpty() ){ key = false;}
        if( x6.getText().isEmpty() ){ key = false;}
        if( x7.getText().isEmpty() ){ key = false;}
        if( x8.getText().isEmpty() ){ key = false;}
        if( x9.getText().isEmpty() ){ key = false;}
        try{
            report.setX1( Double.parseDouble( x1.getText() ) );
            report.setX2( Double.parseDouble( x2.getText() ) );
            report.setX3( Double.parseDouble( x3.getText() ) );
            report.setX4( Double.parseDouble( x4.getText() ) );
            report.setX5( Double.parseDouble( x5.getText() ) );
            report.setX6( Double.parseDouble( x6.getText() ) );
            report.setX7( Double.parseDouble( x7.getText() ) );
            report.setX8( Double.parseDouble( x8.getText() ) );
            report.setX9( Double.parseDouble( x9.getText() ) );
        } catch (NumberFormatException e) {
            Alert alert = new Alert(  Alert.AlertType.WARNING);
            alert.setTitle( "Ошибка" );
            flag = false;
            alert.setHeaderText("Ошибка! Вы ввели число неправильно!");
            alert.showAndWait();
        }

        try{
            selected.getId();
        } catch (NullPointerException e){
            Alert alert = new Alert(  Alert.AlertType.WARNING);
            alert.setTitle( "Ошибка" );
            alert.setHeaderText( "Вы не выбрали валюту!" );
            alert.showAndWait();
            flag = false;
        }

        if(key == flag == true) {
            try{
                report.setIDCompany( company.getId() );

                JSONObject object = new JSONObject();
                object.put( "idCompany", company.getId() );
                object.put( "date", report.getDate() );
                object.put( "x1", report.getX1() );
                object.put( "x2", report.getX2() );
                object.put( "x3", report.getX3() );
                object.put( "x4", report.getX4() );
                object.put( "x5", report.getX5() );
                object.put( "x6", report.getX6() );
                object.put( "x7", report.getX7() );
                object.put( "x8", report.getX8() );
                object.put( "x9", report.getX9() );
                object.put( "IDCompany", report.getIDCompany() );
                Client.getInstance().send( "calculate" );
                Client.getInstance().send( String.valueOf( User.getInstance().getID() ) );
                Client.getInstance().send( selected.getId() );
                Client.getInstance().send( object.toString() );
                Window parentWindow = ((Node) actionEvent.getSource()).getScene().getWindow();
                getResult( parentWindow );
            }
            catch (JSONException ex) {
                ex.printStackTrace();
            }
        }else{
            Alert alert = new Alert(  Alert.AlertType.WARNING);
            alert.setTitle( "Ошибка" );
            alert.setHeaderText( "Заполните все поля!" );
            alert.showAndWait();
        }
    }
}

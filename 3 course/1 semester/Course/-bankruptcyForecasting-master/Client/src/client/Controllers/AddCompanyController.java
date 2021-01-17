package client.Controllers;

import client.Client;
import client.CollectionCompanies;
import client.Company;
import client.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;

public class AddCompanyController {
    public Button adding;
    public TextField address;
    public TextField name;

    public void adding(ActionEvent actionEvent) {
        Company company = new Company(  );
        if( name.getText().isEmpty() ){
            name.setText( "Введите название!" );
        }
        if( address.getText().isEmpty() ){
            address.setText( "Введите адрес!" );
        }
        if(name.getText()!=null || address.getText()!=null || !name.getText().trim().isEmpty() || !address.getText().trim().isEmpty() ) {
            company.setName( name.getText() );
            company.setAddress( address.getText() );
            JSONObject object = new JSONObject();
            try {
                object.put( "name", company.getName() );

                object.put( "address", company.getAddress() );
                CollectionCompanies.getInstance().addCompany( company );
                Client.getInstance().send( "addCompany" );
                Client.getInstance().send( String.valueOf( User.getInstance().getID() ) );
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            Client.getInstance().send( object.toString() );
            Stage stage = (Stage) adding.getScene().getWindow();
            stage.close();
        }
    }
}

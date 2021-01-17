package client.Controllers;

import client.Client;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ResultController {

    public Label result;
    public Label description;
    public Button exit;
    public Button txt;
    public Button pdf;

    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

    public void initialize(String res){
        result.setText( "Результат: "+ String.format("%.2f", Double.parseDouble(res)) );
    }

    public void save(ActionEvent actionEvent) {

        Object source = actionEvent.getSource();
        if(!(source instanceof Button)){return;}

        Button clickedButton = (Button) source;
        switch (clickedButton.getId()){
            case "txt":
            {Client.getInstance().send( "txt" );
            break;}
            case "pdf":{
                Client.getInstance().send( "pdf" );
                break;
            }}
    }
}

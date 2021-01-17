package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML/Main.fxml"));
            primaryStage.setTitle("Прогнозирование платежеспособности предприятия");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            primaryStage.setResizable(false);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
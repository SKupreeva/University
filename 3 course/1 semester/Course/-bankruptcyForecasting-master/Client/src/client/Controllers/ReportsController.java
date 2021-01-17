package client.Controllers;

import client.*;
import client.Exceptions.NoChoiceException;
import client.Exceptions.NullException;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ReportsController {

    public Button exit;
    //public Label companyThis;
    public TableView<Report> reports;
    public TableColumn<Report, String> date;
    public TableColumn<Report, String> x1;
    public TableColumn<Report, String> x2;
    public TableColumn<Report, String> x3;
    public TableColumn<Report, String> x4;
    public TableColumn<Report, String> x5;
    public TableColumn<Report, String> x6;
    public TableColumn<Report, String> x7;
    public TableColumn<Report, String> x8;
    public TableColumn<Report, String> x9;
    public TableColumn<Report, String> result;
    public Button search;
    public Button sort;
    public Button filter;
    public TextField dateBy;
    public Button diagrammaReports;
    public Button diagrammaKoef;
    public ChoiceBox filterBy;

    @FXML
    private Company company;
    private UserS user;

    private CollectionReports collection;

    private BarController controllerBar;

    private LineController lineController;

    public Company fillCompany(Company selectedCompany){
        company = selectedCompany;
       // companyThis.setText( company.getName() );
        return company;
    }

    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

    public void searchByDate(ActionEvent actionEvent) {
        String key = dateBy.getText();
        try{
            if(key != null || !key.isEmpty()) throw new NullException();
        } catch (NullException e) {
            e.printStackTrace();
            System.out.println("Повторите ввод");
            Alert alert = new Alert(  Alert.AlertType.ERROR);
            alert.setTitle( "Ошибка" );
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
        FilteredList<Report> filteredData = new FilteredList<>(collection.getReports(), p -> true);
        filteredData.setPredicate( report -> {
            if(key == null || key.isEmpty()){
                return true;
            }
            if(report.getDate().equals( key )){ return true;}
            return false;
        } );
        SortedList<Report> sortedList = new SortedList<>( filteredData );
        sortedList.comparatorProperty().bind( reports.comparatorProperty() );
        reports.setItems( sortedList );
    }

    public void filterByResult(ActionEvent actionEvent){
        Object value = filterBy.getValue();
        FilteredList<Report> filteredData = new FilteredList<>(collection.getReports(), p -> true);
        filteredData.setPredicate( report -> {
            if(value == null){
                return true;
            }
            if ("Больше 0".equals(value)) {
                return report.getResult() > 0;
            } else if ("Меньше 0".equals(value)) {
                return report.getResult() < 0;
            } else if ("Равно 0".equals(value)) {
                return report.getResult() == 0;
            }
            return false;
        } );
        SortedList<Report> sortedList = new SortedList<>( filteredData );
        sortedList.comparatorProperty().bind( reports.comparatorProperty() );
        reports.setItems( sortedList );
    }

    public void sortByResult(ActionEvent actionEvent) {
        SortedList<Report> sortedList = new SortedList<>( collection.getReports(),
                (Report stock1, Report stock2) -> {
                    if( stock1.getResult() < stock2.getResult() ) {
                        return -1;
                    } else if( stock1.getResult() > stock2.getResult() ) {
                        return 1;
                    } else {
                        return 0;
                    }
                } );

        reports.setItems( sortedList );
    }

    public void initialize(){
        filterBy.getItems().add("Больше 0");
        filterBy.getItems().add("Меньше 0");
        filterBy.getItems().add("Равно 0");
        collection = new CollectionReports();
        collection.setReports();
        date.setCellValueFactory(new PropertyValueFactory<Report, String>("date"));
        result.setCellValueFactory(new PropertyValueFactory<Report, String>("result"));
        x1.setCellValueFactory(new PropertyValueFactory<Report, String>("x1"));
        x2.setCellValueFactory(new PropertyValueFactory<Report, String>("x2"));
        x3.setCellValueFactory(new PropertyValueFactory<Report, String>("x3"));
        x4.setCellValueFactory(new PropertyValueFactory<Report, String>("x4"));
        x5.setCellValueFactory(new PropertyValueFactory<Report, String>("x5"));
        x6.setCellValueFactory(new PropertyValueFactory<Report, String>("x6"));
        x7.setCellValueFactory(new PropertyValueFactory<Report, String>("x7"));
        x8.setCellValueFactory(new PropertyValueFactory<Report, String>("x8"));
        x9.setCellValueFactory(new PropertyValueFactory<Report, String>("x9"));
        reports.setItems( collection.getReports());
    }

    public void openDiagrReports(ActionEvent actionEvent) {

        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("client/FXML/LineChart.fxml"));
            Parent root = loader.load();
            lineController = loader.getController();
            lineController.initialize( collection );
            stage.setScene(new Scene(root));
            stage.setTitle("Диаграмма результатов компании");
            stage.setResizable(false);
            stage.initModality( Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    public void openDiagrKoef(ActionEvent actionEvent) throws IOException {
        Report selectedReport = (Report)  reports.getSelectionModel().getSelectedItem();

        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("client/FXML/BarChart.fxml"));
            Parent root = loader.load();
            controllerBar = loader.getController();
            controllerBar.initialize( selectedReport );
            stage.setScene(new Scene(root));
            stage.setTitle("Диаграмма коэффициентов отчета");
            stage.setResizable(false);
            stage.initModality( Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}

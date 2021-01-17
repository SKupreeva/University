package client.Controllers;

import client.Report;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BarController {
    public Button exitBarChart;
    public BarChart barChart;
    public CategoryAxis xAxis;
    public NumberAxis yAxis;
    private Report myreport;

    public void exitChart(ActionEvent actionEvent) {
        Stage stage = (Stage) exitBarChart.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize(Report report){
        if(report==null){
            System.out.println( "Выберите отчет!" );
        }else{
        myreport = report;
        xAxis.setLabel("Коэффициенты");
        yAxis.setLabel("Значения");

        XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
        System.out.println( myreport.getX5() );
        dataSeries1.getData().add(new XYChart.Data<String, Number>("X1", myreport.getX1()));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("X2", myreport.getX2()));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("X3", myreport.getX3()));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("X4", myreport.getX4()));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("X5", myreport.getX5()));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("X6", myreport.getX6()));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("X7", myreport.getX7()));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("X8", myreport.getX8()));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("X9", myreport.getX9()));

        barChart.getData().add(dataSeries1);
        barChart.setTitle("Коэффициенты отчета");}


    }
}

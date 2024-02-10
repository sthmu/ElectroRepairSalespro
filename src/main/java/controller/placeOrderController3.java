package controller;

import DTO.OrderDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class placeOrderController3 {

    private OrderDto order;

    public void initialize(OrderDto dto){
        this.order=dto;
    }
    public BorderPane pane;

    public void printReciptBtnOnPress(ActionEvent actionEvent) throws JRException, SQLException {
        Map<String, Object> parameters = new HashMap<>();
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrorepairsalespro", "root", "root");

        parameters.put("orderId",order.getOrderId());
        JasperDesign design = JRXmlLoader.load("src/main/resources/reports/order_report.jrxml");
        JasperReport report = JasperCompileManager.compileReport(design);

        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, conn);
        JasperViewer.viewReport(jasperPrint,false);



    }

    public void emailReciptBtnOnPress(ActionEvent actionEvent) {

    }

    public void goToDashboardBtn(ActionEvent actionEvent) throws IOException {

        Stage thisStage = (Stage) pane.getScene().getWindow();
        thisStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"))));
    }

    public void placeAnotherOrderBtn(ActionEvent actionEvent) throws IOException {
        Stage thisStage = (Stage) pane.getScene().getWindow();
        thisStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/PlaceOrder1.fxml"))));
    }
}

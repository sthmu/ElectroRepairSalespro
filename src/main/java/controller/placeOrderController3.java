package controller;

import DTO.OrderDto;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;

public class placeOrderController3 {

    private OrderDto order;

    public void initialize(OrderDto dto){
        this.order=dto;
    }
    public BorderPane pane;

    public void printReciptBtnOnPress(ActionEvent actionEvent) {
    }

    public void emailReciptBtnOnPress(ActionEvent actionEvent) {
    }

    public void goToDashboardBtn(ActionEvent actionEvent) {
    }

    public void placeAnotherOrderBtn(ActionEvent actionEvent) {
    }
}

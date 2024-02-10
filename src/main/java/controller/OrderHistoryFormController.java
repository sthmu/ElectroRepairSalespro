package controller;

import DTO.OrderDto;
import DTO.tm.OrderInfoTm;
import Services.BoFactory;
import Services.BoType;
import Services.custom.CategoryItemBo;
import Services.custom.CustomerBo;
import Services.custom.OrderBo;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class OrderHistoryFormController implements Initializable {
    public BorderPane pane;
    public JFXComboBox statusComboBox;
    public JFXTextField SearchByNameTxt;
    public ComboBox categoryComboBox;
    public JFXTreeTableView<OrderInfoTm> orderTbl;
    public TreeTableColumn idCol;
    public TreeTableColumn dateCol;
    public TreeTableColumn nameCol;
    public TreeTableColumn itemCol;
    public Label orderIdLbl;
    public Label customerNameLbl;
    public Label itemNameTxt;
    public JFXTextArea descriptionTxt;
    public JFXComboBox selectedOrderStatusComboBox;
    public JFXTextField phoneNumberTxt;
    public JFXTextField emailTxt;

    OrderBo orderBo = BoFactory.getInstance().getBo(BoType.ORDER_BO);
    CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER_BO);
    CategoryItemBo categoryItemBo = BoFactory.getInstance().getBo(BoType.CATEGORY_ITEM_BO);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("orderId"));
        dateCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("date"));
        nameCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
        itemCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("itemName"));

        orderTbl.setRowFactory(orderInfoTmTreeTableView -> new JFXTreeTableRow<>());
        orderTbl.setRowFactory(tv -> new JFXTreeTableRow<OrderInfoTm>() {
            @Override
            protected void updateItem(OrderInfoTm item, boolean empty) {
                super.updateItem(item, empty);


                if (item == null) {
                    setStyle("");
                } else {
                    String status = (OrderBo.isInList(item.getOrderId()).getStatus()).toUpperCase();
                    switch (status) {
                        case "PENDING":
                            setStyle("-fx-background-color: orange;");
                            break;
                        case "PROCESSING":
                            setStyle("-fx-background-color: yellow;");
                            break;
                        case "COMPLETED":
                            setStyle("-fx-background-color: green;");
                            break;
                    }
                }
            }
        });

        loadOrderTable();


    }

    private void loadOrderTable() {
        ObservableList<OrderInfoTm> tmList = FXCollections.observableArrayList();
        LinkedList<OrderDto> orderDtoArrayList = (LinkedList<OrderDto>) orderBo.getAll();
        for (OrderDto orderDto : orderDtoArrayList) {
            OrderInfoTm orderInfoTm = new OrderInfoTm(
                    orderDto.getOrderId(),
                    orderDto.getDate(),
                    CustomerBo.isInList(orderDto.getCustId()).getName(),
                    CategoryItemBo.isInList(orderDto.getItemCategoryCode()).getCatItemName()
            );
            tmList.add(orderInfoTm);
        }
        RecursiveTreeItem<OrderInfoTm> treeOrderInfo = new RecursiveTreeItem<>(tmList, RecursiveTreeObject::getChildren);
        orderTbl.setRoot(treeOrderInfo);
        orderTbl.setShowRoot(false);



    }

    public void statusComboBoxOnAction(ActionEvent actionEvent) {
    }

    public void searchBarOnAction(ActionEvent actionEvent) {
    }

    public void updateBtnOnAction(ActionEvent actionEvent) {
    }

    public void closeOrderBtnOnAction(ActionEvent actionEvent) {
    }

    public void sendEmailBtnOnAction(ActionEvent actionEvent) {
    }

    public void bckBtnOnAction(ActionEvent actionEvent) {
    }


}

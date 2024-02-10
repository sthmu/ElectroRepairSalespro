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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
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

            private String getColor(OrderInfoTm orderInfoTm){
                OrderDto actualOrderDto=OrderBo.isInList(orderInfoTm.getOrderId());

                String status = (actualOrderDto.getStatus()).toUpperCase();

                if(status.equalsIgnoreCase("pending")){
                    if(ChronoUnit.DAYS.between(LocalDate.parse(actualOrderDto.getDate()), LocalDate.now())>10) {
                        return "Red";
                    }
                    return "orange";
                }
                return status.equalsIgnoreCase("processing")?"lightyellow":"lightGreen";
            }
            @Override
            protected void updateItem(OrderInfoTm item, boolean empty) {
                super.updateItem(item, empty);


                if (item == null) {
                    setStyle("");
                } else {
                    setStyle("-fx-background-color:"+ getColor(item));

                }
            }
        });

        categoryComboBox.setItems(FXCollections.observableArrayList("Electrical", "Electronic", "All"));
        categoryComboBox.getSelectionModel().select(2);
        categoryComboBox.getSelectionModel().selectedItemProperty().addListener((observableValue, old, newvalue) -> {
            loadOrderTable();
        });

        statusComboBox.setItems(FXCollections.observableArrayList("Pending","Processing","Completed","All"));

        statusComboBox.getSelectionModel().selectedItemProperty().addListener((observableValue, old, newvalue) -> {
            loadOrderTable();
        });


        loadOrderTable();

        orderTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if(newValue!=null)setData(newValue.getValue());
        });


    }

    private void setData(OrderInfoTm orderInfoTm) {
        OrderDto orderDto=OrderBo.isInList(orderInfoTm.getOrderId());
        //configure selectedOrderComboBox Setttings
        ObservableList<String> scomboboxItemList=FXCollections.observableArrayList();
        String orderStatus=orderDto.getStatus();
        if(orderStatus.equalsIgnoreCase("pending")){
            scomboboxItemList.addAll(orderStatus,"Processing","Completed");
        }
        else if(orderStatus.equalsIgnoreCase("processing")){
            scomboboxItemList.addAll(orderStatus,"Completed");
        }
        selectedOrderStatusComboBox.setItems(scomboboxItemList);



        orderIdLbl.setText("#OR"+orderDto.getOrderId());
        customerNameLbl.setText(CustomerBo.isInList(orderDto.getCustId()).getName());
        phoneNumberTxt.setText(CustomerBo.isInList(orderDto.getCustId()).getPhone());
        emailTxt.setText(CustomerBo.isInList(orderDto.getCustId()).getEmail());
        itemNameTxt.setText(CategoryItemBo.isInList(orderDto.getItemCategoryCode()).getCatItemName());
        descriptionTxt.setText(orderDto.getDescription());
        selectedOrderStatusComboBox.getSelectionModel().select(orderStatus);
    }

    private void loadOrderTable() {
        String comboboxValue = (String) categoryComboBox.getSelectionModel().getSelectedItem();
        String statusComboBoxValue=(String) statusComboBox.getSelectionModel().getSelectedItem();
        System.out.println("LOADING TABLE");
        ObservableList<OrderInfoTm> tmList = FXCollections.observableArrayList();
        LinkedList<OrderDto> orderDtoArrayList = (LinkedList<OrderDto>) orderBo.getAll();
        if (comboboxValue != null && (comboboxValue.equalsIgnoreCase("Electronic") | comboboxValue.equalsIgnoreCase("Electrical"))) {

        }
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
        System.out.println("ComboBox Action" );
    }

    public void searchBarOnAction(ActionEvent actionEvent) {
    }

    public void updateBtnOnAction(ActionEvent actionEvent) {
    }

    public void closeOrderBtnOnAction(ActionEvent actionEvent) {
    }

    public void sendEmailBtnOnAction(ActionEvent actionEvent) {
    }

    public void bckBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage thisStage = (Stage) pane.getScene().getWindow();
        thisStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"))));
    }


}

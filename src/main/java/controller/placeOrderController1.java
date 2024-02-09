package controller;

import DTO.CategoryItemDto;
import Services.custom.impl.CategoryItemBoImpl;
import com.jfoenix.controls.JFXButton;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class placeOrderController1 implements Initializable {

    public BorderPane pane;
    public FlowPane flowPane;
    public ComboBox categoryComboBox;
    public JFXButton addItemToCatalogBtn;
    private ItemBox itemSelected = null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadItems();


        categoryComboBox.setItems(FXCollections
                .observableArrayList("Electrical", "Electronic", "All"));
        categoryComboBox.getSelectionModel().select(2);

        categoryComboBox.getSelectionModel().selectedItemProperty().addListener((observableValue, old, newvalue) -> {
            loadItems();
        });

    }


    //IN THIS METHOD IT LOADS THE ITEMS CATALOG BASED ON WHATS SELECETED IN THE COMBOBOX
    private void loadItems() {
        String comboboxValue = (String) categoryComboBox.getSelectionModel().getSelectedItem();
        List<CategoryItemDto> itemDtoList = CategoryItemBoImpl.getItemList();
        flowPane.getChildren().clear();
        if (comboboxValue != null && (comboboxValue.equalsIgnoreCase("Electronic") | comboboxValue.equalsIgnoreCase("Electrical"))) {
            for (CategoryItemDto itemDto : itemDtoList) {
                if (itemDto.getCategory().equalsIgnoreCase(comboboxValue)) {
                    ItemBox itemBox = createItemBox(itemDto);
                    flowPane.getChildren().add(itemBox);
                }
            }
        } else {
            for (CategoryItemDto itemDto : itemDtoList) {
                ItemBox itemBox = createItemBox(itemDto);
                flowPane.getChildren().add(itemBox);
            }
        }
    }

    private ItemBox createItemBox(CategoryItemDto itemDto) {
        ItemBox itemBox = new ItemBox(itemDto);

        ImageView imageView = new ImageView();
        imageView.setImage(itemDto.getImage());
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);

        Label itemCode = new Label(itemDto.getCatItemCode());

        Label itemName = new Label(itemDto.getCatItemName());

        itemName.setStyle("-fx-font-size: 16");

        itemBox.getChildren().addAll(imageView, itemCode, itemName);

        itemBox.setSpacing(10);

        //what happenes when clicked
        //--item clicked background becomes dark AND THE BEFORE CLICKED ONE BECOMES NORMAL
        //--item clicked triggers
        itemBox.setOnMouseClicked(mouseEvent -> handleItemClick(itemBox));

        addPressAndHoldHandler(itemBox, Duration.seconds(1),
                event -> {
                    System.out.println("68 th in placeorderCOntoler");
                });
        return itemBox;

    }

    private void addPressAndHoldHandler(ItemBox node, Duration holdTime,
                                        EventHandler<MouseEvent> handler) {

        class Wrapper<T> {
            T content;
        }
        Wrapper<MouseEvent> eventWrapper = new Wrapper<>();

        PauseTransition holdTimer = new PauseTransition(holdTime);
        holdTimer.setOnFinished(event -> handler.handle(eventWrapper.content));


        node.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            eventWrapper.content = event;
            holdTimer.playFromStart();
        });
        node.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> holdTimer.stop());
        node.addEventHandler(MouseEvent.DRAG_DETECTED, event -> holdTimer.stop());
    }

    private void handleItemClick(ItemBox itemClicked) {
        selectAnItem(itemClicked);
    }

    void selectAnItem(ItemBox itemClicked) {
        if (itemSelected != null) {
            itemSelected.setStyle("-fx-background-color: white;");

        }
        itemClicked.setStyle("-fx-background-color: gray;");
        itemSelected = itemClicked;


    }

    Stage addItemStage;

    public void addItemToCatalogBtnOnPress(ActionEvent actionEvent) throws IOException {

        addItemStage = new Stage();
        addItemStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AddItemCatalogForm.fxml"))));
        addItemStage.show();

        addItemStage.setOnHiding(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                loadItems();
            }
        });


    }

    public void addCustomerAndPlaceOrderBtnOnPress(ActionEvent actionEvent) throws IOException {
        Stage thisStage=(Stage) pane.getScene().getWindow();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/PlaceOrder2.fxml"));
        Parent root=loader.load();
        placeOrderController2 controller2=loader.getController();
        CategoryItemDto newItem= itemSelected.getItem();
        controller2.initialize(newItem);
        thisStage.setScene(new Scene(root));

    }


    public void bckBtnOnAction(ActionEvent actionEvent) {
        
    }
}

class ItemBox extends VBox {
    @Getter
    @Setter
    private CategoryItemDto item;

    ItemBox(CategoryItemDto ItemDto) {
        super();
        this.item = ItemDto;
    }

}



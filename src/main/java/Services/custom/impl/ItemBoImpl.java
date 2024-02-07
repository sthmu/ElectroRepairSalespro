package Services.custom.impl;

import DAO.custom.impl.ItemDaoimpl;
import DTO.ItemDto;
import Entity.Item;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

import java.util.LinkedList;
import java.util.List;

public class ItemBoImpl {

    private static List<ItemDto> itemList = initializeItemList();

    private ItemDaoimpl itemDaoimpl=new ItemDaoimpl();

    private static List<ItemDto> initializeItemList() {

        List<Item> entityList = ItemDaoimpl.getAll();
        List<ItemDto> tempModelList = new LinkedList<>();
        for (Item item : entityList) {

            tempModelList.add(new ItemDto(
                    item.getCode(),
                    item.getTitle(),
                    item.getDescription(),
                    new Image(item.getImgUrl()),
                    item.getCategory()
            ));
        }
        return tempModelList;

    }
    public boolean saveItem(ItemDto item) {
        boolean isSaved = itemDaoimpl.save(new Item(item.getCode(),item.getTitle(), item.getDescription(), item.getCategory(), item.getImage().getUrl()));
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Item Successfully Saved!").show();

            updateItemList(item);

            return true;
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Item Failed to be Saved!").show();
            return false;
        }
    }

    private void updateItemList(ItemDto item) {
        ItemDto itemFromList= isInList(item);
        if(itemFromList==null){
            itemList.add(item);
        }
        else{
            itemFromList.setDescription(item.getDescription());
            itemFromList.setImage(item.getImage());
            itemFromList.setCategory(item.getCategory());
        }

    }
    public static List<ItemDto> getItemList() {
        return itemList;
    }

    private ItemDto isInList(ItemDto item) {

        for(ItemDto itemInList:itemList){
            if(item.getCode().equalsIgnoreCase(itemInList.getCode())){
                return itemInList;
            }
        }
        return null;
    }
    private void removeFromItemList(String code){
        for(ItemDto itemInList:itemList){
            if(code.equalsIgnoreCase(itemInList.getCode())){
                itemList.remove(itemInList);
            }
        }

    }


    public static String getGeneratedCode() {
        int lastCodeNum=0;
        for(ItemDto item:itemList){
            int curCode=Integer.parseInt(item.getCode().substring(1,5));
            if(curCode>lastCodeNum){
                lastCodeNum=curCode;
            }
            System.out.println(curCode);
        }
        String LastCode=(++lastCodeNum)+"";

        for(int i=(LastCode).length();i<4;i++){
            LastCode="0"+LastCode;
        }

        return "P"+LastCode;
    }


}
